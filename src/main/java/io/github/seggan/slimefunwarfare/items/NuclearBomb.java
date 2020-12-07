package io.github.seggan.slimefunwarfare.items;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

public class NuclearBomb extends ABetterExplosive {

    public NuclearBomb(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public float getExplosionPower() {
        return 100;
    }

    @Override
    public int getFuseTicks() {
        return 100;
    }

    @Override
    public boolean setFire() {
        return true;
    }
}
