package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.groups.MultiGroup;
import io.github.mooy1.infinitylib.groups.SubGroup;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;

@UtilityClass
public final class Categories {

    public static final ItemGroup GENERAL = new SubGroup(
        "slimefunwarfare_general",
        CustomItemStack.create(Material.DIAMOND_SWORD, "Slimefun Warfare - General")
    );

    public static final ItemGroup MACHINES = new SubGroup(
        "slimefunwarfare_machines",
        CustomItemStack.create(Material.STONECUTTER, "Slimefun Warfare - Machines")
    );

    public static final ItemGroup GUNS = new SubGroup(
        "slimefunwarfare_guns",
        CustomItemStack.create(Material.CROSSBOW, "Slimefun Warfare - Guns")
    );

    public static final ItemGroup MELEE = new SubGroup(
        "slimefunwarfare_melee",
        CustomItemStack.create(Material.IRON_AXE, "Slimefun Warfare - Melee Weapons")
    );

    public static final ItemGroup EXPLOSIVES = new SubGroup(
        "slimefunwarfare_explosives",
        CustomItemStack.create(Material.TNT, "Slimefun Warfare - Explosives")
    );

    public static final ItemGroup RESOURCES = new SubGroup(
        "slimefunwarfare_resources",
        CustomItemStack.create(Material.IRON_ORE, "Slimefun Warfare - Resources")
    );

    public static final ItemGroup POWER_SUITS = new SubGroup(
        "slimefunwarfare_power_suits",
        CustomItemStack.create(PlayerHead.getItemStack(Heads.SUIT_HELMET), "Slimefun Warfare - Power Suits")
    );

    private static final ItemGroup MAIN = new MultiGroup(
        "slimefunwarfare",
        CustomItemStack.create(Material.DIAMOND_SWORD, "Slimefun Warfare"),
        GENERAL, MACHINES, GUNS, MELEE, EXPLOSIVES, RESOURCES, POWER_SUITS
    );

    public static void setup(SlimefunWarfare addon) {
        MAIN.register(addon);
    }
}
