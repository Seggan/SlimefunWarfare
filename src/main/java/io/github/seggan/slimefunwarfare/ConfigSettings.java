package io.github.seggan.slimefunwarfare;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSettings {

    private SlimefunWarfare plugin;

    @Getter
    private String[] blacklistedWorlds;
    @Getter
    private int concreteExplodePercent;

    ConfigSettings(SlimefunWarfare plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();

        concreteExplodePercent = config.getInt("explosions.concrete-explode-chance");
    }
}
