package io.github.seggan.slimefunwarfare.lists;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;

public final class Explosives {

    private Explosives() {}

    public static final SlimefunItemStack NITROGEN_TRIIODIDE = new SlimefunItemStack(
        "NITROGEN_TRIIODIDE",
        Material.PURPLE_DYE,
        "&5Nitrogen Triiodide",
        "&7A material for grenades"
    );

    public static final SlimefunItemStack AZIDOAZIDE_AZIDE = new SlimefunItemStack(
        "AZIDOAZIDE_AZIDE",
        Material.SUGAR,
        "&eAzidoazide Azide",
        "&7A material for grenades"
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

    public static final SlimefunItemStack C2N14_GRENADE = new SlimefunItemStack(
        "AZIDOAZIDE_AZIDE_GRENADE",
        Material.SNOWBALL,
        "&7Grenade",
        "&7Contents: Azidoazide azide"
    );
}
