package io.github.seggan.slimefunwarfare.items.powersuits;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import lombok.Getter;

import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PowerSuit extends SlimefunItem implements ProtectiveArmor, Rechargeable {

    private static final NamespacedKey MODULES = AbstractAddon.createKey("modules");
    @Getter
    private final ArmorPiece type;

    public PowerSuit(SlimefunItemStack item, ItemStack[] recipe, ArmorPiece type) {
        super(Categories.POWER_SUITS, item, RecipeType.ARMOR_FORGE, recipe);
        this.type = type;

        ItemMeta meta = this.getItem().getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        this.getItem().setItemMeta(meta);
    }

    @Nonnull
    public static Module[] getModules(@Nullable ItemStack stack) {
        if (stack == null) {
            return new Module[0];
        }
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.getOrDefault(MODULES, PersistentModuleArray.TYPE, new Module[0]);
    }

    public static void addModule(@Nonnull ItemStack stack, @Nonnull Module module) {
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Module[] newArr = (Module[]) ArrayUtils.add(container.getOrDefault(MODULES, PersistentModuleArray.TYPE, new Module[0]), module);
        container.set(MODULES, PersistentModuleArray.TYPE, newArr);
        stack.setItemMeta(meta);
    }

    @Nullable
    public static Module popModule(@Nonnull ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        Module[] modules = container.getOrDefault(MODULES, PersistentModuleArray.TYPE, new Module[0]);
        if (modules.length == 0) {
            return null;
        }

        Module module = modules[modules.length - 1];

        container.set(MODULES, PersistentModuleArray.TYPE, Arrays.copyOf(modules, modules.length - 1));
        stack.setItemMeta(meta);

        return module;
    }

    @Nonnull
    @Override
    public ProtectionType[] getProtectionTypes() {
        return ProtectionType.values();
    }

    @Override
    public boolean isFullSetRequired() {
        return true;
    }

    @Nonnull
    @Override
    public NamespacedKey getArmorSetId() {
        return AbstractAddon.createKey("power_suit");
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 1000;
    }
}
