package io.github.seggan.slimefunwarfare.lists;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public final class Constants {

    private Constants() {}

    public static final Set<Material> TNT_WEAK = new HashSet<>();
    public static final Set<Material> INDESTRUCTIBLE = new HashSet<>();

    static {
        for (Material mat : Material.values()) {
            if (mat.getBlastResistance() < 15.5) {
                TNT_WEAK.add(mat);
            }
            if (mat.getHardness() > 100) {
                INDESTRUCTIBLE.add(mat);
            }
        }
    }
}
