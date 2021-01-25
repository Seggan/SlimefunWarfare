package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Explosives;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;

public class Grenade extends SlimefunItem {

    private final SlimefunItemStack chemical;

    public Grenade(SlimefunItemStack chemical) {
        super(Categories.EXPLOSIVES, new SlimefunItemStack(
            chemical.getItemId() + "_GRENADE",
            Material.SNOWBALL,
            "&7Chemical Grenade",
            "&7Contents: " + ChatUtils.removeColorCodes(chemical.getDisplayName())
        ), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Explosives.EMPTY_GRENADE, chemical, null,
            null, null, null,
            null, null, null
        });

        this.chemical = chemical;
        addItemHandler(onLaunch());
    }

    private ItemUseHandler onLaunch() {
        return e -> {
            PlayerInventory inv = e.getPlayer().getInventory();
            ItemStack item = inv.getItemInMainHand();
            if (SlimefunItem.getByItem(item) instanceof Grenade ) {
                e.cancel();
                Snowball snowball = e.getPlayer().launchProjectile(Snowball.class);
                snowball.setMetadata("effect", new FixedMetadataValue(
                    SlimefunWarfare.getInstance(),
                    chemical.getItemId()
                ));
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    item.setAmount(item.getAmount() - 1);
                    inv.setItemInMainHand(item);
                }
            }
        };
    }
}
