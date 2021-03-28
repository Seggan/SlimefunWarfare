package io.github.seggan.slimefunwarfare.lists;

import io.github.mooy1.infinitylib.core.PluginUtils;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;

@UtilityClass
public final class RecipeTypes {

    public static final RecipeType ELEMENT_FORGE = new RecipeType(
        PluginUtils.getKey("element_forge"),
        Items.ELEMENT_FORGE,
        "&cElement Forge",
        "&7Used to create new elements"
    );

    public static final RecipeType SPACE = new RecipeType(
        PluginUtils.getKey("space"),
        new CustomItem(
            Material.NETHER_STAR,
            "&fSpace",
            "&7Find this material in outer space"
        )
    );

    public static final RecipeType AIR_LIQUEFIER = new RecipeType(
        PluginUtils.getKey("air_liquefier"),
        Items.AIR_LIQUEFIER
    );

    public static final RecipeType BULLET_PRESS = new RecipeType(
        PluginUtils.getKey("bullet_factory"),
        Items.BULLET_PRESS
    );

    public static final RecipeType EXPLOSIVE_SYNTHESIZER = new RecipeType(
        PluginUtils.getKey("explosive_synthesizer"),
        Items.EXPLOSIVE_SYNTHESIZER
    );

    public static final RecipeType BOOMINATOR = new RecipeType(
        PluginUtils.getKey("boominator_9000"),
        Items.BOOMINATOR_9000
    );
}
