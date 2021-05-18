package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.seggan.slimefunwarfare.lists.items.Explosives;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FLOX extends SlimefunItem implements NotPlaceable {

    public FLOX() {
        super(Categories.EXPLOSIVES, Explosives.FLOX, RecipeTypes.EXPLOSIVE_SYNTHESIZER, new ItemStack[]{
            SlimefunItems.FUEL_BUCKET, Explosives.LIQUID_OXYGEN, null,
            null, null, null,
            null, null, null
        });

        addItemHandler((ItemUseHandler) e -> {
            e.cancel();

            Player p = e.getPlayer();
            Location loc = p.getLocation();
            String worldName = p.getWorld().getName();
            if (worldName.endsWith("_space")) {
                PaperLib.teleportAsync(p, Bukkit.getWorld(worldName.replace("_space", ""))
                    .getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()).getRelative(BlockFace.UP).getLocation());
                p.setAllowFlight(false);
                p.setFlying(false);
            } else {
                PaperLib.teleportAsync(p, new Location(
                    Bukkit.getWorld(worldName + "_space"),
                    loc.getX(),
                    100,
                    loc.getZ()
                ));
                p.setAllowFlight(true);
                p.setFlying(true);
            }

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(p.getInventory().getItem(e.getHand()), true);
            }
        });
    }
}
