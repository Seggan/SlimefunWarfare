package io.github.seggan.slimefunwarfare.lists;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;

public final class Items {

    private Items() {}

    public static final SlimefunItemStack SLIMESTEEL = new SlimefunItemStack(
        "SLIMESTEEL_INGOT",
        Material.IRON_INGOT,
        "&aSlimesteel Ingot",
        "",
        "Hard but elastic, this alloy",
        "is perfect for all sorts",
        "of applications"
    );

    public static final SlimefunItemStack REINFORCED_SLIMESTEEL = new SlimefunItemStack(
        "REINFORCED_SLIMESTEEL_INGOT",
        Material.IRON_INGOT,
        "&aReinforced Slimesteel Ingot",
        "",
        "Hard but elastic, this alloy",
        "is perfect for all sorts",
        "of applications"
    );

    public static final SlimefunItemStack SCOPE = new SlimefunItemStack(
        "SCOPE",
        Material.STICK,
        "&aScope"
    );

    public static final SlimefunItemStack BARREL = new SlimefunItemStack(
        "BARREL",
        Material.STICK,
        "&7Barrel"
    );

    public static final SlimefunItemStack ADVANCED_BARREL = new SlimefunItemStack(
        "ADVANCED_BARREL",
        Material.STICK,
        "&7Advanced Barrel"
    );

    public static final SlimefunItemStack BULLET_PRESS = new SlimefunItemStack(
        "BULLET_PRESS",
        Material.SMOKER,
        "&7Bullet Press",
        LoreBuilder.powerPerSecond(16),
        LoreBuilder.powerBuffer(32),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack AIR_LIQUEFIER = new SlimefunItemStack(
        "AIR_LIQUEFIER",
        Material.BEACON,
        "&bAir Liquefier",
        LoreBuilder.powerPerSecond(64),
        LoreBuilder.powerBuffer(128),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack EXPLOSIVE_SYNTHESIZER = new SlimefunItemStack(
        "EXPLOSIVE_SYNTHESIZER",
        Material.TNT,
        "&4Explosive Synthesizer",
        LoreBuilder.powerPerSecond(64),
        LoreBuilder.powerBuffer(128),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack IRON_BULLET = new SlimefunItemStack(
        "IRON_BULLET",
        Material.IRON_NUGGET,
        "&7Iron Bullet",
        "&7x0.75 damage"
    );

    public static final SlimefunItemStack LEAD_BULLET = new SlimefunItemStack(
        "LEAD_BULLET",
        Material.IRON_NUGGET,
        "&7Lead Bullet",
        "&7x1 damage"
    );

    public static final SlimefunItemStack DU_BULLET = new SlimefunItemStack(
        "DU_BULLET",
        Material.IRON_NUGGET,
        "&aDU Bullet",
        "&7Sets hit entities on fire",
        "&7x1.5 damage"
    );

    public static final SlimefunItemStack GOLD_BULLET = new SlimefunItemStack(
        "GOLD_BULLET",
        Material.GOLD_NUGGET,
        "&6Gold Bullet",
        "&7x2 damage"
    );

    public static final SlimefunItemStack TRINITROBULLETENE = new SlimefunItemStack(
        "TRINITROBULLETENE_BULLET",
        Material.GOLD_NUGGET,
        "&6Trinitrobulletene",
        "&7Sets hit entities on fire",
        "&7x2.75 damage"
    );

    public static final SlimefunItemStack GUN_CASE = new SlimefunItemStack(
        "GUN_CASE",
        Material.CROSSBOW,
        "&7Gun Case",
        "&7The base of all guns"
    );

    public static final SlimefunItemStack PYRO_POWDER = new SlimefunItemStack(
        "PYRO_POWDER",
        Material.REDSTONE,
        "&4Pyro Powder",
        "&7A very explosive substance indeed"
    );

    public static final SlimefunItemStack LIQUID_AIR = new SlimefunItemStack(
        "LIQUID_AIR",
        HeadTexture.TIN_CAN,
        "&fLiquid Air",
        "&7Don't touch!"
    );

    public static final SlimefunItemStack LIQUID_NITROGEN = new SlimefunItemStack(
        "LIQUID_NITROGEN",
        HeadTexture.TIN_CAN,
        "&fLiquid Nitrogen",
        "&7Ice cream anyone?"
    );

    public static final SlimefunItemStack PURIFIED_LIQUID_NITROGEN = new SlimefunItemStack(
        "PURIFIED_LIQUID_NITROGEN",
        HeadTexture.TIN_CAN,
        "&fPurified Liquid Nitrogen",
        "&7As pure as can be"
    );

    public static final SlimefunItemStack TITANIUM_METEOR = new SlimefunItemStack(
        "TITANIUM_METEOR",
        Material.IRON_ORE,
        "&7Titanium Meteor",
        "",
        "&7The source of the rare metal titanium"
    );

    public static final SlimefunItemStack TITANIUM_DUST = new SlimefunItemStack(
        "TITANIUM_DUST",
        Material.SUGAR,
        "&fTitanium Dust"
    );

    public static final SlimefunItemStack TITANIUM_INGOT = new SlimefunItemStack(
        "TITANIUM_INGOT",
        Material.IRON_INGOT,
        "&fTitanium Ingot",
        "",
        "&7A very strong metal found only in outer space"
    );

    public static final SlimefunItemStack TITANIUM_SUPERALLOY = new SlimefunItemStack(
        "TITANIUM_SUPERALLOY",
        Material.IRON_INGOT,
        "&fTitanium Superalloy",
        "",
        "&7The hardest, toughest, strongest material known",
        "&7to Mineraftkind"
    );

    public static final SlimefunItemStack SEGGANESSON_METEOR = new SlimefunItemStack(
        "SEGGANESSON_METEOR",
        Material.DIAMOND_ORE,
        "&7Segganesson Meteor",
        "",
        "&7The source of the rare element segganesson"
    );

    public static final SlimefunItemStack SEGGANESSON = new SlimefunItemStack(
        "SEGGANESSON",
        Material.LIGHT_BLUE_DYE,
        "&bSegganesson",
        "",
        "&7A rare element that has the potential of powering",
        "&7entire cities"
    );
}
