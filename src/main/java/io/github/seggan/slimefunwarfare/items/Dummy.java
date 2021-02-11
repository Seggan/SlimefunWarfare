package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.items.Melee;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Dummy extends SlimefunItem {

    public static final NamespacedKey KEY = new NamespacedKey(SlimefunWarfare.getInstance(), "dummy");

    public Dummy() {
        super(Categories.GENERAL, Melee.DUMMY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.LEATHER_CHESTPLATE), null, null,
            new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.ARMOR_STAND), null,
            new ItemStack(Material.LEATHER_BOOTS), null, null
        });

        addItemHandler((ItemUseHandler) e -> {
            e.cancel();

            if (!e.getClickedBlock().isPresent()) return;

            Location l = e.getClickedBlock().get().getRelative(e.getClickedFace()).getLocation();

            Husk z = l.getWorld().spawn(l, Husk.class);
            PersistentDataAPI.setString(z, KEY, "DUMMY");

            z.setCustomName("Dummy");
            z.setCustomNameVisible(true);

            z.setAI(false);
            z.setAware(false);

            z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1024);
            z.setHealth(1024);
            z.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
            z.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);

            Player p = e.getPlayer();
            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(p.getInventory().getItem(e.getHand()), true);
            }
        });
    }
}
