package io.github.seggan.slimefunwarfare.items.powersuits;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Heads;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Module {
    NANOFIBER_CUSHION(0, "Nanofober Cushion", new ArmorPiece[]{ArmorPiece.FEET}, new ItemStack[]{
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.FEATHER),
        Items.REINFORCED_SLIMESTEEL, Items.MODULE_CASE, Items.REINFORCED_SLIMESTEEL,
        new ItemStack(Material.FEATHER), Items.REINFORCED_SLIMESTEEL, new ItemStack(Material.FEATHER),
    }, 5, "&7This module keeps you from", "&7taking fall damage"),
    MINI_JETS(1, "Mini Jets", new ArmorPiece[]{ArmorPiece.FEET}, new ItemStack[]{
        Items.OSMIUM_SUPERALLOY, Items.REINFORCED_SLIMESTEEL, Items.OSMIUM_SUPERALLOY,
        SlimefunItems.STEEL_THRUSTER, Items.MODULE_CASE, SlimefunItems.STEEL_THRUSTER,
        Items.OSMIUM_SUPERALLOY, Items.REINFORCED_SLIMESTEEL, Items.OSMIUM_SUPERALLOY
    }, 8),
    LIFE_SUPPORT(2, "Life Support Sysc d etems", PotionEffectType.REGENERATION, 2, new ArmorPiece[]{ArmorPiece.CHEST}, new ItemStack[]{
        SlimefunItems.ESSENCE_OF_AFTERLIFE, Items.SLIMESTEEL, SlimefunItems.ESSENCE_OF_AFTERLIFE,
        Items.SLIMESTEEL, Items.MODULE_CASE, Items.SLIMESTEEL,
        SlimefunItems.ESSENCE_OF_AFTERLIFE, Items.SLIMESTEEL, SlimefunItems.ESSENCE_OF_AFTERLIFE
    }, 1),
    HEAT_SINKS(3, "Heat Sinks", PotionEffectType.FIRE_RESISTANCE, 0, new ArmorPiece[]{ArmorPiece.CHEST}, new ItemStack[]{
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT, new ItemStack(Material.MAGMA_CREAM),
        Items.OSMIUM_INGOT, Items.MODULE_CASE, Items.OSMIUM_INGOT,
        new ItemStack(Material.MAGMA_CREAM), Items.OSMIUM_INGOT, new ItemStack(Material.MAGMA_CREAM)
    }, 1),
    HYDRAULICS(4, "Integrated Hydraulics", PotionEffectType.INCREASE_DAMAGE, 1, new ArmorPiece[]{ArmorPiece.CHEST}, new ItemStack[]{
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE,
        SlimefunItems.FUEL_BUCKET, Items.MODULE_CASE, SlimefunItems.FUEL_BUCKET,
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE
    }, 1, "&7These integrated hydraulics make", "&7you much stronger"),
    REACTION_WHEELS(5, "Reaction Wheels", new ArmorPiece[]{ArmorPiece.CHEST}, new ItemStack[]{
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE,
        SlimefunItems.FUEL_BUCKET, Items.MODULE_CASE, SlimefunItems.FUEL_BUCKET,
        SlimefunItems.REINFORCED_PLATE, Items.OSMIUM_SUPERALLOY, SlimefunItems.REINFORCED_PLATE
    }, 1)

    ;
    @Getter
    @Nullable
    private final PotionEffect effect;
    @Getter
    @Nonnull
    private final ArmorPiece[] allowed;
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

    Module(int id, @Nonnull String name, @Nullable PotionEffectType effect, int level, @Nonnull ArmorPiece[] allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
        this.id = id;
        this.allowed = allowed;
        this.power = power;
        if (effect != null) {
            this.effect = new PotionEffect(effect, 20, level, false, false, false);
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
            loreList.add(String.format("&7Effect: %s %d", effect.getName(), level + 1));
        }
        String[] pieces = new String[allowed.length];
        for (int i = 0; i < allowed.length; i++) {
            pieces[i] = allowed[i].toString();
        }
        loreList.add("&7Install on " + String.join(", ", pieces));
        loreList.add(String.format("&eUses %dJ", power));

        this.item = new SlimefunItemStack(
            this.name(),
            Heads.MODULE,
            "&6" + name,
            loreList.toArray(new String[0])
        );
    }

    Module(int id, @Nonnull String name, ArmorPiece[] allowed, @Nonnull ItemStack[] recipe, int power, @Nonnull String... lore) {
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

        public ModuleItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Module module) {
            super(category, item, recipeType, recipe);

            this.module = module;
        }
    }
}
