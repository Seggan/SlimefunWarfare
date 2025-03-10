package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ExplosiveSynthesizer extends AContainer implements RecipeDisplayItem {

    public ExplosiveSynthesizer() {
        super(Categories.MACHINES, Items.EXPLOSIVE_SYNTHESIZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.REINFORCED_PLATE.item(), Items.REINFORCED_SLIMESTEEL.item(),
            SlimefunItems.REINFORCED_PLATE.item(), SlimefunItems.BLISTERING_INGOT_3.item(), SlimefunItems.REINFORCED_PLATE.item(),
            Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.REINFORCED_PLATE.item(), Items.REINFORCED_SLIMESTEEL.item()
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5,
            new ItemStack[]{Items.PURIFIED_LIQUID_NITROGEN.item(), new ItemStack(Material.COAL)},
            new ItemStack[]{CustomItemStack.create(Items.AZIDOAZIDE_AZIDE.item(), 2)}
        );

        registerRecipe(5,
            new ItemStack[]{Items.LIQUID_NITROGEN.item(), new ItemStack(Material.DRIED_KELP)},
            new ItemStack[]{CustomItemStack.create(Items.NITROGEN_TRIIODIDE.item(), 2)}
        );

        registerRecipe(5,
            new ItemStack[]{SlimefunItems.SULFATE.item(), SlimefunItems.OIL_BUCKET.item()},
            new ItemStack[]{CustomItemStack.create(Items.THIOACETONE.item(), 2)}
        );
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GUNPOWDER);
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
        return "EXPLOSIVE_SYNTHESIZER";
    }

    @Override
    public int getCapacity() {
        return 128;
    }
}
