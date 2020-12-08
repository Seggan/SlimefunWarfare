package io.github.seggan.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.Gun;
import io.github.seggan.slimefunwarfare.listeners.BetterExplosiveListener;
import io.github.seggan.slimefunwarfare.listeners.BulletListener;
import io.github.seggan.slimefunwarfare.listeners.ConcreteListener;
import io.github.seggan.slimefunwarfare.listeners.GrenadeListener;
import io.github.seggan.slimefunwarfare.listeners.PyroListener;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class SlimefunWarfare extends JavaPlugin implements SlimefunAddon {

    @Getter
    private static SlimefunWarfare instance = null;

    @Getter
    private static ConfigSettings configSettings = null;

    @Override
    public void onEnable() {
        getLogger().info("Slimefun Warfare enabled.");

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BulletListener(), this);
        getServer().getPluginManager().registerEvents(new PyroListener(), this);
        getServer().getPluginManager().registerEvents(new GrenadeListener(), this);
        getServer().getPluginManager().registerEvents(new ConcreteListener(), this);
        getServer().getPluginManager().registerEvents(new BetterExplosiveListener(), this);

        instance = this;

        new Metrics(this, 9227);

        if (getConfig().getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Seggan/SlimefunWarfare/master").start();
        }

        configSettings = new ConfigSettings(this);
        configSettings.loadConfig();

        Setup.setupItems(this);
        Setup.setupBullets(this);
        Setup.setupGuns(this);
        Setup.setupExplosives(this);

        if (configSettings.isAutoshoot()) {
            // Gun autoshoot task
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                for (Player p : getServer().getOnlinePlayers()) {
                    if (p.isSneaking() && !p.isFlying()) {
                        SlimefunItem item = SlimefunItem.getByItem(p.getInventory().getItemInMainHand());
                        if (!(item instanceof Gun)) {
                            continue;
                        }
                        UUID uuid = p.getUniqueId();
                        Gun gun = (Gun) item;
                        Long lastUse = gun.getLAST_USES().get(uuid);
                        long time = System.currentTimeMillis();
                        if (lastUse != null) {
                            if ((time - lastUse) < gun.getCooldown()) {
                                continue;
                            }
                        }
                        gun.shoot(p);
                    }
                }
            }, 0, 1);
        }
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
