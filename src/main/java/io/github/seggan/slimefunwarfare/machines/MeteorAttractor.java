package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;

public class MeteorAttractor extends SimpleSlimefunItem<BlockUseHandler> {

    public MeteorAttractor() {
        super(Categories.MACHINES, Items.METEOR_ATTRACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.TERFENOL_D_BLOCK, Items.NDFEB_ALLOY_BLOCK, Items.TERFENOL_D_BLOCK
        });
    }

    private void drop(Location l) {
        Block b = l.getBlock();
        if (!Objects.equals(BlockStorage.checkID(b), Items.NDFEB_ALLOY_BLOCK.getItemId())) return;

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

    @Nonnull
    @Override
    public BlockUseHandler getItemHandler() {
        return (b) -> {
            int mins = ThreadLocalRandom.current().nextInt(10, 31);
            Location l = b.getClickedBlock().get().getLocation();
            b.getPlayer().sendMessage("Sending meteor in " + mins + " minutes");
            SlimefunWarfare.inst().runSync(() -> drop(l),  mins * 60 * 20L);
        };
    }
}
