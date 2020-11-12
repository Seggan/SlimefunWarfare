package io.github.seggan.slimefunwarfare.lists;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;

public final class Explosives {

    private Explosives() {}

    public static final SlimefunItemStack NITROGEN_TRIIODIDE = new SlimefunItemStack(
        "NITROGEN_TRIIODIDE",
        Material.PURPLE_DYE,
        "&5Nitrogen Triiodide",
        "&A material for grenades"
    );

    public static final SlimefunItemStack EMPTY_GRENADE = new SlimefunItemStack(
        "GRENADE",
        Material.SNOWBALL,
        "&7Grenade",
        "&7Contents: none"
    );

    public static final SlimefunItemStack NI3_GRENADE = new SlimefunItemStack(
        "NITROGEN_TRIIODIDE_GRENADE",
        Material.SNOWBALL,
        "&7Grenade",
        "&7Contents: Nitrogen Triiodide"
    );
}
