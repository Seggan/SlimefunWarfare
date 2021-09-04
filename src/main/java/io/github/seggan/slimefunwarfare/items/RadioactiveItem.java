package io.github.seggan.slimefunwarfare.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

public class RadioactiveItem extends SlimefunItem implements Radioactive {

    @Getter(onMethod_ = @Override)
    private final Radioactivity radioactivity;

    public RadioactiveItem(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Radioactivity radioactivity) {
        super(category, item, recipeType, recipe);
        this.radioactivity = radioactivity;
    }
}
