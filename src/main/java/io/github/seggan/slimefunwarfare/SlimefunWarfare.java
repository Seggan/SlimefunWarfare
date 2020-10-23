package io.github.seggan.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.items.Gun;
import io.github.seggan.slimefunwarfare.machines.BulletFactory;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
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

        new Gun(Items.TEST_GUN, new ItemStack[] {}, 30, 10, 2).register(this);
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
