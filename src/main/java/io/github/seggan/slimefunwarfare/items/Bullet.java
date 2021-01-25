package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

@Getter
public class Bullet extends SlimefunItem {

    private final double multiplier;
    private final boolean isFire;

    public Bullet(SlimefunItemStack item, ItemStack ammoType, double multiplier, boolean isFire) {
        super(Categories.GENERAL, item, RecipeTypes.BULLET_PRESS, new ItemStack[]{
            ammoType, null, null,
            null, null, null,
            null, null, null
        });

        this.multiplier = multiplier;
        this.isFire = isFire;
    }
}
