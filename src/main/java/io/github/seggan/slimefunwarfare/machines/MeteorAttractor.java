package io.github.seggan.slimefunwarfare.machines;

import io.github.mooy1.infinitylib.configuration.AddonConfig;
import io.github.mooy1.infinitylib.players.CoolDownMap;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;

public class MeteorAttractor extends SimpleSlimefunItem<BlockUseHandler> {

    private static final CoolDownMap cooldowns = new CoolDownMap(1000L * 60L *
        SlimefunWarfare.inst().getConfig().getInt("space.attractor-cooldown", 1)
    );

    public MeteorAttractor() {
        super(Categories.MACHINES, Items.METEOR_ATTRACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.TERFENOL_D_BLOCK, Items.NDFEB_ALLOY_BLOCK, Items.TERFENOL_D_BLOCK
        });
    }

    private void drop(Location l) {
        Block b = l.getBlock();
        if (!(BlockStorage.check(l) instanceof MeteorAttractor)) return;

        AddonConfig config = SlimefunWarfare.inst().getConfig();

        int radius = config.getInt("space.meteor-drop-radius", 100);

        int x = ThreadLocalRandom.current().nextInt(b.getX() - radius, b.getX() + radius + 1);
        int z = ThreadLocalRandom.current().nextInt(b.getZ() - radius, b.getZ() + radius + 1);
        World world = b.getWorld();

        world.createExplosion(x, world.getHighestBlockYAt(x, z), z, 4);

        SlimefunWarfare.inst().runSync(() -> {
            SlimefunItemStack stack = Items.OSMIUM_METEOR;
            if (ThreadLocalRandom.current().nextInt(100) < config.getInt("space.segganesson-chance", 0, 100)) {
                stack = Items.SEGGANESSON_METEOR;
            }

            Block landing = world.getHighestBlockAt(x, z);
            landing.setType(stack.getType());
            BlockStorage.addBlockInfo(landing, "id", stack.getItemId());
        }, 2);
    }

    @Nonnull
    @Override
    public BlockUseHandler getItemHandler() {
        return (b) -> {
            AddonConfig config = SlimefunWarfare.inst().getConfig();
            if (cooldowns.check(b.getPlayer().getUniqueId())) {
                cooldowns.reset(b.getPlayer().getUniqueId());

                int mins = ThreadLocalRandom.current().nextInt(
                    config.getInt("space.meteor-min-time", 10),
                    config.getInt("space.meteor-max-time", 30) + 1
                );

                Location l = b.getClickedBlock().get().getLocation();
                b.getPlayer().sendMessage("Sending meteor in " + mins + " minutes");
                SlimefunWarfare.inst().runSync(() -> drop(l), mins * 60 * 20L);
            } else {
                b.getPlayer().sendMessage(ChatColor.RED + "The Meteor Attractor has a "
                    + config.getInt("space.space.attractor-cooldown", 1) +
                    " minute cooldown"
                );
            }
        };
    }
}
