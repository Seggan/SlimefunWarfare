package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.Util;
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
import java.util.Set;

public abstract class ABetterExplosive extends SlimefunItem {

    private final Map<String, String> metadata = new HashMap<>();

    public ABetterExplosive(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(getHandler());
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
                tnt.setFuseTicks(getFuseTicks());
                tnt.setMetadata("isBetterExplosive", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    true
                ));
                tnt.setMetadata("rad", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    getExplosionPower()
                ));
                tnt.setMetadata("explodableBlocks", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    Util.serializeMaterialSet(getExplodableStrongBlocks())
                ));

                // To prevent ghost blocks
                BlockStorage.clearBlockInfo(b);
            }
        };
    }

    public abstract float getExplosionPower();

    public abstract int getFuseTicks();

    public abstract Set<Material> getExplodableStrongBlocks();
}
