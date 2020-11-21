package io.github.seggan.slimefunwarfare;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigSettings {

    private final SlimefunWarfare plugin;

    @Getter
    private final List<String> blacklistedWorlds = new ArrayList<>();
    @Getter
    private int concreteExplodePercent = 10;

    ConfigSettings(SlimefunWarfare plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();

        concreteExplodePercent = config.getInt("explosions.concrete-explode-chance");
    }
}
