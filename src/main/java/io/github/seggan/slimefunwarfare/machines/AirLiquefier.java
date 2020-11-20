package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AirLiquefier extends AContainer implements RecipeDisplayItem {

    public AirLiquefier() {
        super(Items.sfwarfareCategory, Items.AIR_LIQUEFIER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.PACKED_ICE), SlimefunItems.COOLING_UNIT, new ItemStack(Material.PACKED_ICE),
            SlimefunItems.COOLING_UNIT, SlimefunItems.FREEZER, SlimefunItems.COOLING_UNIT,
            new ItemStack(Material.PACKED_ICE), SlimefunItems.COOLING_UNIT, new ItemStack(Material.PACKED_ICE)
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5, SlimefunItems.TIN_CAN, Items.LIQUID_AIR);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.STRUCTURE_VOID);
    }

    @Override
    public int getEnergyConsumption() {
        return 64;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return "AIR_LIQUEFIER";
    }

    @Override
    public int getCapacity() {
        return 128;
    }
}
