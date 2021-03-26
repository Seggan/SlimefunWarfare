package io.github.seggan.slimefunwarfare.items.guns;

import io.github.mooy1.infinitylib.core.ConfigUtils;
import io.github.mooy1.infinitylib.core.PluginUtils;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

@Getter
public class Gun extends SlimefunItem implements DamageableItem {

    public static final NamespacedKey LAST_USE = PluginUtils.getKey("last_use");

    private final int range;
    private final int minRange;
    private final int damageDealt;
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
        this.minRange = ConfigUtils.getBoolean("guns.min-range-on", true) ? minRange : 0;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);

        addItemHandler(getItemHandler());
    }

    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player p = e.getPlayer();
            ItemStack gun = p.getInventory().getItemInMainHand();
            if (!(SlimefunItem.getByItem(gun) instanceof Gun)) {
                return;
            }

            ItemMeta meta = gun.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            long lastUse = container.getOrDefault(Gun.LAST_USE, PersistentDataType.LONG, 0L);
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastUse) < cooldown) {
                p.sendMessage(ChatColor.RED + "The gun is still reloading!");
                return;
            }
            container.set(LAST_USE, PersistentDataType.LONG, currentTime);
            gun.setItemMeta(meta);
            shoot(p, gun);
        };
    }

    public void shoot(Player p, ItemStack gun) {
        PlayerInventory inv = p.getInventory();

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
                if (ConfigUtils.getBoolean("guns.use-bullets-from-inv", true)) {
                    for (int i = 0; i < inv.getSize(); i++) {
                        stack = inv.getItem(i);
                        item = SlimefunItem.getByItem(stack);
                        if (item instanceof Bullet) {
                            Bullet bullet = (Bullet) item;
                            multiplier = bullet.getMultiplier();
                            isFire = bullet.isFire();
                            ItemUtils.consumeItem(stack, true);
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
