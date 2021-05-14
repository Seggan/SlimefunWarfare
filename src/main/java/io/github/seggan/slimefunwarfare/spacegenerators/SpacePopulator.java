package io.github.seggan.slimefunwarfare.spacegenerators;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import javax.annotation.Nonnull;
import java.util.Random;

class SpacePopulator extends BlockPopulator {

    @Override
    public void populate(@Nonnull World world, @Nonnull Random random, @Nonnull Chunk chunk) {
        if (BlockStorage.getStorage(world) == null) {
            new BlockStorage(world);
        }

        for (int i = 0; i < SlimefunWarfare.inst().getConfig().getInt("space.meteors-per-chunk", 1, 64); i++) {
            if (random.nextDouble() < SlimefunWarfare.inst().getConfig().getInt("space.meteor-spawn-rate", 1, 100) / 100.0) {
                int randx = random.nextInt(16);
                int randy = random.nextInt(world.getMaxHeight());
                int randz = random.nextInt(16);

                Block block = chunk.getBlock(randx, randy, randz);

                if (random.nextDouble() < SlimefunWarfare.inst().getConfig().getInt("space.segganesson-chance", 1, 100) / 100.0) {
                    block.setType(Items.SEGGANESSON_METEOR.getType());
                    BlockStorage.addBlockInfo(block.getLocation(), "id",
                        Items.SEGGANESSON_METEOR.getItemId(), true);
                } else {
                    block.setType(Items.OSMIUM_METEOR.getType());
                    BlockStorage.addBlockInfo(block.getLocation(), "id",
                        Items.OSMIUM_METEOR.getItemId(), true);
                }
            }
        }
    }
}
