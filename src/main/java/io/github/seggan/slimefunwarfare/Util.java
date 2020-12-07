package io.github.seggan.slimefunwarfare;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public final class Util {

    private Util() {}

    @NonNull
    public static Location deserializeLocation(@NonNull String s) {
        if (s == null || s.trim().equals("")) {
            throw new IllegalArgumentException("Invalid location deserialization parameter, got " + s);
        }

        String[] parts = s.split(":");

        if (parts.length == 4) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            return new Location(w, x, y, z);
        }

        throw new IllegalArgumentException("Invalid location deserialization parameter, got " + s);
    }

    @NonNull
    public static String serializeLocation(@NonNull Location loc) {
        return loc.getWorld().getName() +
            ":" + loc.getX() +
            ":" + loc.getY() +
            ":" + loc.getZ();
    }
}
