package io.github.seggan.slimefunwarfare.items.powersuits;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ArmorPiece {
    HEAD("helmet"),
    CHEST("chestplate"),
    LEGS("leggings"),
    FEET("boots");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
