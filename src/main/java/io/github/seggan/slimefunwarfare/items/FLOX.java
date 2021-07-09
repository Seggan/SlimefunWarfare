package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FLOX extends SlimefunItem implements NotPlaceable {

    public FLOX() {
        super(Categories.EXPLOSIVES, Items.FLOX, RecipeTypes.EXPLOSIVE_SYNTHESIZER, new ItemStack[]{
            SlimefunItems.FUEL_BUCKET, Items.LIQUID_OXYGEN, null,
            null, null, null,
            null, null, null
        });

        addItemHandler((ItemUseHandler) e -> {
            e.cancel();

            Player p = e.getPlayer();
            p.getWorld().createExplosion(p.getLocation(), 6);

            ItemUtils.consumeItem(e.getItem(), false);
        });
    }
}
