package io.github.seggan.slimefunwarfare.machines;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Location;
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
    }

    @Override
    public void onInteract(Player p, Block b) {
        int mins = ThreadLocalRandom.current().nextInt(10, 31);
        Location l = b.getLocation();
        p.sendMessage("Sending meteor in " + mins + " minutes");
        SlimefunWarfare.inst().runSync(() -> drop(l), (long) mins * 60 * 20);
    }

    private void drop(Location l) {
        Block b = l.getBlock();
        if (!(BlockStorage.check(b) instanceof MeteorAttractor)) return;

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
