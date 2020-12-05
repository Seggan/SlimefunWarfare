package io.github.seggan.slimefunwarfare;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSettings {

    private final SlimefunWarfare plugin;

    @Getter
    private int concreteExplodePercent = 10;
    @Getter
    private boolean autoshoot = true;
    @Getter
    private boolean minRangeOn = true;
    @Getter
    private boolean useBulletsFromInv = true;

    ConfigSettings(SlimefunWarfare plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();

        concreteExplodePercent = config.getInt("explosions.concrete-explode-chance");
        autoshoot = config.getBoolean("guns.autoshoot");
        minRangeOn = config.getBoolean("guns.min-range-on");
        useBulletsFromInv = config.getBoolean("guns.use-bullets-from-inv");
    }
}
