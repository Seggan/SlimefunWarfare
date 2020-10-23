package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.Items;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class Gun extends SimpleSlimefunItem<ItemUseHandler> implements DamageableItem {

    public static final HashMap<UUID, Long> LAST_USES = new HashMap<>();

    private final int range;
    private final int damageDealt;
    private final int cooldown;

    public Gun(SlimefunItemStack item, ItemStack[] recipe, int range, int damage, double cooldown) {
        super(Items.sfwarfareGunsCategory, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.range = range;
        damageDealt = damage;
        this.cooldown = (int) (cooldown * 1000);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            shoot(e.getPlayer());
        };
    }

    public void shoot(Player p) {

        PlayerInventory inv = p.getInventory();

        ItemStack gun = inv.getItemInMainHand();
        if (gun.getType() == Material.AIR) {
            return;
        }

        entityLoop: {
            for (Entity e : p.getNearbyEntities(range, range, range)) {
                if (e instanceof LivingEntity) {
                    if (getLookingAt(p, (LivingEntity) e)) {
                        break entityLoop;
                    }
                }
            }
            return;
        }

        Long lastUse = LAST_USES.get(p.getUniqueId());
        long currentTime = System.currentTimeMillis();
        if (lastUse != null) {
            if ((currentTime - lastUse) < cooldown) {
                p.sendMessage(ChatColor.RED + "The gun is still reloading!");
                return;
            }
        }
        LAST_USES.put(p.getUniqueId(), currentTime);

        double multiplier;
        boolean isFire;
        bulletLoop: {
            ItemStack stack = inv.getItemInOffHand();
            SlimefunItem item = SlimefunItem.getByItem(stack);
            if (item instanceof Bullet) {
                multiplier = ((Bullet) item).getMultiplier();
                isFire = item.getID().equals("DU_BULLET");
                stack.setAmount(stack.getAmount() - 1);
                inv.setItemInOffHand(stack);
                break bulletLoop;
            } else {
                ItemStack[] contents = inv.getContents();
                Iterator<ItemStack> iter = Arrays.stream(contents).iterator();
                while (iter.hasNext()) {
                    stack = iter.next();
                    item = SlimefunItem.getByItem(stack);
                    if (item instanceof Bullet) {
                        multiplier = ((Bullet) item).getMultiplier();
                        isFire = item.getID().equals("DU_BULLET");
                        stack.setAmount(stack.getAmount() - 1);
                        contents[ArrayUtils.indexOf(contents, stack)] = stack;
                        inv.setContents(contents);
                        break bulletLoop;
                    }
                }
            }
            p.sendMessage(ChatColor.RED + "You have run out of bullets!");
            return;
        }

        Vector v = p.getEyeLocation().getDirection().multiply(20);
        LlamaSpit bullet = p.launchProjectile(LlamaSpit.class);
        bullet.setMetadata("damage",
            new FixedMetadataValue(SlimefunWarfare.getInstance(), damageDealt * multiplier)
        );
        bullet.setMetadata("isFire", new FixedMetadataValue(SlimefunWarfare.getInstance(), isFire));
        bullet.setVelocity(v);
    }

    private boolean getLookingAt(Player player, LivingEntity livingEntity){
        Location eye = player.getEyeLocation();
        Vector toEntity = livingEntity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}
