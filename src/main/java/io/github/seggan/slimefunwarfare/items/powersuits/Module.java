package io.github.seggan.slimefunwarfare.items.powersuits;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Heads;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import lombok.Getter;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public enum Module {
    NANOFIBER_CUSHION(0, "Nanofober Cushion", ArmorPiece.FEET, new ItemStack[]{
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL.item(), new ItemStack(Material.FEATHER),
        Items.REINFORCED_SLIMESTEEL.item(), Items.MODULE_CASE.item(), Items.REINFORCED_SLIMESTEEL.item(),
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL.item(), new ItemStack(Material.FEATHER),
    }, 5, "&7This module keeps you from", "&7taking fall damage"),
    MINI_JETS(1, "Mini Jets", ArmorPiece.FEET, new ItemStack[]{
        Items.OSMIUM_SUPERALLOY.item(), Items.REINFORCED_SLIMESTEEL.item(), Items.OSMIUM_SUPERALLOY.item(),
        SlimefunItems.STEEL_THRUSTER.item(), Items.MODULE_CASE.item(), SlimefunItems.STEEL_THRUSTER.item(),
        Items.OSMIUM_SUPERALLOY.item(), Items.REINFORCED_SLIMESTEEL.item(), Items.OSMIUM_SUPERALLOY.item()
    }, 8, "&7Gives you creative flight"),
    LIFE_SUPPORT(2, "Life Support Systems", PotionEffectType.REGENERATION, 2, ArmorPiece.CHEST, new ItemStack[]{
        SlimefunItems.ESSENCE_OF_AFTERLIFE.item(), Items.SLIMESTEEL.item(), SlimefunItems.ESSENCE_OF_AFTERLIFE.item(),
        Items.SLIMESTEEL.item(), Items.MODULE_CASE.item(), Items.SLIMESTEEL.item(),
        SlimefunItems.ESSENCE_OF_AFTERLIFE.item(), Items.SLIMESTEEL.item(), SlimefunItems.ESSENCE_OF_AFTERLIFE.item()
    }, 5),
    HEAT_SINKS(3, "Heat Sinks", PotionEffectType.FIRE_RESISTANCE, 0, ArmorPiece.CHEST, new ItemStack[]{
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT.item(), new ItemStack(Material.MAGMA_CREAM),
        Items.OSMIUM_INGOT.item(), Items.MODULE_CASE.item(), Items.OSMIUM_INGOT.item(),
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT.item(), new ItemStack(Material.MAGMA_CREAM)
    }, 1),
    HYDRAULICS(4, "Integrated Hydraulics", PotionEffectType.INCREASE_DAMAGE, 1, ArmorPiece.CHEST, new ItemStack[]{
        SlimefunItems.REINFORCED_PLATE.item(), Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.REINFORCED_PLATE.item(),
        SlimefunItems.FUEL_BUCKET.item(), Items.MODULE_CASE.item(), SlimefunItems.FUEL_BUCKET.item(),
        SlimefunItems.REINFORCED_PLATE.item(), Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.REINFORCED_PLATE.item()
    }, 3),
    REACTION_WHEELS(5, "Reaction Wheels", ArmorPiece.LEGS, new ItemStack[]{
        SlimefunItems.STEEL_PLATE.item(), Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.STEEL_PLATE.item(),
        SlimefunItems.STEEL_INGOT.item(), Items.MODULE_CASE.item(), SlimefunItems.STEEL_INGOT.item(),
        SlimefunItems.STEEL_PLATE.item(), Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.STEEL_PLATE.item()
    }, 5, "&7This module makes you", "&7sprint faster"),
    ENERGY_SHIELD(6, "Personal Energy Shield", PotionEffectType.DAMAGE_RESISTANCE, 2, ArmorPiece.CHEST, new ItemStack[]{
        Items.UNPATENTABLIUM.item(), Items.OSMIUM_SUPERALLOY.item(), Items.UNPATENTABLIUM.item(),
        Items.POWER_SUIT_GENERATOR.item(), Items.MODULE_CASE.item(), Items.POWER_SUIT_GENERATOR.item(),
        Items.ENERGY_RECTIFIER.item(), Items.SEGGANESSON.item(), Items.ENERGY_RECTIFIER.item()
    }, 3),
    AQUAMASK(7, "AquaMask™", PotionEffectType.CONDUIT_POWER, 0, ArmorPiece.HEAD, new ItemStack[]{
        new ItemStack(Material.PUFFERFISH), SlimefunItems.CLOTH.item(), new ItemStack(Material.PUFFERFISH),
        Items.SLIMESTEEL.item(), Items.MODULE_CASE.item(), Items.SLIMESTEEL.item(),
        new ItemStack(Material.PUFFERFISH), SlimefunItems.CLOTH.item(), new ItemStack(Material.PUFFERFISH)
    }, 2),
    AUXILIARY_GENERATOR(8, "Auxiliary Generator", null, new ItemStack[]{
        Items.OSMIUM_SUPERALLOY.item(), Items.POWER_SUIT_GENERATOR.item(), Items.OSMIUM_SUPERALLOY.item(),
        Items.SEGGANESSON.item(), Items.MODULE_CASE.item(), Items.SEGGANESSON.item(),
        Items.OSMIUM_SUPERALLOY.item(), Items.SEGGANESSON.item(), Items.OSMIUM_SUPERALLOY.item()
    }, 5, "&7Boosts the suit's energy production"),
    ELECTRONIC_SPRINGS(9, "Electronic Springs", PotionEffectType.JUMP, 1, ArmorPiece.LEGS, new ItemStack[]{
        Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.ADVANCED_CIRCUIT_BOARD.item(), Items.REINFORCED_SLIMESTEEL.item(),
        Items.REINFORCED_SLIMESTEEL.item(), Items.MODULE_CASE.item(), Items.REINFORCED_SLIMESTEEL.item(),
        Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.BASIC_CIRCUIT_BOARD.item(), Items.REINFORCED_SLIMESTEEL.item()
    }, 3),
    MINI_PISTONS(10, "Mini Pistons", PotionEffectType.FAST_DIGGING, 3, ArmorPiece.CHEST, new ItemStack[]{
        Items.REINFORCED_SLIMESTEEL.item(), new ItemStack(Material.PISTON), Items.REINFORCED_SLIMESTEEL.item(),
        Items.REINFORCED_SLIMESTEEL.item(), Items.MODULE_CASE.item(), Items.REINFORCED_SLIMESTEEL.item(),
        Items.REINFORCED_SLIMESTEEL.item(), new ItemStack(Material.PISTON), Items.REINFORCED_SLIMESTEEL.item()
    }, 3),
    ;
    @Getter
    @Nullable
    private final PotionEffect effect;
    @Getter
    @Nullable
    private final ArmorPiece allowed;
    @Getter
    @Nonnull
    private final SlimefunItemStack item;
    @Nonnull
    private final ItemStack[] recipe;
    @Getter
    private final int power;

    @Getter
    private final int id;

    private static final Map<Integer, Module> cache = new HashMap<>();

    static {
        for (Module module : Module.values()) {
            cache.put(module.id, module);
        }
    }

    Module(int id, @Nonnull String name, @Nullable PotionEffectType effect, int level, @Nullable ArmorPiece allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
        this.id = id;
        this.allowed = allowed;
        this.power = power;
        if (effect != null) {
            this.effect = new PotionEffect(effect, 21, level, false, false, false);
        } else {
            this.effect = null;
        }
        this.recipe = recipe;

        List<String> loreList = new ArrayList<>(Arrays.asList(lore));
        if (lore.length > 0) {
            loreList.add(0, "");
        }
        loreList.add("");
        if (effect != null) {
            loreList.add(String.format("&7Effect: &a%s %d", WordUtils.capitalizeFully(effect.getName().replace('_', ' ')), level + 1));
        }
        loreList.add(allowed == null ? "&7Install anywhere" : "&7Install on " + allowed);
        loreList.add(String.format("&eUses %dJ", power));

        this.item = new SlimefunItemStack(
            this.name(),
            PlayerHead.getItemStack(Heads.MODULE),
            "&6" + name,
            loreList.toArray(new String[0])
        );
    }

    Module(int id, @Nonnull String name, ArmorPiece allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
        this(id, name, null, 0, allowed, recipe, power, lore);
    }

    public static void setup(SlimefunWarfare addon) {
        for (Module module : Module.values()) {
            new ModuleItem(Categories.POWER_SUITS, module.item, RecipeType.ENHANCED_CRAFTING_TABLE, module.recipe, module)
                .register(addon);
        }
    }

    @Nullable
    public static Module getById(int id) {
        return cache.get(id);
    }

    public static class ModuleItem extends SlimefunItem implements NotPlaceable {

        @Getter
        private final Module module;

        public ModuleItem(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Module module) {
            super(category, item, recipeType, recipe);

            this.module = module;
        }
    }
}
