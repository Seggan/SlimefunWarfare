package io.github.seggan.slimefunwarfare.listeners;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.regex.Pattern;

public class SpaceListener implements Listener {

    private static final Pattern SPACE_PATTERN = Pattern.compile("_space$");

    @EventHandler
    public void onEntityFallOutOfSpace(EntityDamageEvent e) {
        if (e.getCause() != EntityDamageEvent.DamageCause.VOID) return;

        Entity entity = e.getEntity();
        World world = entity.getWorld();

        String name = world.getName();
        if (!name.endsWith("_space")) return;

        e.setCancelled(true);

        World overworld = Bukkit.getWorld(SPACE_PATTERN.matcher(name).replaceAll(""));
        if (overworld == null) {
            throw new IllegalStateException("Space world " + name + " has no overworld counterpart!");
        }

        Location loc = entity.getLocation();
        PaperLib.teleportAsync(entity, new Location(
            overworld,
            loc.getX(),
            overworld.getMaxHeight() + 10,
            loc.getZ()
        ), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        if (!e.isFlying() && e.getPlayer().getWorld().getName().endsWith("_space")) {
            e.setCancelled(true);
        }
    }
}
