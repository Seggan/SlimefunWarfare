package io.github.seggan.slimefunwarfare.lists;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class RecipeTypes {

    private RecipeTypes() {}

    public static final RecipeType SPACE = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "space"),
        new CustomItem(
            Material.NETHER_STAR,
            "&fSpace",
            "&7Find this material in outer space"
        )
    );

    public static final RecipeType AIR_LIQUEFIER = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "air_liquefier"),
        Items.AIR_LIQUEFIER
    );

    public static final RecipeType BULLET_PRESS = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "bullet_factory"),
        Items.BULLET_PRESS
    );

    public static final RecipeType EXPLOSIVE_SYNTHESIZER = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "explosive_synthesizer"),
        Items.EXPLOSIVE_SYNTHESIZER
    );

    public static final RecipeType BOOMINATOR = new RecipeType(
        new NamespacedKey(SlimefunWarfare.getInstance(), "boominator_9000"),
        Items.BOOMINATOR_9000
    );
}
