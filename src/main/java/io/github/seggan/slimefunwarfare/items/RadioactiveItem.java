package io.github.seggan.slimefunwarfare.items;

import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

public class RadioactiveItem extends SlimefunItem implements Radioactive {

    @Getter(onMethod_ = @Override)
    private final Radioactivity radioactivity;

    public RadioactiveItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Radioactivity radioactivity) {
        super(category, item, recipeType, recipe);
        this.radioactivity = radioactivity;
    }
}
