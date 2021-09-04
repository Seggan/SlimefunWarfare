package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

public class EnergyBlade extends SlimefunItem implements Rechargeable {

    public EnergyBlade() {
        super(Categories.MELEE, Items.ENERGY_BLADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SEGGANESSON, SlimefunItems.SYNTHETIC_DIAMOND, Items.SEGGANESSON,
            Items.SEGGANESSON, SlimefunItems.SYNTHETIC_DIAMOND, Items.SEGGANESSON,
            Items.OSMIUM_SUPERALLOY, Items.ENERGY_RECTIFIER, Items.OSMIUM_SUPERALLOY
        });
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 2500;
    }
}
