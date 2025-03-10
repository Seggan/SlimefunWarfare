package io.github.seggan.slimefunwarfare.items.blocks;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Meteor extends SlimefunItem {

    public Meteor(SlimefunItemStack item) {
        super(Categories.RESOURCES, item, RecipeTypes.METEOR_ATTRACTOR, new ItemStack[9]);

        addItemHandler(new BlockBreakHandler(true, false) {
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> drops) {
                if (itemStack.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    drops.add(getItem().clone());
                } else {
                    SlimefunItemStack stack = getItem().equals(Items.OSMIUM_METEOR.item()) ? Items.OSMIUM_DUST : Items.SEGGANESSON;

                    drops.add(stack.item().clone());
                    int fortune = itemStack.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                    if (fortune == 0) return;

                    for (int i = 0; i < fortune; i++) {
                        if (ThreadLocalRandom.current().nextBoolean()) {
                            drops.add(stack.item().clone());
                        }
                    }
                }
            }
        });
    }

    @Nonnull
    @Override
    public Collection<ItemStack> getDrops() {
        return new ArrayList<>();
    }
}
