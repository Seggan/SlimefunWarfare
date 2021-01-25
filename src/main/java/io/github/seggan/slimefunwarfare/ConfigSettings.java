package io.github.seggan.slimefunwarfare;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class ConfigSettings {

    @Getter(AccessLevel.NONE)
    private final SlimefunWarfare plugin;

    private int concreteExplodePercent = 10;
    private boolean autoshoot = true;
    private boolean minRangeOn = true;
    private boolean useBulletsFromInv = true;

    private int meteorSpawnRate = 5;
    private int meteorsPerChunk = 1;
    private int segganessonChance = 15;

    ConfigSettings(SlimefunWarfare plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfig();

        concreteExplodePercent = config.getInt("explosions.concrete-explode-chance");
        autoshoot = config.getBoolean("guns.autoshoot");
        minRangeOn = config.getBoolean("guns.min-range-on");
        useBulletsFromInv = config.getBoolean("guns.use-bullets-from-inv");

        meteorSpawnRate = config.getInt("space.meteor-spawn-rate");
        meteorsPerChunk = config.getInt("space.meteors-per-chunk");
        segganessonChance = config.getInt("space.segganesson-chance");
    }
}
