package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.machines.BulletFactory;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

public class Bullet extends SlimefunItem {

    @Getter
    private final double multiplier;

    public Bullet(SlimefunItemStack item, ItemStack ammoType, double multiplier) {
        super(Items.sfwarfareCategory, item, BulletFactory.RECIPE_TYPE, new ItemStack[]{
            ammoType, null, null,
            null, null, null,
            null, null, null
        }, new SlimefunItemStack(item, 9));

        this.multiplier = multiplier;
    }
}
