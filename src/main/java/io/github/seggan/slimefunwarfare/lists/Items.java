package io.github.seggan.slimefunwarfare.lists;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class Items {
    public static final Category sfwarfareCategory = new Category(
            new NamespacedKey(SlimefunWarfare.getInstance(), "slimefunwarfare"),
            new CustomItem(Material.DIAMOND_SWORD, "Slimefun Warfare")
    );
    public static final Category sfwarfareGunsCategory = new Category(
            new NamespacedKey(SlimefunWarfare.getInstance(), "slimefunwarfare_guns"),
            new CustomItem(Material.CROSSBOW, "Slimefun Warfare - Guns")
    );

    public static final SlimefunItemStack SLIMESTEEL = new SlimefunItemStack(
            "SLIMESTEEL_INGOT",
            Material.IRON_INGOT,
            "&aSlimesteel Ingot",
            "",
            "Hard but elastic, this alloy",
            "is perfect for all sorts",
            "of applications"
    );

    public static final SlimefunItemStack BULLET_FACTORY  = new SlimefunItemStack(
        "BULLET_FACTORY",
        Material.SMOKER,
        "&7Bullet Factory",
        LoreBuilder.powerPerSecond(16),
        LoreBuilder.powerBuffer(32),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack LEAD_BULLET = new SlimefunItemStack(
        "LEAD_BULLET",
        Material.IRON_NUGGET,
        "&7Lead Bullet",
        "&7x1 damage"
    );

    public static final SlimefunItemStack DU_BULLET = new SlimefunItemStack(
        "DU_BULLET",
        Material.IRON_NUGGET,
        "&aDU Bullet",
        "&7Sets hit entities on fire",
        "&7x1.5 damage"
    );

    public static final SlimefunItemStack GOLD_BULLET = new SlimefunItemStack(
        "GOLD_BULLET",
        Material.GOLD_NUGGET,
        "&6Gold Bullet",
        "&7x2 damage"
    );

    public static final SlimefunItemStack GUN_CASE = new SlimefunItemStack(
        "GUN_CASE",
        Material.CROSSBOW,
        "&7Gun Case",
        "&7The base of all guns"
    );
}
