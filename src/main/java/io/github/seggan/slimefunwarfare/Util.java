package io.github.seggan.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Util {

    public static final BlockFace[] SURROUNDING_FACES = new BlockFace[] {
        BlockFace.NORTH,
        BlockFace.NORTH_EAST,
        BlockFace.EAST,
        BlockFace.SOUTH_EAST,
        BlockFace.SOUTH,
        BlockFace.SOUTH_WEST,
        BlockFace.WEST,
        BlockFace.NORTH_WEST
    };

    private Util() {}

    @Nonnull
    public static Location deserializeLocation(@Nonnull String s) {
        if (s.trim().equals("")) {
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

    @Nonnull
    public static String serializeLocation(@Nonnull Location loc) {
        return loc.getWorld().getName() +
            ":" + loc.getX() +
            ":" + loc.getY() +
            ":" + loc.getZ();
    }

    public static void ifPowerSuit(@Nullable ItemStack stack, @Nonnull Consumer<PowerSuit> callback) {
        ifPowerSuit(stack, callback, () -> {});
    }

    public static void ifPowerSuit(@Nullable ItemStack stack, @Nonnull Consumer<PowerSuit> callback, @Nonnull Runnable orElse) {
        SlimefunItem sfitem = SlimefunItem.getByItem(stack);
        if (sfitem instanceof PowerSuit) {
            callback.accept((PowerSuit) sfitem);
        } else {
            orElse.run();
        }
    }
}
