package io.github.seggan.slimefunwarfare.items.guns;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.lists.Guns;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class EnergyRifle extends Gun implements Rechargeable {

    public EnergyRifle() {
        super(Guns.ENERGY_RIFLE, new ItemStack[]{
            Items.OSMIUM_SUPERALLOY, Items.OSMIUM_SUPERALLOY, Items.SEGGANESSON,
            Items.ADVANCED_BARREL, Items.ADVANCED_BARREL, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
            Items.OSMIUM_SUPERALLOY, SlimefunItems.ENERGIZED_CAPACITOR, Items.ENERGY_RECTIFIER
        }, 100, 20, 0.2);
    }

    @Override
    public void shoot(Player p) {

        ItemStack gun = p.getInventory().getItemInMainHand();
        if (!(SlimefunItem.getByItem(gun) instanceof Gun)) {
            return;
        }

        Long lastUse = this.getLAST_USES().get(p.getUniqueId());
        long currentTime = System.currentTimeMillis();
        if (lastUse != null) {
            if ((currentTime - lastUse) < this.getCooldown()) {
                p.sendMessage(ChatColor.RED + "The gun is still reloading!");
                return;
            }
        }
        this.getLAST_USES().put(p.getUniqueId(), currentTime);

        if (getItemCharge(gun) < 5) {
            return;
        } else {
            removeItemCharge(gun, 5);
        }

        Vector v = p.getEyeLocation().subtract(0, 1, 0).getDirection().multiply(10);
        ShulkerBullet bullet = p.launchProjectile(ShulkerBullet.class);
        bullet.setTarget(null);

        bullet.setMetadata("isGunBullet", new FixedMetadataValue(SlimefunWarfare.getInstance(), true));
        bullet.setMetadata("damage",
            new FixedMetadataValue(SlimefunWarfare.getInstance(), this.getDamageDealt())
        );
        bullet.setMetadata("isFire", new FixedMetadataValue(SlimefunWarfare.getInstance(), true));
        bullet.setMetadata("locInfo", new FixedMetadataValue(
            SlimefunWarfare.getInstance(),
            Util.serializeLocation(p.getEyeLocation())
        ));
        bullet.setMetadata("rangeInfo", new FixedMetadataValue(
            SlimefunWarfare.getInstance(),
            this.getRange() + ":0"
        ));
        bullet.setVelocity(v);
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 2500;
    }
}
