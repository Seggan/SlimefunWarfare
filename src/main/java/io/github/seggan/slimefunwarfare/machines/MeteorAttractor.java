package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class MeteorAttractor extends MultiBlockMachine {

    public MeteorAttractor() {
        super(Categories.MACHINES, Items.METEOR_ATTRACTOR, new ItemStack[]{
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.TERFENOL_D_BLOCK, Items.NDFEB_ALLOY_BLOCK, Items.TERFENOL_D_BLOCK
        }, BlockFace.DOWN);

        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return false;
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                MeteorAttractor.this.tick(b);
            }
        });
    }

    @Override
    public void onInteract(Player p, Block b) {
        int perSec = SlimefunPlugin.getTickerTask().getTickRate() / 20;
        int mins = ThreadLocalRandom.current().nextInt(10, 31);
        int ticks = perSec * 60 * mins;
        BlockStorage.addBlockInfo(b, "left", Integer.toString(ticks));
        p.sendMessage("Meteor will fall in a 100x100 area in " + mins + " minutes");
    }

    private void tick(Block b) {
        String data = BlockStorage.getLocationInfo(b.getLocation(), "left");
        if (data == null || data.isEmpty()) return;
        int left = Integer.parseInt(data);

        if (left > 0) {
            BlockStorage.addBlockInfo(b, "left", Integer.toString(--left));
        } else {
            BlockStorage.addBlockInfo(b, "left", "");

            int x = ThreadLocalRandom.current().nextInt(b.getX() - 100, b.getX() + 101);
            int z = ThreadLocalRandom.current().nextInt(b.getZ() - 100, b.getZ() + 101);
            World world = b.getWorld();

            world.createExplosion(x, world.getHighestBlockYAt(x, z), z, 4);

            SlimefunWarfare.inst().runSync(() -> {
                SlimefunItemStack stack = Items.OSMIUM_METEOR;
                if (ThreadLocalRandom.current().nextInt(100) < SlimefunWarfare.inst().getConfig().getInt("space.segganesson-chance", 0, 100)) {
                    stack = Items.SEGGANESSON_METEOR;
                }

                Block landing = world.getHighestBlockAt(x, z);
                landing.setType(stack.getType());
                BlockStorage.addBlockInfo(landing, "id", stack.getItemId());
            }, 2);
        }
    }
}
