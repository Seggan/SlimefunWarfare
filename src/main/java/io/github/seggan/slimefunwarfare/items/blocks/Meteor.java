package io.github.seggan.slimefunwarfare.items.blocks;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Meteor extends SlimefunItem {

    public Meteor(SlimefunItemStack item) {
        super(Categories.RESOURCES, item, RecipeTypes.SPACE, null);

        addItemHandler((BlockBreakHandler) (e, handItem, fortune, drops) -> {
            if (handItem.containsEnchantment(Enchantment.SILK_TOUCH)) {
                drops.add(getItem().clone());
            } else {
                SlimefunItemStack itemStack = getItem().equals(Items.TITANIUM_METEOR) ? Items.TITANIUM_DUST : Items.SEGGANESSON;

                drops.add(itemStack.clone());
                if (fortune == 0) return true;

                for (int i = 0; i < fortune; i++) {
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        drops.add(itemStack.clone());
                    }
                }
            }
            return true;
        });
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return new ArrayList<>();
    }
}
