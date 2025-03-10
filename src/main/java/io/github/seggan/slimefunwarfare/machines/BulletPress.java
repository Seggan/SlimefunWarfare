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

public class BulletPress extends AContainer implements RecipeDisplayItem {

    public BulletPress() {
        super(Categories.MACHINES, Items.BULLET_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),
            SlimefunItems.ELECTRIC_MOTOR.item(), null, SlimefunItems.ELECTRIC_MOTOR.item(),
            SlimefunItems.REINFORCED_ALLOY_INGOT.item(), new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT.item()
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack(Material.IRON_INGOT), Items.IRON_BULLET.add(8));
        registerRecipe(10, SlimefunItems.LEAD_INGOT.item(), Items.LEAD_BULLET.add(8));
        registerRecipe(10, SlimefunItems.SMALL_URANIUM.item(), Items.DU_BULLET.add(8));
        registerRecipe(10, SlimefunItems.GOLD_12K.item(), Items.GOLD_BULLET.add(8));
        registerRecipe(10, Items.PYRO_POWDER.item(), Items.TRINITROBULLETENE.item());
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.BOW);
    }

    @Override
    public int getEnergyConsumption() {
        return 16;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "BULLET_PRESS";
    }

    @Override
    public int getCapacity() {
        return 32;
    }
}
