package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class RecipeTypes {

    public static final RecipeType ELEMENT_FORGE = new RecipeType(
        AbstractAddon.createKey("element_forge"),
        Items.ELEMENT_FORGE,
        "&cElement Forge",
        "&7Used to create new elements"
    );

    public static final RecipeType AIR_LIQUEFIER = new RecipeType(
        AbstractAddon.createKey("air_liquefier"),
        Items.AIR_LIQUEFIER
    );

    public static final RecipeType BULLET_PRESS = new RecipeType(
        AbstractAddon.createKey("bullet_factory"),
        Items.BULLET_PRESS
    );

    public static final RecipeType EXPLOSIVE_SYNTHESIZER = new RecipeType(
        AbstractAddon.createKey("explosive_synthesizer"),
        Items.EXPLOSIVE_SYNTHESIZER
    );

    public static final RecipeType BOOMINATOR = new RecipeType(
        AbstractAddon.createKey("boominator_9000"),
        Items.BOOMINATOR_9000
    );

    public static final RecipeType ION_SEPARATOR = new RecipeType(
        AbstractAddon.createKey("ion_exchange_separator"),
        Items.ION_EXCHANGE_SEPARATOR
    );

    public static final RecipeType METEOR_ATTRACTOR = new RecipeType(
        AbstractAddon.createKey("meteor_attractor"),
        Items.METEOR_ATTRACTOR
    );
}
