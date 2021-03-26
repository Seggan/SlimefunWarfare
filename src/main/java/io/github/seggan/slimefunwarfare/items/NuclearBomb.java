package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NuclearBomb extends SlimefunItem {

    private final Map<String, String> metadata = new HashMap<>();

    public NuclearBomb(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(NuclearBomb.this.getHandler());
    }

    public float getExplosionPower() {
        return 100;
    }

    private BlockUseHandler getHandler() {
        return e -> {
            e.cancel();
            Optional<Block> optionalBlock = e.getClickedBlock();
            if (!optionalBlock.isPresent()) {
                return;
            }
            Block b = optionalBlock.get();
            if (e.getPlayer().getInventory().getItem(e.getHand()).getType() == Material.FLINT_AND_STEEL) {
                b.setType(Material.AIR);
                TNTPrimed tnt = b.getWorld().spawn(b.getLocation(), TNTPrimed.class);
                tnt.setFuseTicks(100);
                tnt.setMetadata("isNuke", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    true
                ));
                tnt.setMetadata("rad", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    getExplosionPower()
                ));

                // To prevent ghost blocks
                BlockStorage.clearBlockInfo(b);
            }
        };
    }
}
