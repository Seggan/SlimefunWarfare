package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Explosives;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ExplosiveSynthesizer extends AContainer implements RecipeDisplayItem {

    public static RecipeType RECIPE_TYPE = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "explosive_synthesizer"),
        Items.EXPLOSIVE_SYNTHESIZER
    );

    public ExplosiveSynthesizer() {
        super(Items.sfwarfareCategory, Items.EXPLOSIVE_SYNTHESIZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.REINFORCED_PLATE, Items.REINFORCED_SLIMESTEEL,
            SlimefunItems.REINFORCED_PLATE, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_PLATE,
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.REINFORCED_PLATE, Items.REINFORCED_SLIMESTEEL
        });
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5,
            new ItemStack[]{Items.PURIFIED_LIQUID_NITROGEN, new ItemStack(Material.COAL)},
            new ItemStack[]{new SlimefunItemStack(Explosives.AZIDOAZIDE_AZIDE, 2)}
        );

        registerRecipe(5,
            new ItemStack[]{Items.LIQUID_NITROGEN, new ItemStack(Material.DRIED_KELP)},
            new ItemStack[]{new SlimefunItemStack(Explosives.NITROGEN_TRIIODIDE, 2)}
        );

        registerRecipe(5,
            new ItemStack[]{SlimefunItems.SULFATE, SlimefunItems.OIL_BUCKET},
            new ItemStack[]{new SlimefunItemStack(Explosives.THIOACETONE, 2)}
        );
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.TNT);
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
        return "EXPLOSIVE_SYNTHESIZER";
    }

    @Override
    public int getCapacity() {
        return 128;
    }
}
