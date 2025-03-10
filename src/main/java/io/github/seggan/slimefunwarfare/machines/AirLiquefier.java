package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class AirLiquefier extends AContainer implements RecipeDisplayItem {

    public AirLiquefier() {
        super(Categories.MACHINES, Items.AIR_LIQUEFIER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.PACKED_ICE), SlimefunItems.COOLING_UNIT.item(), new ItemStack(Material.PACKED_ICE),
            SlimefunItems.COOLING_UNIT.item(), SlimefunItems.FREEZER.item(), SlimefunItems.COOLING_UNIT.item(),
            new ItemStack(Material.PACKED_ICE), SlimefunItems.COOLING_UNIT.item(), new ItemStack(Material.PACKED_ICE)
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

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AIR_LIQUEFIER";
    }

    @Override
    public int getCapacity() {
        return 128;
    }
}
