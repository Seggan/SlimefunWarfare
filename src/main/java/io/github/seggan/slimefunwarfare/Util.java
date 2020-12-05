package io.github.seggan.slimefunwarfare;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Set<Material> deserializeMaterialSet(String s) {
        JSONArray jsonArray = new JSONArray(s);
        Set<Material> materialSet = new HashSet<>();

        for (Object string : jsonArray) {
            assert string instanceof String;
            materialSet.add(Material.valueOf((String) string));
        }

        return materialSet;
    }

    public static String serializeMaterialSet(Set<Material> s) {
        Set<String> stringSet = new HashSet<>();
        for (Material material : s) {
            stringSet.add(material.toString());
        }

        return new JSONArray(stringSet).toString();
    }

    public static List<Block> getBlocksAroundCenter(Location loc, int radius) {
        ArrayList<Block> blocks = new ArrayList<>();

        int radiusSquared = radius * radius;

        for (int x = (loc.getBlockX() - radius); x <= (loc.getBlockX() + radius); x++) {
            for (int y = (loc.getBlockY() - radius); y <= (loc.getBlockY() + radius); y++) {
                for (int z = (loc.getBlockZ() - radius); z <= (loc.getBlockZ() + radius); z++) {
                    Location l = new Location(loc.getWorld(), x, y, z);
                    if (l.distanceSquared(loc) <= radiusSquared) {
                        blocks.add(l.getBlock());
                    }
                }
            }
        }

        return blocks;
    }
}
