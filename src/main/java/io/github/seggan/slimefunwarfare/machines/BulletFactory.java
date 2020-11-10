package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class BulletFactory extends AContainer implements RecipeDisplayItem {
    public static final RecipeType RECIPE_TYPE = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "bullet_factory"),
        Items.BULLET_FACTORY
    );

    public BulletFactory() {
        super(Items.sfwarfareCategory, Items.BULLET_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT,
            SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.ELECTRIC_MOTOR,
            SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.PISTON), SlimefunItems.REINFORCED_ALLOY_INGOT
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(10, SlimefunItems.LEAD_INGOT, new SlimefunItemStack(Items.LEAD_BULLET, 9));
        registerRecipe(10, SlimefunItems.SMALL_URANIUM, new SlimefunItemStack(Items.DU_BULLET, 9));
        registerRecipe(10, SlimefunItems.GOLD_20K, new SlimefunItemStack(Items.GOLD_BULLET, 9));
        registerRecipe(10, Items.PYRO_POWDER, Items.TRINITROBULLETENE);
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
        return "BULLET_FACTORY";
    }

    @Override
    public int getCapacity() {
        return 32;
    }
}
