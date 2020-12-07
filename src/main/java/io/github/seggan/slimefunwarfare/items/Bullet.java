package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.machines.BulletPress;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

@Getter
public class Bullet extends SlimefunItem {

    private final double multiplier;
    private final boolean isFire;

    public Bullet(SlimefunItemStack item, ItemStack ammoType, double multiplier, boolean isFire) {
        super(Items.sfwarfareCategory, item, BulletPress.RECIPE_TYPE, new ItemStack[]{
            ammoType, null, null,
            null, null, null,
            null, null, null
        });

        this.multiplier = multiplier;
        this.isFire = isFire;
    }
}
