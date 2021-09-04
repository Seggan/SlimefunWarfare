package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
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
            "&fChemical Grenade",
            "",
            "&7Contents: " + ChatUtils.removeColorCodes(chemical.getDisplayName())
        ), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.EMPTY_GRENADE, chemical, null,
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
                    SlimefunWarfare.inst(),
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
