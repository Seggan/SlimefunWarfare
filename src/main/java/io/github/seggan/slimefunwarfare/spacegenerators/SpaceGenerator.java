package io.github.seggan.slimefunwarfare.spacegenerators;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class SpaceGenerator extends ChunkGenerator {

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        return createChunkData(world);
    }

    @Nonnull
    @Override
    public List<BlockPopulator> getDefaultPopulators(@Nonnull World world) {
        return Collections.singletonList(new SpacePopulator());
    }

}
