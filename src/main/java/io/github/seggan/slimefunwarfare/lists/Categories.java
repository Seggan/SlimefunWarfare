package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.groups.MultiGroup;
import io.github.mooy1.infinitylib.groups.SubGroup;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import org.bukkit.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Categories {

    public static final ItemGroup GENERAL = new SubGroup(
        "slimefunwarfare_general",
        new CustomItemStack(Material.DIAMOND_SWORD, "Slimefun Warfare - General")
    );

    public static final ItemGroup MACHINES = new SubGroup(
        "slimefunwarfare_machines",
        new CustomItemStack(Material.STONECUTTER, "Slimefun Warfare - Machines")
    );

    public static final ItemGroup GUNS = new SubGroup(
        "slimefunwarfare_guns",
        new CustomItemStack(Material.CROSSBOW, "Slimefun Warfare - Guns")
    );

    public static final ItemGroup MELEE = new SubGroup(
        "slimefunwarfare_melee",
        new CustomItemStack(Material.IRON_AXE, "Slimefun Warfare - Melee Weapons")
    );

    public static final ItemGroup EXPLOSIVES = new SubGroup(
        "slimefunwarfare_explosives",
        new CustomItemStack(Material.TNT, "Slimefun Warfare - Explosives")
    );

    public static final ItemGroup RESOURCES = new SubGroup(
        "slimefunwarfare_resources",
        new CustomItemStack(Material.IRON_ORE, "Slimefun Warfare - Resources")
    );

    public static final ItemGroup POWER_SUITS = new SubGroup(
        "slimefunwarfare_power_suits",
        new CustomItemStack(PlayerHead.getItemStack(Heads.SUIT_HELMET), "Slimefun Warfare - Power Suits")
    );

    private static final ItemGroup MAIN = new MultiGroup(
        "slimefunwarfare",
        new CustomItemStack(Material.DIAMOND_SWORD, "Slimefun Warfare"),
        GENERAL, MACHINES, GUNS, MELEE, EXPLOSIVES, RESOURCES, POWER_SUITS
    );

    public static void setup(SlimefunWarfare addon) {
        MAIN.register(addon);
    }
}
