package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ElementalReactor extends AGenerator {

    public ElementalReactor() {
        super(Categories.MACHINES, Items.ELEMENTAL_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.UNPATENTABLIUM, Items.OSMIUM_SUPERALLOY, Items.UNPATENTABLIUM,
            Items.OSMIUM_SUPERALLOY, Items.POWER_SUIT_GENERATOR, Items.OSMIUM_SUPERALLOY,
            Items.UNPATENTABLIUM, Items.OSMIUM_SUPERALLOY, Items.UNPATENTABLIUM
        });
        setCapacity(32_768);
        setEnergyProduction(16_384);
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHER_STAR);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(new MachineFuel(60 * 60 * 4, Items.UNPATENTABLIUM));
        registerFuel(new MachineFuel(60 * 60, Items.SEGGANESSON));
    }
}
