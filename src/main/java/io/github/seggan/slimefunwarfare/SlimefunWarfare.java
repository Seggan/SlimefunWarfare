package io.github.seggan.slimefunwarfare;

import io.github.mooy1.infinitylib.core.ConfigUtils;
import io.github.mooy1.infinitylib.core.PluginUtils;
import io.github.seggan.slimefunwarfare.items.guns.Gun;
import io.github.seggan.slimefunwarfare.items.powersuits.Module;
import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import io.github.seggan.slimefunwarfare.listeners.BetterExplosiveListener;
import io.github.seggan.slimefunwarfare.listeners.BulletListener;
import io.github.seggan.slimefunwarfare.listeners.ConcreteListener;
import io.github.seggan.slimefunwarfare.listeners.GrenadeListener;
import io.github.seggan.slimefunwarfare.listeners.HitListener;
import io.github.seggan.slimefunwarfare.listeners.ModuleListener;
import io.github.seggan.slimefunwarfare.listeners.PyroListener;
import io.github.seggan.slimefunwarfare.listeners.SpaceListener;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.spacegenerators.SpaceGenerator;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class SlimefunWarfare extends JavaPlugin implements SlimefunAddon {

    @Getter
    private static SlimefunWarfare instance = null;

    @Override
    public void onEnable() {
        instance = this;

        PluginUtils.setup("SlimefunWarfare", this, "Seggan/SlimefunWarfare/master", getFile());
        PluginUtils.setupMetrics(9227);

        PluginUtils.registerListener(new BulletListener());
        PluginUtils.registerListener(new PyroListener());
        PluginUtils.registerListener(new GrenadeListener());
        PluginUtils.registerListener(new ConcreteListener());
        PluginUtils.registerListener(new BetterExplosiveListener());
        PluginUtils.registerListener(new SpaceListener());
        PluginUtils.registerListener(new HitListener());
        PluginUtils.registerListener(new ModuleListener());

        Categories.setup(this);

        Setup.setupItems(this);
        Setup.setupMelee(this);
        Setup.setupBullets(this);
        Setup.setupGuns(this);
        Setup.setupExplosives(this);
        Setup.setupSpace(this);
        Setup.setupSuits(this);

        Module.setup(this);

        for (World world : Bukkit.getWorlds()) {
            String name = world.getName();
            if (name.endsWith("_nether") || name.endsWith("_the_end")) continue;

            World space = Bukkit.getWorld(name + "_space");
            if (space != null) continue;

            if (!SlimefunPlugin.getWorldSettingsService().isWorldEnabled(world)) continue;

            WorldCreator creator = new WorldCreator(name + "_space")
                .seed(world.getSeed())
                .generator(new SpaceGenerator());
            space = creator.createWorld();

            space.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            space.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            space.setTime(18000L);
        }

        if (ConfigUtils.getBoolean("guns.autoshoot", true)) {
            // Gun autoshoot task
            PluginUtils.scheduleRepeatingSync(() -> {
                for (Player p : getServer().getOnlinePlayers()) {
                    if (p.isSneaking() && !p.isFlying()) {
                        ItemStack stack = p.getInventory().getItemInMainHand();
                        SlimefunItem item = SlimefunItem.getByItem(stack);
                        if (!(item instanceof Gun)) {
                            continue;
                        }
                        Gun gun = (Gun) item;
                        ItemMeta meta = stack.getItemMeta();
                        PersistentDataContainer container = meta.getPersistentDataContainer();
                        long lastUse = container.getOrDefault(Gun.LAST_USE, PersistentDataType.LONG, 0L);
                        long currentTime = System.currentTimeMillis();
                        if ((currentTime - lastUse) < gun.getCooldown()) {
                            return;
                        }
                        container.set(Gun.LAST_USE, PersistentDataType.LONG, currentTime);
                        stack.setItemMeta(meta);
                        gun.shoot(p, stack);
                    }
                }
            },1);
        }

        PluginUtils.scheduleRepeatingSync(() -> {
            for (Player p : getServer().getOnlinePlayers()) {
                for (ItemStack stack : p.getInventory().getArmorContents()) {
                    SlimefunItem sfi = SlimefunItem.getByItem(stack);
                    if (sfi instanceof PowerSuit) {
                        PowerSuit suit = (PowerSuit) sfi;
                        for (Module module : PowerSuit.getModules(stack)) {
                            PotionEffect effect = module.getEffect();
                            if (effect != null) {
                                p.addPotionEffect(effect);
                            }

                            module.moreEffects(p, stack);

                            suit.addItemCharge(stack, 10);
                            suit.removeItemCharge(stack, module.getPower());
                        }
                    }
                }
            }
        }, 20);
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
