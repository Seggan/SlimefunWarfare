package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.items.Explosives;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BulletPress extends AContainer implements RecipeDisplayItem {

    public BulletPress() {
        super(Categories.GENERAL, Items.BULLET_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT,
            SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.ELECTRIC_MOTOR,
            SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack(Material.IRON_INGOT), new SlimefunItemStack(Items.LEAD_BULLET, 9));
        registerRecipe(10, SlimefunItems.LEAD_INGOT, new SlimefunItemStack(Items.LEAD_BULLET, 9));
        registerRecipe(10, SlimefunItems.SMALL_URANIUM, new SlimefunItemStack(Items.DU_BULLET, 9));
        registerRecipe(10, SlimefunItems.GOLD_12K, new SlimefunItemStack(Items.GOLD_BULLET, 9));
        registerRecipe(10, Explosives.PYRO_POWDER, Items.TRINITROBULLETENE);
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

    @Override
    public String getMachineIdentifier() {
        return "BULLET_PRESS";
    }

    @Override
    public int getCapacity() {
        return 32;
    }
}
