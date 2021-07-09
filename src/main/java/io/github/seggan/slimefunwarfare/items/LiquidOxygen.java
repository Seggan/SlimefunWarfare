package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.Util;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class LiquidOxygen extends SimpleSlimefunItem<ItemDropHandler> {

    public LiquidOxygen() {
        super(Categories.EXPLOSIVES, Items.LIQUID_OXYGEN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.LIQUID_AIR, Items.LIQUID_AIR, Items.LIQUID_AIR,
            Items.LIQUID_AIR, Items.LIQUID_AIR, Items.LIQUID_AIR,
            Items.LIQUID_AIR, Items.LIQUID_AIR, Items.LIQUID_AIR
        }, new SlimefunItemStack(Items.LIQUID_OXYGEN, 2));
    }

    @Nonnull
    @Override
    public ItemDropHandler getItemHandler() {
        return (e, p, item) -> {
            if (isItem(item.getItemStack())) {
                Block b = item.getLocation().getBlock();
                b.setType(Material.FIRE);

                for (BlockFace face : Util.SURROUNDING_FACES) {
                    Block rel = b.getRelative(face);
                    if (rel.isPassable() && !rel.isLiquid()) {
                        rel.setType(Material.FIRE);
                    }
                }
            }

            return true;
        };
    }
}
