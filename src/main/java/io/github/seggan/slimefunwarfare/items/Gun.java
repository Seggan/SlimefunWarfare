package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class Gun extends SlimefunItem implements DamageableItem {

    @Getter
    private final HashMap<UUID, Long> LAST_USES = new HashMap<>();

    private final int range;
    private final int minRange;
    private final int damageDealt;
    @Getter
    private final int cooldown;

    public Gun(SlimefunItemStack item, ItemStack[] recipe, int range, int damage, double cooldown) {
        super(Categories.GUNS, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.range = range;
        minRange = 0;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);

        addItemHandler(getItemHandler());
    }

    public Gun(SlimefunItemStack item, ItemStack[] recipe, int range, int minRange, int damage, double cooldown) {
        super(Categories.GUNS, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.range = range;
        this.minRange = SlimefunWarfare.getConfigSettings().isMinRangeOn() ? minRange : 0;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);

        addItemHandler(getItemHandler());
    }

    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player p = e.getPlayer();
            if (canShoot(p)) {
                shoot(e.getPlayer());
            } else {
                p.sendMessage(ChatColor.RED + "The gun is still reloading!");
            }
        };
    }

    public boolean canShoot(Player p) {
        UUID uuid = p.getUniqueId();
        Long lastUse = LAST_USES.get(uuid);
        long time = System.currentTimeMillis();
        if (lastUse != null) {
            if ((time - lastUse) >= cooldown) {
                LAST_USES.put(uuid, time);
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    public void shoot(Player p) {

        PlayerInventory inv = p.getInventory();

        ItemStack gun = inv.getItemInMainHand();
        if (!(SlimefunItem.getByItem(gun) instanceof Gun)) {
            return;
        }

        double multiplier;
        boolean isFire;
        bulletLoop: {
            ItemStack stack = inv.getItemInOffHand();
            SlimefunItem item = SlimefunItem.getByItem(stack);
            if (item instanceof Bullet) {
                Bullet bullet = (Bullet) item;
                multiplier = bullet.getMultiplier();
                isFire = bullet.isFire();
                stack.setAmount(stack.getAmount() - 1);
                inv.setItemInOffHand(stack);
                break bulletLoop;
            } else {
                if (SlimefunWarfare.getConfigSettings().isUseBulletsFromInv()) {
                    for (int i = 0; i < inv.getSize(); i++) {
                        stack = inv.getItem(i);
                        item = SlimefunItem.getByItem(stack);
                        if (item instanceof Bullet) {
                            Bullet bullet = (Bullet) item;
                            multiplier = bullet.getMultiplier();
                            isFire = bullet.isFire();
                            ItemUtils.consumeItem(stack, false); // idk the replaceConsumables thing
                            break bulletLoop;
                        }
                    }
                }
            }
            p.sendMessage(ChatColor.RED + "You have run out of bullets!");
            return;
        }

        Vector v = p.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(20);
        LlamaSpit bullet = p.launchProjectile(LlamaSpit.class);
        bullet.setMetadata("isGunBullet", new FixedMetadataValue(SlimefunWarfare.getInstance(), true));
        bullet.setMetadata("damage",
            new FixedMetadataValue(SlimefunWarfare.getInstance(), damageDealt * multiplier)
        );
        bullet.setMetadata("isFire", new FixedMetadataValue(SlimefunWarfare.getInstance(), isFire));
        bullet.setMetadata("locInfo", new FixedMetadataValue(
            SlimefunWarfare.getInstance(),
            Util.serializeLocation(p.getEyeLocation())
        ));
        bullet.setMetadata("rangeInfo", new FixedMetadataValue(
            SlimefunWarfare.getInstance(),
            range + ":" + minRange
            ));
        bullet.setVelocity(v);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}
