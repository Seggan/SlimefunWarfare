package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.machines.BulletPress;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

public class Bullet extends SlimefunItem {

    @Getter
    private final double multiplier;

    public Bullet(SlimefunItemStack item, ItemStack ammoType, double multiplier) {
        super(Items.sfwarfareCategory, item, BulletPress.RECIPE_TYPE, new ItemStack[]{
            ammoType, null, null,
            null, null, null,
            null, null, null
        });

        this.multiplier = multiplier;
    }
}
