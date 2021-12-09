package io.github.seggan.slimefunwarfare.georesources;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.Util;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;
import javax.annotation.Nonnull;

public class Monazite implements GEOResource {

    private static final Biome theBiome;

    static {
        String s = "ONFNYG_" + Util.whatIsThis;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        theBiome = Biome.valueOf(sb.toString().toUpperCase(Locale.ENGLISH));
    }

    private final NamespacedKey key;
    private final ItemStack item;

    public Monazite(ItemStack stack) {
        key = AbstractAddon.createKey("mozanite");
        item = stack;
    }

    @Override
    public int getDefaultSupply(@Nonnull World.Environment environment, @Nonnull Biome biome) {
        if (biome == theBiome) {
            return Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion") ? 1 : 4;
        }

        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion") ? 2 : 3;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Monazite";
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
