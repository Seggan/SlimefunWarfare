package io.github.seggan.slimefunwarfare.spacegenerators;

import io.github.seggan.slimefunwarfare.ConfigSettings;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

class SpacePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (BlockStorage.getStorage(world) == null) {
            new BlockStorage(world);
        }
        this.populate(world, random, chunk, SlimefunWarfare.getConfigSettings());
    }

    private void populate(World world, Random random, Chunk chunk, ConfigSettings config) {
        for (int i = 0; i < config.getMeteorsPerChunk(); i++) {
            if (random.nextDouble() < config.getMeteorSpawnRate() / 100.0) {
                int randx = random.nextInt(16);
                int randy = random.nextInt(world.getMaxHeight());
                int randz = random.nextInt(16);

                Block block = chunk.getBlock(randx, randy, randz);

                if (random.nextDouble() < config.getSegganessonChance() / 100.0) {
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
