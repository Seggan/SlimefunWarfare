package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Grenade extends SlimefunItem {

    private final SlimefunItemStack chemical;

    public Grenade(SlimefunItemStack item, SlimefunItemStack chemical) {
        super(Items.sfwarfareExplosivesCategory, item, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            item, chemical, null,
            null, null, null,
            null, null, null
        });

        this.chemical = chemical;
        addItemHandler(onLaunch());
    }

    private ItemUseHandler onLaunch() {
        return e -> {
            e.cancel();
            Snowball snowball = e.getPlayer().launchProjectile(Snowball.class);
            snowball.setMetadata("effect", new FixedMetadataValue(
                SlimefunWarfare.getInstance(),
                chemical.getItemId()
            ));
        };
    }
}
