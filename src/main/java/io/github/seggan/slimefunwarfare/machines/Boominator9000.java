package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Explosives;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Boominator9000 extends AContainer {

    public Boominator9000() {
        super(Categories.EXPLOSIVES, Items.BOOMINATOR_9000, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_CLOTH, SlimefunItems.REINFORCED_PLATE,
            new ItemStack(Material.BOWL), Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.BOWL),
            SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_CLOTH, SlimefunItems.REINFORCED_PLATE
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(600, SlimefunItems.BOOSTED_URANIUM, Explosives.ENRICHED_URANIUM);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.TNT);
    }

    @Override
    public int getEnergyConsumption() {
        return 1024;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public String getMachineIdentifier() {
        return "BOOMINATOR_9000";
    }

    @Override
    public int getCapacity() {
        return 2048;
    }
}
