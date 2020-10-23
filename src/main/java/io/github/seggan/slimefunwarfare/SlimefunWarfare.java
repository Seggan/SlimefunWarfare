package io.github.seggan.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.items.Gun;
import io.github.seggan.slimefunwarfare.items.SlimesteelIngot;
import io.github.seggan.slimefunwarfare.lists.Guns;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.machines.BulletFactory;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimefunWarfare extends JavaPlugin implements SlimefunAddon {

    @Getter
    private static SlimefunWarfare instance = null;

    @Override
    public void onEnable() {
        getLogger().info("Slimefun Warfare enabled.");

        getServer().getPluginManager().registerEvents(new BulletListener(), this);

        instance = this;

        new BulletFactory().register(this);
        new Bullet(Items.LEAD_BULLET, SlimefunItems.LEAD_INGOT, 1).register(this);
        new Bullet(Items.DU_BULLET, SlimefunItems.SMALL_URANIUM, 1.5).register(this);
        new Bullet(Items.GOLD_BULLET, SlimefunItems.GOLD_20K, 2).register(this);

        new SlimesteelIngot().register(this);

        new SlimefunItem(Items.sfwarfareCategory, Items.GUN_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL, new ItemStack(Material.GUNPOWDER), Items.SLIMESTEEL,
            Items.SLIMESTEEL, new ItemStack(Material.FLINT_AND_STEEL), Items.SLIMESTEEL,
            SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.CROSSBOW), SlimefunItems.PLASTIC_SHEET
        });

        new Gun(Guns.PISTOL, new ItemStack[] {}, 7, 3, 0.75);
    }

    @Override
    public void onDisable() {
        getLogger().info("Slimefun Warfare disabled.");
    }

    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public String getBugTrackerURL() {
        return null;
    }
}
