package io.github.seggan.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.items.Gun;
import io.github.seggan.slimefunwarfare.items.SlimesteelIngot;
import io.github.seggan.slimefunwarfare.listeners.BulletListener;
import io.github.seggan.slimefunwarfare.lists.Guns;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.machines.BulletFactory;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class SlimefunWarfare extends JavaPlugin implements SlimefunAddon {

    @Getter
    private static SlimefunWarfare instance = null;

    @Override
    public void onEnable() {
        getLogger().info("Slimefun Warfare enabled.");

        getServer().getPluginManager().registerEvents(new BulletListener(), this);

        instance = this;


        new SlimesteelIngot().register(this);
        new SlimefunItem(
            Items.sfwarfareCategory, Items.REINFORCED_SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
            Items.SLIMESTEEL, new ItemStack(Material.SLIME_BLOCK), SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT,
            null, null, null
        }).register(this);
        new SlimefunItem(Items.sfwarfareCategory, Items.SCOPE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.MULTIMETER, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.HARDENED_GLASS, null, SlimefunItems.HARDENED_GLASS,
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
        }).register(this);
        new SlimefunItem(Items.sfwarfareCategory, Items.BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL, Items.SLIMESTEEL, Items.SLIMESTEEL,
            null, null, null,
            Items.SLIMESTEEL, Items.SLIMESTEEL, Items.SLIMESTEEL
        }).register(this);
        new SlimefunItem(
            Items.sfwarfareCategory, Items.ADVANCED_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL,
            Items.BARREL, Items.BARREL, Items.BARREL,
            Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL
        }).register(this);

        new BulletFactory().register(this);
        new Bullet(Items.LEAD_BULLET, SlimefunItems.LEAD_INGOT, 1).register(this);
        new Bullet(Items.DU_BULLET, SlimefunItems.SMALL_URANIUM, 1.5).register(this);
        new Bullet(Items.GOLD_BULLET, SlimefunItems.GOLD_20K, 2).register(this);

        new SlimefunItem(Items.sfwarfareCategory, Items.GUN_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL, new ItemStack(Material.GUNPOWDER), Items.SLIMESTEEL,
            Items.SLIMESTEEL, new ItemStack(Material.FLINT_AND_STEEL), Items.SLIMESTEEL,
            SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.CROSSBOW), SlimefunItems.PLASTIC_SHEET
        }).register(this);

        new Gun(Guns.PISTOL, new ItemStack[] {
            null, Items.SLIMESTEEL, null,
            null, Items.GUN_CASE, Items.SLIMESTEEL,
            null, Items.SLIMESTEEL, new ItemStack(Material.STICK)
        }, 7, 4, 0.75).register(this);

        new Gun(Guns.REVOLVER, new ItemStack[] {
            null, Items.SLIMESTEEL, null,
            null, Guns.PISTOL, Items.SLIMESTEEL,
            null, Items.SLIMESTEEL, null
        }, 10, 6, 0.5).register(this);

        new Gun(Guns.MACHINE_GUN, new ItemStack[] {
            Items.SLIMESTEEL, Items.SCOPE, null,
            Items.BARREL, Guns.REVOLVER, Items.SLIMESTEEL,
            Items.SLIMESTEEL, Items.SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 30, 5, 6, 0.15).register(this);

        new Gun(Guns.MINIGUN, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, Items.SCOPE, Items.REINFORCED_SLIMESTEEL,
            Items.ADVANCED_BARREL, Guns.MACHINE_GUN, Items.REINFORCED_SLIMESTEEL,
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
        }, 40, 5, 8, 0).register(this);

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
