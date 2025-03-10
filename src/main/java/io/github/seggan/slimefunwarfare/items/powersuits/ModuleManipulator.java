package io.github.seggan.slimefunwarfare.items.powersuits;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Heads;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ModuleManipulator extends SlimefunItem {

    private static final int[] BACKGROUND = new int[]{0, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 18, 19, 20, 21, 22, 27, 29, 31, 36, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] BORDER = new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    private static final int[] OUTPUT_SLOTS = new int[]{24, 25};

    private static final int INSTALLED = 3;
    private static final int INSTALL = 30;

    private static final int SUIT_MARKER = 1;
    private static final int SUIT = 10;

    private static final int MODULE_MARKER = 28;
    private static final int MODULE = 37;

    public ModuleManipulator() {
        super(Categories.POWER_SUITS, Items.MODULE_MANIPULATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.FIBER_OPTIC_CABLE.item(), Items.MODULE_CASE.item(), Items.FIBER_OPTIC_CABLE.item(),
            Items.SEGGANESSON.item(), new ItemStack(Material.CRAFTING_TABLE), Items.SEGGANESSON.item(),
            Items.LASER_DIODE.item(), Items.OSMIUM_INGOT.item(), Items.LASER_DIODE.item()
        });

        new BlockMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                ModuleManipulator.this.setup(this);
            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
                return Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return OUTPUT_SLOTS;
                }

                return new int[0];
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                ModuleManipulator.this.onNewInstance(menu, b);
            }
        };
    }

    private void setup(@Nonnull BlockMenuPreset preset) {
        preset.drawBackground(BACKGROUND);

        for (int i : BORDER) {
            preset.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(INSTALL, CustomItemStack.create(
            Material.REDSTONE,
            "&fInstall/Uninstall Module"
        ));

        preset.addItem(INSTALLED, CustomItemStack.create(
            Material.BOOK,
            "&fInstalled Modules (Click to Refresh)"
        ));

        preset.addItem(SUIT_MARKER, CustomItemStack.create(
            PlayerHead.getItemStack(Heads.SUIT_HELMET),
            "&4Place Suit Piece Here"
        ), ChestMenuUtils.getEmptyClickHandler());

        preset.addItem(MODULE_MARKER, CustomItemStack.create(
            PlayerHead.getItemStack(Heads.MODULE),
            "&6Place Module Here"
        ), ChestMenuUtils.getEmptyClickHandler());
    }

    protected void onNewInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
        menu.addMenuClickHandler(INSTALLED, (p, slot, item, action) -> {
            refresh(menu);
            return false;
        });
        menu.addMenuClickHandler(INSTALL, (p, slot, item, action) -> {
            install(menu);
            return false;
        });
    }

    private void install(@Nonnull BlockMenu menu) {
        ItemStack stack = menu.getItemInSlot(SUIT);
        SlimefunItem slimefunItem = SlimefunItem.getByItem(stack);
        if (!(slimefunItem instanceof PowerSuit powerSuit)) return;

        slimefunItem = SlimefunItem.getByItem(menu.getItemInSlot(MODULE));
        if (slimefunItem instanceof Module.ModuleItem module) {

            if (module.getModule().getAllowed() == null || module.getModule().getAllowed() == powerSuit.getType()) {
                PowerSuit.addModule(stack, module.getModule());

                menu.pushItem(stack, OUTPUT_SLOTS);
                menu.consumeItem(SUIT);
                menu.consumeItem(MODULE);
            }
        } else {
            Module module = PowerSuit.popModule(stack);

            menu.pushItem(stack, OUTPUT_SLOTS);
            menu.consumeItem(SUIT);

            if (module != null) {
                menu.pushItem(module.getItem().item().clone(), OUTPUT_SLOTS);
            }
        }
    }

    private void refresh(@Nonnull BlockMenu menu) {
        ItemStack stack = menu.getItemInSlot(SUIT);
        if (!(SlimefunItem.getByItem(stack) instanceof PowerSuit)) return;

        Module[] modules = PowerSuit.getModules(stack);

        ItemStack book = menu.getItemInSlot(INSTALLED);
        ItemMeta meta = book.getItemMeta();
        List<String> lore = meta.getLore();
        lore = lore == null ? new ArrayList<>() : lore;
        lore.clear();

        for (Module module : modules) {
            lore.add(module.getItem().getDisplayName());
        }

        meta.setLore(lore);
        book.setItemMeta(meta);
    }
}
