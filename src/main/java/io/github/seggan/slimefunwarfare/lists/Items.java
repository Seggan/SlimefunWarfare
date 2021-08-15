package io.github.seggan.slimefunwarfare.lists;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public final class Items {

    // region explosives
    public static final SlimefunItemStack PYRO_POWDER = new SlimefunItemStack(
        "PYRO_POWDER",
        Material.REDSTONE,
        "&4Pyro Powder",
        "",
        "&7A very explosive substance indeed"
    );
    public static final SlimefunItemStack LIQUID_AIR = new SlimefunItemStack(
        "LIQUID_AIR",
        HeadTexture.TIN_CAN,
        "&fLiquid Air",
        "",
        "&7Don't touch!"
    );
    public static final SlimefunItemStack LIQUID_NITROGEN = new SlimefunItemStack(
        "LIQUID_NITROGEN",
        HeadTexture.TIN_CAN,
        "&fLiquid Nitrogen",
        "",
        "&7Ice cream anyone?"
    );
    public static final SlimefunItemStack PURIFIED_LIQUID_NITROGEN = new SlimefunItemStack(
        "PURIFIED_LIQUID_NITROGEN",
        HeadTexture.TIN_CAN,
        "&fPurified Liquid Nitrogen",
        "",
        "&7As pure as can be"
    );
    public static final SlimefunItemStack LIQUID_OXYGEN = new SlimefunItemStack(
        "LIQUID_OXYGEN",
        HeadTexture.TIN_CAN,
        "&fLiquid Oxygen",
        "",
        "&7Old lab legends say that if",
        "&7you drop this on carpet..."
    );
    public static final SlimefunItemStack FLOX = new SlimefunItemStack(
        "FLOX",
        HeadTexture.TIN_CAN,
        "&fFLOX",
        "",
        "&7Fuel + liquid oxygen. What's",
        "&7the worst that can happen?"
    );
    public static final SlimefunItemStack THIOACETONE = new SlimefunItemStack(
        "THIOACETONE",
        Material.BROWN_DYE,
        "&6Thioacetone",
        "",
        "&7Very smelly indeed"
    );
    public static final SlimefunItemStack NITROGEN_TRIIODIDE = new SlimefunItemStack(
        "NITROGEN_TRIIODIDE",
        Material.PURPLE_DYE,
        "&5Nitrogen Triiodide",
        "",
        "&7A material for grenades"
    );
    public static final SlimefunItemStack AZIDOAZIDE_AZIDE = new SlimefunItemStack(
        "AZIDOAZIDE_AZIDE",
        Material.SUGAR,
        "&eAzidoazide Azide",
        "",
        "&7A material for grenades"
    );
    public static final SlimefunItemStack ARSENIC = new SlimefunItemStack(
        "ARSENIC",
        Material.GUNPOWDER,
        "&7Arsenic",
        "",
        "&7A material for grenades"
    );
    public static final SlimefunItemStack ENRICHED_URANIUM = new SlimefunItemStack(
        "ENRICHED_URANIUM",
        HeadTexture.BOOSTED_URANIUM,
        "&aEnriched Uranium"
    );
    public static final SlimefunItemStack EMPTY_GRENADE = new SlimefunItemStack(
        "GRENADE",
        Material.SNOWBALL,
        "&fChemical Grenade",
        "",
        "&7Contents: none"
    );
    public static final SlimefunItemStack REINFORCED_CONCRETE = new SlimefunItemStack(
        "REINFORCED_CONCRETE",
        Material.GRAY_CONCRETE,
        "&7Reinforced Concrete",
        "",
        "&7A blast-resistant (not blastproof) concrete"
    );
    public static final SlimefunItemStack NUCLEAR_BOMB = new SlimefunItemStack(
        "NUCLEAR_BOMB",
        Material.TNT,
        "&7Nuclear Bomb",
        "",
        "&7KABOOM!",
        LoreBuilder.radioactive(Radioactivity.VERY_DEADLY)
    );
    // endregion

    // region general
    public static final SlimefunItemStack BORAX = new SlimefunItemStack(
        "BORAX",
        Material.QUARTZ,
        "&fBorax",
        "",
        "&7A common mineral dropped from stone"
    );
    public static final SlimefunItemStack BORON = new SlimefunItemStack(
        "BORON",
        Material.CHARCOAL,
        "&7Boron"
    );
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
        "",
        LoreBuilder.powerPerSecond(16),
        LoreBuilder.powerBuffer(32),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack AIR_LIQUEFIER = new SlimefunItemStack(
        "AIR_LIQUEFIER",
        Material.BEACON,
        "&bAir Liquefier",
        "",
        LoreBuilder.powerPerSecond(64),
        LoreBuilder.powerBuffer(128),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack EXPLOSIVE_SYNTHESIZER = new SlimefunItemStack(
        "EXPLOSIVE_SYNTHESIZER",
        Material.TNT,
        "&4Explosive Synthesizer",
        "",
        LoreBuilder.powerPerSecond(64),
        LoreBuilder.powerBuffer(128),
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
    );

    public static final SlimefunItemStack BOOMINATOR_9000 = new SlimefunItemStack(
        "BOOMINATOR_9000",
        Material.SMITHING_TABLE,
        "&4Boominator 9000",
        "",
        "&7Processes uranium for nuclear bombs",
        LoreBuilder.powerPerSecond(1024),
        LoreBuilder.powerBuffer(2048),
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE)
    );

    public static final SlimefunItemStack IRON_BULLET = new SlimefunItemStack(
        "IRON_BULLET",
        Material.IRON_NUGGET,
        "&7Iron Bullet",
        "",
        "&7x0.75 damage"
    );

    public static final SlimefunItemStack LEAD_BULLET = new SlimefunItemStack(
        "LEAD_BULLET",
        Material.IRON_NUGGET,
        "&7Lead Bullet",
        "",
        "&7x1 damage"
    );

    public static final SlimefunItemStack DU_BULLET = new SlimefunItemStack(
        "DU_BULLET",
        Material.IRON_NUGGET,
        "&aDU Bullet",
        "",
        "&7Sets hit entities on fire",
        "&7x1.5 damage"
    );

    public static final SlimefunItemStack GOLD_BULLET = new SlimefunItemStack(
        "GOLD_BULLET",
        Material.GOLD_NUGGET,
        "&6Gold Bullet",
        "",
        "&7x2 damage"
    );

    public static final SlimefunItemStack TRINITROBULLETENE = new SlimefunItemStack(
        "TRINITROBULLETENE_BULLET",
        Material.GOLD_NUGGET,
        "&6Trinitrobulletene",
        "",
        "&7Sets hit entities on fire",
        "&7x2.75 damage"
    );

    public static final SlimefunItemStack GUN_CASE = new SlimefunItemStack(
        "GUN_CASE",
        Material.CROSSBOW,
        "&7Gun Case",
        "",
        "&7The base of all guns"
    );

    public static final SlimefunItemStack OSMIUM_METEOR = new SlimefunItemStack(
        "OSMIUM_METEOR",
        Material.IRON_ORE,
        "&9Osmium Meteor",
        "",
        "&7The source of the rare metal osmium"
    );

    public static final SlimefunItemStack OSMIUM_DUST = new SlimefunItemStack(
        "OSMIUM_DUST",
        Material.SUGAR,
        "&9Osmium Dust",
        "",
        "&7Highly toxic. Do not inhale!"
    );

    public static final SlimefunItemStack OSMIUM_INGOT = new SlimefunItemStack(
        "OSMIUM_INGOT",
        Material.IRON_INGOT,
        "&9Osmium Ingot",
        "",
        "&7A very strong metal found only in outer space"
    );

    public static final SlimefunItemStack OSMIUM_SUPERALLOY = new SlimefunItemStack(
        "OSMIUM_SUPERALLOY",
        Material.IRON_INGOT,
        "&9Osmium Superalloy",
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

    public static final SlimefunItemStack ENERGY_RECTIFIER = new SlimefunItemStack(
        "ENERGY_RECTIFIER",
        Material.POWERED_RAIL,
        "&bEnergy Rectifier",
        "",
        "&7Converts electricity into pure energy"
    );

    public static final SlimefunItemStack METEOR_ATTRACTOR = new SlimefunItemStack(
        "METEOR_ATTRACTOR",
        HeadTexture.MAGNET,
        "&fMeteor Attractor",
        "",
        "&7A magnet so strong, that it",
        "&7can pull meteors from space"
    );

    public static final SlimefunItemStack ELEMENTAL_REACTOR = new SlimefunItemStack(
        "ELEMENTAL_REACTOR",
        HeadTexture.GENERATOR,
        "&bElemental Reactor",
        "",
        "&7Generates energy from the",
        "&7energy-rich elements Unpatentablium",
        "&7and Segganesson",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR),
        LoreBuilder.powerBuffer(32_768),
        LoreBuilder.powerPerSecond(32_768)
    );

    public static final SlimefunItemStack FIBER_OPTIC_GLASS = new SlimefunItemStack(
        "FIBER_OPTIC_GLASS",
        Material.BLUE_STAINED_GLASS,
        "&fFiber Optic Glass"
    );

    public static final SlimefunItemStack FIBER_OPTIC_CABLE = new SlimefunItemStack(
        "FIBER_OPTIC_CABLE",
        Material.STRING,
        "&fFiber Optic Cable"
    );

    public static final SlimefunItemStack LASER_DIODE = new SlimefunItemStack(
        "LASER_DIODE",
        Heads.LASER,
        "&4Laser Diode"
    );

    public static final SlimefunItemStack ULTRA_MAGNET = new SlimefunItemStack(
        "ULTRA_MAGNET",
        HeadTexture.MAGNET.getTexture(),
        "&fUltra Magnet"
    );

    public static final SlimefunItemStack RADIO = new SlimefunItemStack(
        "RADIO",
        Material.REDSTONE_TORCH,
        "&fRadio",
        "",
        "&7Hold this to chat to anyone else having",
        "&7this in their inventory. The encryption key",
        "&7is used to encode/decode messages so only",
        "&7people with the same key as yours can",
        "&7understand the message. Hold to chat using",
        "&7this and right click to set the encryption key"
    );
    // endregion

    // region rare earths
    public static final SlimefunItemStack ION_EXCHANGE_SEPARATOR = new SlimefunItemStack(
        "ION_EXCHANGE_SEPARATOR",
        Material.SEA_LANTERN,
        "&bIon Exchange Separator",
        "",
        "&7Separates the hard-to-separate",
        "&7rare earths from monazite",
        LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
        LoreBuilder.powerPerSecond(256),
        LoreBuilder.powerBuffer(512)
    );

    public static final SlimefunItemStack MONAZITE = new SlimefunItemStack(
        "MONAZITE",
        Material.ORANGE_DYE,
        "&eMonazite",
        "",
        "&7The source of all the rare earths.",
        "&7Find it in igneous rock"
    );

    public static final SlimefunItemStack LANTHANUM_INGOT = new SlimefunItemStack(
        "LANTHANUM_INGOT",
        Material.IRON_INGOT,
        "&eLanthanum Ingot",
        "",
        "&7Can be used as infinite flint and steel"
    );

    public static final SlimefunItemStack NEODYMIUM_INGOT = new SlimefunItemStack(
        "NEODYMIUM_INGOT",
        Material.NETHERITE_INGOT,
        "&eNeodymium Ingot"
    );

    public static final SlimefunItemStack GADOLINIUM_INGOT = new SlimefunItemStack(
        "GADOLINIUM_INGOT",
        Material.IRON_INGOT,
        "&eGadolinium Ingot"
    );

    public static final SlimefunItemStack TERBIUM_INGOT = new SlimefunItemStack(
        "TERBIUM_INGOT",
        Material.IRON_INGOT,
        "&eTerbium Ingot"
    );

    public static final SlimefunItemStack DYSPROSIUM_INGOT = new SlimefunItemStack(
        "DYSPROSIUM_INGOT",
        Material.NETHERITE_INGOT,
        "&eDysprosium Ingot"
    );

    public static final SlimefunItemStack HOLMIUM_INGOT = new SlimefunItemStack(
        "HOLMIUM_INGOT",
        Material.BRICK,
        "&eHolmium Ingot"
    );

    public static final SlimefunItemStack ERBIUM_INGOT = new SlimefunItemStack(
        "ERBIUM_INGOT",
        Material.IRON_INGOT,
        "&eErbium Ingot"
    );

    public static final SlimefunItemStack YTTERBIUM_INGOT = new SlimefunItemStack(
        "YTTERBIUM_INGOT",
        Material.IRON_INGOT,
        "&eYtterbium Ingot"
    );

    public static final SlimefunItemStack TERFENOL_D = new SlimefunItemStack(
        "TERFENOL_D",
        Material.IRON_INGOT,
        "&6Terfenol-D",
        "",
        "&7This alloy has the interesting",
        "&7property of changing shape in",
        "&7a magnetic field"
    );

    public static final SlimefunItemStack TERFENOL_D_BLOCK = new SlimefunItemStack(
        "TERFENOL_D_BLOCK",
        Material.IRON_BLOCK,
        "&6Block of Terfenol-D"
    );

    public static final SlimefunItemStack NDFEB_ALLOY = new SlimefunItemStack(
        "NDFEB_ALLOY",
        Material.NETHERITE_INGOT,
        "&6NdFeB Alloy",
        "",
        "&7This alloy is the most magnetic",
        "&7material known to man"
    );

    public static final SlimefunItemStack NDFEB_ALLOY_BLOCK = new SlimefunItemStack(
        "NDFEB_ALLOY_BLOCK",
        Material.NETHERITE_BLOCK,
        "&6Block of NdFeB Alloy"
    );
    // endregion

    // region suits
    public static final SlimefunItemStack UNPATENTABLIUM = new SlimefunItemStack(
        "UNPATENTABLIUM",
        Material.LIGHT_BLUE_DYE,
        "&bUnpatentablium",
        "",
        "&7For some reason, the",
        "&7Feds wouldn't let you",
        "&7patent this powerful",
        "&7power source"
    );

    public static final SlimefunItemStack POWER_SUIT_GENERATOR = new SlimefunItemStack(
        "POWER_SUIT_GENERATOR",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFkNWExZmY3Zjk3NmMxYzJlYmQ0ZWY5YTkwYWQ5MTQ2Nzk1YzFjNDRmZGFlNjI5NjQ5NDg0MzRhNzI1NyJ9fX0=",
        "&6Power Suit Generator",
        "",
        "&7The central power",
        "&7source of any power",
        "&7suit"
    );

    public static final SlimefunItemStack ELEMENT_FORGE = new SlimefunItemStack(
        "ELEMENT_FORGE",
        Material.SMITHING_TABLE,
        "&cElement Forge",
        "",
        "&7Used to create new elements",
        "&cMultiblock Structure"
    );

    public static final SlimefunItemStack POWER_SUIT_HELMET = new SlimefunItemStack(
        "POWER_SUIT_HELMET",
        Heads.SUIT_HELMET,
        "&4Power Suit Helmet",
        "",
        "&7A powerful piece of armor",
        "&7that is designed to be modified.",
        LoreBuilder.powerPerSecond(5),
        LoreBuilder.powerCharged(0, 1000)
    );
    public static final SlimefunItemStack POWER_SUIT_CHESTPLATE = new SlimefunItemStack(
        "POWER_SUIT_CHESTPLATE",
        Material.LEATHER_CHESTPLATE, Color.MAROON,
        "&4Power Suit Chestplate",
        "",
        "&7A powerful piece of armor",
        "&7that is designed to be modified.",
        LoreBuilder.powerPerSecond(5),
        LoreBuilder.powerCharged(0, 1000)
    );
    public static final SlimefunItemStack POWER_SUIT_LEGGINGS = new SlimefunItemStack(
        "POWER_SUIT_LEGGINGS",
        Material.LEATHER_LEGGINGS, Color.MAROON,
        "&4Power Suit Leggings",
        "",
        "&7A powerful piece of armor",
        "&7that is designed to be modified.",
        LoreBuilder.powerPerSecond(5),
        LoreBuilder.powerCharged(0, 1000)
    );
    public static final SlimefunItemStack POWER_SUIT_BOOTS = new SlimefunItemStack(
        "POWER_SUIT_BOOTS",
        Material.LEATHER_BOOTS, Color.MAROON,
        "&4Power Suit Boots",
        "",
        "&7A powerful piece of armor",
        "&7that is designed to be modified.",
        LoreBuilder.powerPerSecond(5),
        LoreBuilder.powerCharged(0, 1000)
    );

    public static final SlimefunItemStack MODULE_MANIPULATOR = new SlimefunItemStack(
        "MODULE_MANIPULATOR",
        Material.CRAFTING_TABLE,
        "&fModule Manipulator",
        "",
        "&7Allows you to install, uninstall",
        "&7and view modules"
    );

    public static final SlimefunItemStack MODULE_CASE = new SlimefunItemStack(
        "MODULE_CASE",
        Heads.MODULE,
        "&6Module Case"
    );
    // endregion

    // region guns
    public static final SlimefunItemStack PISTOL = new SlimefunItemStack(
        "GUN_PISTOL",
        Material.CROSSBOW,
        "&7Pistol",
        "",
        "&7A short range gun",
        "&7that reloads quickly.",
        "&7Useful for close combat.",
        "&cRange: 10",
        "&cDamage: 3 hearts",
        "&cCooldown: 0.5 seconds"
    );
    public static final SlimefunItemStack REVOLVER = new SlimefunItemStack(
        "GUN_REVOLVER",
        Material.CROSSBOW,
        "&7Revolver",
        "",
        "&7A short range gun",
        "&7that reloads quickly.",
        "&7Useful for close combat.",
        "&cRange: 10",
        "&cDamage: 3 hearts",
        "&cCooldown: 0.3 seconds"
    );
    public static final SlimefunItemStack MACHINE_GUN = new SlimefunItemStack(
        "GUN_MACHINE_GUN",
        Material.CROSSBOW,
        "&7Machine Gun",
        "",
        "&7Pew pew pew",
        "&cRange: 30",
        "&cMinimum Range: 5",
        "&cDamage: 3 hearts",
        "&cCooldown: 0.15 seconds"
    );
    public static final SlimefunItemStack MINIGUN = new SlimefunItemStack(
        "GUN_MINIGUN",
        Material.CROSSBOW,
        "&7Minigun",
        "",
        "&7The ultimate device",
        "&7to pepper your friends with.",
        "&cRange: 40",
        "&cMinimum Range: 5",
        "&cDamage: 4 hearts",
        "&cCooldown: none"
    );
    public static final SlimefunItemStack RIFLE = new SlimefunItemStack(
        "GUN_RIFLE",
        Material.CROSSBOW,
        "&7Rifle",
        "",
        "&7A standard rifle.",
        "&cRange: 40",
        "&cMinimum Range: 5",
        "&cDamage: 4 hearts",
        "&cCooldown: 0.75 seconds"
    );
    public static final SlimefunItemStack SHOTGUN = new SlimefunItemStack(
        "GUN_SHOTGUN",
        Material.CROSSBOW,
        "&7Shotgun",
        "",
        "&7Less range than a",
        "&7rifle, but more damage.",
        "&cRange: 25",
        "&cMinimum Range: 5",
        "&cDamage: 6.5 hearts",
        "&cCooldown: 1.25 seconds"
    );
    public static final SlimefunItemStack ASSAULT_RIFLE = new SlimefunItemStack(
        "GUN_ASSAULT_RIFLE",
        Material.CROSSBOW,
        "&7Assault Rifle",
        "",
        "&7A derivative of",
        "&7the rifle, the assault",
        "&7rifle is the standard",
        "&7military weapon.",
        "&cRange: 50",
        "&cMinimum Range: 3",
        "&cDamage: 6.5 hearts",
        "&cCooldown: 0.3 seconds"
    );
    public static final SlimefunItemStack SNIPER = new SlimefunItemStack(
        "GUN_SNIPER",
        Material.CROSSBOW,
        "&7Sniper Rifle",
        "",
        "&7The ultimate long-range",
        "&7gun, the sniper is very powerful.",
        "&cRange: 130",
        "&cMinimum Range: 50",
        "&cDamage: 11 hearts",
        "&cCooldown: 8 seconds"
    );
    public static final SlimefunItemStack ENERGY_RIFLE = new SlimefunItemStack(
        "GUN_ENERGY_RIFLE",
        Material.CROSSBOW,
        "&eEnergy Rifle",
        "",
        "&7Finally, no need to carry around bullets!",
        "&cUses 5J per shot",
        "&cRange: 100",
        "&cDamage: 10 hearts",
        "&cCooldown: 0.2 seconds",
        LoreBuilder.powerCharged(0, 2500),
        "&eNote: The bullets visually shoot a little off",
        "&ebut you still hit the target"
    );
    // endregion

    // region melee
    public static final SlimefunItemStack ENERGY_BLADE = new SlimefunItemStack(
        "ENERGY_BLADE",
        Material.DIAMOND_SWORD,
        "&bEnergy Blade",
        "",
        "&7Known in some circles as a \"lightsaber\",",
        "&7this advanced sword uses pure energy to",
        "&7slice through living tissue",
        "",
        "&9Uses 5J per hit",
        LoreBuilder.powerCharged(0, 2500),
        "",
        "&7When In Main Hand:",
        "&2 14 Attack Damage",
        "&2 1.6 Attack Speed"
    );
    public static final SlimefunItemStack BATTLE_AXE = new SlimefunItemStack(
        "BATTLE_AXE",
        Material.IRON_AXE,
        "&6&lBattle Axe",
        "",
        "&7This axe is designed for battle. No more annoying cooldowns!",
        "",
        "&7When In Main Hand:",
        "&2 9 Attack Damage",
        "&2 1.6 Attack Speed"
    );
    public static final SlimefunItemStack OSMIUM_SWORD = new SlimefunItemStack(
        "OSMIUM_SWORD",
        Material.IRON_SWORD,
        "&6Osmium Sword",
        "",
        "&7Heavy in the hand",
        "",
        "&7When In Main Hand:",
        "&2 10 Attack Damage",
        "&2 1.6 Attack Speed"
    );
    public static final SlimefunItemStack DUMMY = new SlimefunItemStack(
        "DUMMY",
        Material.HUSK_SPAWN_EGG,
        "&fDummy Spawn Egg",
        "",
        "&7Right click to spawn a dummy; if you hit him",
        "&7he'll tell you how much damage you dealt.",
        "&7Right click on him to destroy him"
    );
    // endregion

    static {
        ENERGY_BLADE.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);

        ItemMeta meta = Items.ENERGY_BLADE.getItemMeta();
        meta.setUnbreakable(true);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackDamage",
            13,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        ));

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ENERGY_BLADE.setItemMeta(meta);

        // Sets the attack speed to match that of a sword
        meta = BATTLE_AXE.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackSpeed",
            -2.4,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        ));

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackDamage",
            8,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        ));

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        BATTLE_AXE.setItemMeta(meta);

        OSMIUM_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 8);

        meta = OSMIUM_SWORD.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackDamage",
            9,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND
        ));

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        OSMIUM_SWORD.setItemMeta(meta);
    }
}
