package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.core.PluginUtils;
import io.github.mooy1.infinitylib.slimefun.utils.MultiCategory;
import io.github.mooy1.infinitylib.slimefun.utils.SubCategory;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import org.bukkit.Material;

@UtilityClass
public final class Categories {

    public static final Category GENERAL = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_general"),
        new CustomItem(Material.DIAMOND_SWORD, "Slimefun Warfare - General")
    );

    public static final Category GUNS = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_guns"),
        new CustomItem(Material.CROSSBOW, "Slimefun Warfare - Guns")
    );

    public static final Category MELEE = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_melee"),
        new CustomItem(Material.IRON_AXE, "Slimefun Warfare - Melee Weapons")
    );

    public static final Category EXPLOSIVES = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_explosives"),
        new CustomItem(Material.TNT, "Slimefun Warfare - Explosives")
    );

    public static final Category RESOURCES = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_resources"),
        new CustomItem(Material.IRON_ORE, "Slimefun Warfare - Resources")
    );

    public static final Category POWER_SUITS = new SubCategory(
        PluginUtils.getKey("slimefunwarfare_power_suits"),
        new CustomItem(SkullItem.fromBase64(Heads.SUIT_HELMET), "Slimefun Warfare - Power Suits")
    );

    public static final Category MAIN = new MultiCategory(
        PluginUtils.getKey("slimefunwarfare"),
        new CustomItem(Material.DIAMOND_SWORD, "Slimefun Warfare"),
        GENERAL, GUNS, MELEE, EXPLOSIVES, RESOURCES, POWER_SUITS
    );

    public static void setup(SlimefunWarfare addon) {
        MAIN.register(addon);
    }
}
