package io.github.seggan.slimefunwarfare.lists;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;

public final class Guns {

    public static final SlimefunItemStack PISTOL = new SlimefunItemStack(
        "GUN_PISTOL",
        Material.CROSSBOW,
        "&7Pistol",
        "&7A short range gun",
        "&7that reloads quickly.",
        "&7Useful for close combat.",
        "&cRange: 7",
        "&cDamage: 2 hearts",
        "&cCooldown: 0.75 seconds"
    );

    public static final SlimefunItemStack REVOLVER = new SlimefunItemStack(
        "GUN_REVOLVER",
        Material.CROSSBOW,
        "&7Revolver",
        "&7A short range gun",
        "&7that reloads quickly.",
        "&7Useful for close combat.",
        "&cRange: 10",
        "&cDamage: 3 hearts",
        "&cCooldown: 0.5 seconds"
    );

    public static final SlimefunItemStack MACHINE_GUN = new SlimefunItemStack(
        "GUN_MACHINE_GUN",
        Material.CROSSBOW,
        "&7Machine Gun",
        "&7Pew pew pew",
        "&cRange: 30",
        "&cMinimum Range: 5",
        "&cDamage: 3 hearts",
        "&cCooldown: 0.15 seconds"
    );
}
