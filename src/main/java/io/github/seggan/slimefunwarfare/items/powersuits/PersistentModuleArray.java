package io.github.seggan.slimefunwarfare.items.powersuits;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

public class PersistentModuleArray implements PersistentDataType<int[], Module[]> {

    public static final PersistentModuleArray TYPE = new PersistentModuleArray();

    @Nonnull
    @Override
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @Nonnull
    @Override
    public Class<Module[]> getComplexType() {
        return Module[].class;
    }

    @Nonnull
    @Override
    public int[] toPrimitive(@Nonnull Module[] complex, @Nonnull PersistentDataAdapterContext context) {
        int[] result = new int[complex.length];

        for (int i = 0; i < complex.length; i++) {
            result[i] = complex[i].getId();
        }

        return result;
    }

    @Nonnull
    @Override
    public Module[] fromPrimitive(@Nonnull int[] primitive, @Nonnull PersistentDataAdapterContext context) {
        Module[] result = new Module[primitive.length];

        for (int i = 0; i < primitive.length; i++) {
            result[i] = Module.getById(primitive[i]);
        }

        return result;
    }
}
