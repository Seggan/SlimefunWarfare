package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.Items;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SlimesteelIngot extends SlimefunItem {
    public SlimesteelIngot() {
        super(Items.sfwarfareCategory, Items.SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, new ItemStack(Material.SLIME_BALL, 1), null,
                null, null, null,
                null, null, null
        });
    }
}
