package io.github.seggan.slimefunwarfare.machines;

import io.github.mooy1.infinitylib.common.StackUtils;
import io.github.mooy1.infinitylib.machines.AbstractMachineBlock;
import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineOperation;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class IonExchangeSeparator extends AbstractMachineBlock implements EnergyNetComponent, MachineProcessHolder<IonExchangeSeparator.Operation> {

    private static final ItemStack NONE = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    private static final int[] BACKGROUND = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] INPUT = new int[]{9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    private static final int[] OUTPUT = new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    private final MachineProcessor<Operation> processor = new MachineProcessor<>(this);
    private final List<ItemStack> results = new ArrayList<>();

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
        results.add(Items.DYSPROSIUM_INGOT);
        results.add(Items.HOLMIUM_INGOT);
        results.add(Items.ERBIUM_INGOT);
        results.add(Items.YTTERBIUM_INGOT);

        SlimefunItem cerium = SlimefunItem.getById("MATERIAL_BASTNAESITE_INGOT");
        if (cerium != null) {
            results.add(cerium.getItem());
        }

        processor.setProgressBar(new ItemStack(Material.PRISMARINE_CRYSTALS));
    }

    @Nonnull
    @Override
    public MachineProcessor<Operation> getMachineProcessor() {
        return processor;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected boolean process(Block b, BlockMenu menu) {
        Operation operation = processor.getOperation(b);

        if (operation != null) {
            if (operation.isFinished()) {
                processor.updateProgressBar(menu, 22, operation);
                menu.pushItem(operation.getResult(), getInputSlots());
                processor.endOperation(b);
            } else {
                processor.updateProgressBar(menu, 22, operation);
                operation.addProgress(100);
            }
        } else {
            for (int i : getInputSlots()) {
                ItemStack item = menu.getItemInSlot(i);
                if (item != null && Objects.equals(StackUtils.getId(item), Items.MONAZITE.getItemId())) {
                    menu.consumeItem(i);
                    processor.startOperation(b, new Operation(
                        results.get(ThreadLocalRandom.current().nextInt(results.size())).clone()
                    ));
                    break;
                }
            }
        }

        return true;
    }

    @Override
    protected int getStatusSlot() {
        return 22;
    }

    @Override
    protected void setup(@Nonnull BlockMenuPreset preset) {
        preset.drawBackground(BACKGROUND);
        for (int i : INPUT) {
            preset.addItem(i, MenuBlock.INPUT_BORDER, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : OUTPUT) {
            preset.addItem(i, MenuBlock.OUTPUT_BORDER, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{19, 20};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
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
