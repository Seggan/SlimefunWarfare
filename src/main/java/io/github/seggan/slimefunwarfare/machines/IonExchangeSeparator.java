package io.github.seggan.slimefunwarfare.machines;

import io.github.mooy1.infinitylib.items.StackUtils;
import io.github.mooy1.infinitylib.presets.MenuPreset;
import io.github.mooy1.infinitylib.slimefun.AbstractTickingContainer;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineOperation;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;

public class IonExchangeSeparator extends AbstractTickingContainer implements EnergyNetComponent, MachineProcessHolder<IonExchangeSeparator.Operation> {

    private static final int[] BACKGROUND = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] INPUT_BORDER = new int[]{9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    private static final int[] OUTPUT_BORDER = new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    private static final int[] INPUT = new int[]{19, 20};
    private static final int[] OUTPUT = new int[]{24, 25};

    private static final ItemStack NONE = new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " ");

    private final MachineProcessor<Operation> processor = new MachineProcessor<>(this);
    private final List<SlimefunItemStack> results = new ArrayList<>();

    public IonExchangeSeparator() {
        super(Categories.MACHINES, Items.ION_EXCHANGE_SEPARATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.ELECTRO_MAGNET, Items.REINFORCED_SLIMESTEEL,
            SlimefunItems.SULFATE, null, SlimefunItems.SULFATE,
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.ELECTRO_MAGNET, Items.REINFORCED_SLIMESTEEL
        });

        processor.setProgressBar(new ItemStack(Material.IRON_PICKAXE));

        results.add(Items.LANTHANUM_INGOT);
        results.add(Items.NEODYMIUM_INGOT);
        results.add(Items.GADOLINIUM_INGOT);
        results.add(Items.TERBIUM_INGOT);

        addItemHandler(onBreak());
    }

    @Override
    protected void tick(@Nonnull BlockMenu menu, @Nonnull Block b) {
        Operation operation = processor.getOperation(b);

        if (operation != null) {
            int charge = getCharge(b.getLocation());
            if (charge > 128) {
                removeCharge(b.getLocation(), 128);
                if (operation.isFinished()) {
                    menu.replaceExistingItem(22, NONE);
                    menu.pushItem(operation.getResult(), OUTPUT);
                    processor.endOperation(b);
                } else {
                    processor.updateProgressBar(menu, 22, operation);
                    operation.addProgress(1);
                }
            }
        } else {
            for (int i : INPUT) {
                ItemStack item = menu.getItemInSlot(i);
                if (item == null) continue;
                if (Objects.equals(StackUtils.getID(item), Items.MONAZITE.getItemId())) {
                    menu.consumeItem(i);
                    processor.startOperation(b, new Operation(
                        results.get(ThreadLocalRandom.current().nextInt(results.size())).clone()
                    ));
                    break;
                }
            }
        }
    }

    @Override
    protected void setupMenu(@Nonnull BlockMenuPreset preset) {
        preset.drawBackground(BACKGROUND);

        for (int i : INPUT_BORDER) {
            preset.addItem(i, MenuPreset.INPUT_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : OUTPUT_BORDER) {
            preset.addItem(i, MenuPreset.OUTPUT_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(22, NONE, ChestMenuUtils.getEmptyClickHandler());
    }

    @Nonnull
    @Override
    protected int[] getTransportSlots(@Nonnull DirtyChestMenu menu, @Nonnull ItemTransportFlow flow, ItemStack item) {
        if (flow == ItemTransportFlow.INSERT) {
            return INPUT;
        } else {
            return OUTPUT;
        }
    }

    @Nonnull
    @Override
    public MachineProcessor<Operation> getMachineProcessor() {
        return processor;
    }

    @Nonnull
    protected BlockBreakHandler onBreak() {
        return new SimpleBlockBreakHandler() {
            @Override
            public void onBlockBreak(@Nonnull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), INPUT);
                    inv.dropItems(b.getLocation(), OUTPUT);
                }
                processor.endOperation(b);
            }

        };
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 512;
    }

    protected static final class Operation implements MachineOperation {

        @Getter
        private final ItemStack result;

        private int ticks = 0;

        protected Operation(ItemStack result) {
            this.result = result;
        }

        @Override
        public void addProgress(int ticks) {
            this.ticks += ticks;
        }

        @Override
        public int getProgress() {
            return ticks;
        }

        @Override
        public int getTotalTicks() {
            return 60 * 5 * 2;
        }
    }
}
