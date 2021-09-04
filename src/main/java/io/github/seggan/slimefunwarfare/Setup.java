package io.github.seggan.slimefunwarfare;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.georesources.Arsenic;
import io.github.seggan.slimefunwarfare.georesources.Monazite;
import io.github.seggan.slimefunwarfare.items.Bullet;
import io.github.seggan.slimefunwarfare.items.Dummy;
import io.github.seggan.slimefunwarfare.items.EnergyBlade;
import io.github.seggan.slimefunwarfare.items.Grenade;
import io.github.seggan.slimefunwarfare.items.NuclearBomb;
import io.github.seggan.slimefunwarfare.items.Radio;
import io.github.seggan.slimefunwarfare.items.RadioactiveItem;
import io.github.seggan.slimefunwarfare.items.blocks.Meteor;
import io.github.seggan.slimefunwarfare.items.guns.EnergyRifle;
import io.github.seggan.slimefunwarfare.items.guns.Gun;
import io.github.seggan.slimefunwarfare.items.powersuits.ArmorPiece;
import io.github.seggan.slimefunwarfare.items.powersuits.ElementForge;
import io.github.seggan.slimefunwarfare.items.powersuits.ModuleManipulator;
import io.github.seggan.slimefunwarfare.items.powersuits.PowerSuit;
import io.github.seggan.slimefunwarfare.items.rareearths.Lanthanum;
import io.github.seggan.slimefunwarfare.items.rareearths.RareEarth;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.seggan.slimefunwarfare.machines.AirLiquefier;
import io.github.seggan.slimefunwarfare.machines.Boominator9000;
import io.github.seggan.slimefunwarfare.machines.BulletPress;
import io.github.seggan.slimefunwarfare.machines.ElementalReactor;
import io.github.seggan.slimefunwarfare.machines.ExplosiveSynthesizer;
import io.github.seggan.slimefunwarfare.machines.IonExchangeSeparator;
import io.github.seggan.slimefunwarfare.machines.MeteorAttractor;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.VanillaItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public final class Setup {

    private static int researchId = 3478;

    static void setupItems(SlimefunWarfare addon) {
        new SlimefunItem(Categories.RESOURCES, Items.BORAX, RecipeType.NULL, fillNulls(
            new CustomItemStack(
                Material.STONE,
                "&fStone drop",
                "",
                "&7This item is dropped from stone"
            )
        )).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.BORON, RecipeType.SMELTERY, fillNulls(Items.BORAX)).register(addon);
        new SlimefunItem(Categories.GENERAL, Items.SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
            SlimefunItems.STEEL_INGOT, new ItemStack(Material.SLIME_BALL), null,
            null, null, null,
            null, null, null
        }).register(addon);

        new SlimefunItem(
            Categories.GENERAL, Items.REINFORCED_SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
            Items.SLIMESTEEL, new ItemStack(Material.SLIME_BLOCK), SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.CORINTHIAN_BRONZE_INGOT, SlimefunItems.ALUMINUM_BRONZE_INGOT,
            null, null, null
        }).register(addon);

        new VanillaItem(Categories.GENERAL, new ItemStack(Material.SLIME_BALL), "SLIME_BALL", RecipeType.ENHANCED_CRAFTING_TABLE, fillNulls(
            Items.BORAX, new ItemStack(Material.BONE_MEAL), new ItemStack(Material.WATER_BUCKET)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.SCOPE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.MULTIMETER, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.HARDENED_GLASS, null, SlimefunItems.HARDENED_GLASS,
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL, Items.SLIMESTEEL, Items.SLIMESTEEL,
            null, null, null,
            Items.SLIMESTEEL, Items.SLIMESTEEL, Items.SLIMESTEEL
        }).register(addon);

        new SlimefunItem(
            Categories.GENERAL, Items.ADVANCED_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL,
            Items.BARREL, Items.BARREL, Items.BARREL,
            Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL
        }).register(addon);

        new ElementForge(Categories.POWER_SUITS, Items.ELEMENT_FORGE).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.FIBER_OPTIC_GLASS, RecipeType.SMELTERY, fillNulls(
            Items.ERBIUM_INGOT, SlimefunItems.SILICON, new ItemStack(Material.GLASS), new ItemStack(Material.GLASS_PANE)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.FIBER_OPTIC_CABLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.SILVER_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.SILVER_INGOT,
            Items.FIBER_OPTIC_GLASS, Items.FIBER_OPTIC_GLASS, Items.FIBER_OPTIC_GLASS,
            SlimefunItems.SILVER_INGOT, SlimefunItems.SILVER_INGOT, SlimefunItems.SILVER_INGOT
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.LASER_DIODE, RecipeType.SMELTERY, fillNulls(
            SlimefunItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.GLOWSTONE),
            new SlimefunItemStack(Items.YTTERBIUM_INGOT, 2), Items.FIBER_OPTIC_GLASS
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.ULTRA_MAGNET, RecipeType.SMELTERY, fillNulls(
            SlimefunItems.MAGNET, Items.NDFEB_ALLOY, new SlimefunItemStack(Items.HOLMIUM_INGOT, 2)
        )).register(addon);

        new Radio().register(addon);
    }

    static void setupMelee(SlimefunWarfare addon) {
        new UnplaceableBlock(Categories.MELEE, Items.BATTLE_AXE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.IRON_AXE), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_AXE),
            null, new ItemStack(Material.STICK), null,
            null, new ItemStack(Material.STICK), null
        }).register(addon);

        new SlimefunItem(Categories.MELEE, Items.OSMIUM_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, Items.OSMIUM_INGOT, null,
            null, Items.OSMIUM_INGOT, null,
            null, new ItemStack(Material.STICK), null
        }).register(addon);

        // Energy
        new SlimefunItem(Categories.GENERAL, Items.OSMIUM_SUPERALLOY, RecipeType.SMELTERY, new ItemStack[]{
            Items.OSMIUM_INGOT, Items.SEGGANESSON, Items.REINFORCED_SLIMESTEEL, SlimefunItems.REINFORCED_ALLOY_INGOT,
            Items.OSMIUM_DUST, Items.GADOLINIUM_INGOT, null,
            null, null, null
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.ENERGY_RECTIFIER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.OSMIUM_SUPERALLOY, SlimefunItems.CARBONADO_EDGED_CAPACITOR, Items.OSMIUM_SUPERALLOY,
            SlimefunItems.POWER_CRYSTAL, SlimefunItems.ENERGY_REGULATOR, SlimefunItems.POWER_CRYSTAL,
            Items.OSMIUM_SUPERALLOY, SlimefunItems.CARBONADO_EDGED_CAPACITOR, Items.OSMIUM_SUPERALLOY
        }).register(addon);

        new EnergyBlade().register(addon);

        // Misc
        new Dummy().register(addon);
    }

    static void setupBullets(SlimefunWarfare addon) {
        new BulletPress().register(addon);
        new Bullet(Items.IRON_BULLET, new ItemStack(Material.IRON_INGOT), 0.75, false).register(addon);
        new Bullet(Items.LEAD_BULLET, SlimefunItems.LEAD_INGOT, 1, false).register(addon);
        new Bullet(Items.DU_BULLET, SlimefunItems.SMALL_URANIUM, 1.5, true).register(addon);
        new Bullet(Items.GOLD_BULLET, SlimefunItems.GOLD_12K, 2, false).register(addon);
        new Bullet(Items.TRINITROBULLETENE, Items.PYRO_POWDER, 2.75, true).register(addon);
    }

    static void setupGuns(SlimefunWarfare addon) {
        new SlimefunItem(Categories.GENERAL, Items.GUN_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL, new ItemStack(Material.GUNPOWDER), Items.SLIMESTEEL,
            Items.SLIMESTEEL, new ItemStack(Material.FLINT_AND_STEEL), Items.SLIMESTEEL,
            SlimefunItems.PLASTIC_SHEET, new ItemStack(Material.CROSSBOW), SlimefunItems.PLASTIC_SHEET
        }).register(addon);

        new Gun(Items.PISTOL, new ItemStack[]{
            null, Items.SLIMESTEEL, null,
            null, Items.GUN_CASE, Items.SLIMESTEEL,
            null, Items.SLIMESTEEL, new ItemStack(Material.STICK)
        }, 10, 6, 0.5).register(addon);

        new Gun(Items.REVOLVER, new ItemStack[]{
            null, Items.SLIMESTEEL, null,
            null, Items.PISTOL, Items.SLIMESTEEL,
            null, Items.SLIMESTEEL, null
        }, 10, 6, 0.3).register(addon);

        new Gun(Items.MACHINE_GUN, new ItemStack[]{
            Items.SLIMESTEEL, Items.SCOPE, null,
            Items.BARREL, Items.REVOLVER, Items.SLIMESTEEL,
            Items.SLIMESTEEL, Items.SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 30, 5, 6, 0.15).register(addon);

        new Gun(Items.MINIGUN, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL, Items.SCOPE, Items.REINFORCED_SLIMESTEEL,
            Items.ADVANCED_BARREL, Items.MACHINE_GUN, Items.REINFORCED_SLIMESTEEL,
            Items.REINFORCED_SLIMESTEEL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
        }, 40, 5, 8, 0).register(addon);

        new Gun(Items.RIFLE, new ItemStack[]{
            null, Items.SCOPE, null,
            Items.BARREL, Items.GUN_CASE, Items.SLIMESTEEL,
            null, Items.SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 40, 5, 8, 0.75).register(addon);

        new Gun(Items.SHOTGUN, new ItemStack[]{
            Items.SLIMESTEEL, Items.SLIMESTEEL, null,
            Items.BARREL, Items.BARREL, Items.GUN_CASE,
            Items.SLIMESTEEL, Items.SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 25, 5, 13, 1.25).register(addon);

        new Gun(Items.ASSAULT_RIFLE, new ItemStack[]{
            Items.SLIMESTEEL, Items.SCOPE, new ItemStack(Material.OAK_PLANKS),
            Items.BARREL, Items.RIFLE, Items.REINFORCED_SLIMESTEEL,
            Items.SLIMESTEEL, Items.SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 50, 3, 13, 0.3).register(addon);

        new Gun(Items.SNIPER, new ItemStack[]{
            null, Items.REINFORCED_SLIMESTEEL, Items.REINFORCED_SLIMESTEEL,
            Items.ADVANCED_BARREL, Items.ADVANCED_BARREL, Items.ASSAULT_RIFLE,
            new ItemStack(Material.STICK), Items.REINFORCED_SLIMESTEEL, SlimefunItems.PLASTIC_SHEET
        }, 130, 50, 22, 8).register(addon);

        new EnergyRifle().register(addon);
    }

    static void setupExplosives(SlimefunWarfare addon) {
        new AirLiquefier().register(addon);
        new ExplosiveSynthesizer().register(addon);
        new Boominator9000().register(addon);

        new SlimefunItem(Categories.GENERAL, Items.REINFORCED_CONCRETE, RecipeType.SMELTERY,
            new ItemStack[]{
                SlimefunItems.IRON_DUST, new ItemStack(Material.GRAY_CONCRETE_POWDER), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.LIQUID_AIR, RecipeTypes.AIR_LIQUEFIER,
            new ItemStack[]{
                SlimefunItems.TIN_CAN, null, null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.LIQUID_NITROGEN, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.LIQUID_AIR, Items.LIQUID_AIR, Items.LIQUID_AIR,
                Items.LIQUID_AIR, Items.LIQUID_AIR, Items.LIQUID_AIR,
                Items.LIQUID_AIR, null, null
            }, new SlimefunItemStack(Items.LIQUID_NITROGEN, 4)).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.PURIFIED_LIQUID_NITROGEN, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN,
                Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN,
                Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN, Items.LIQUID_NITROGEN
            }, new SlimefunItemStack(Items.PURIFIED_LIQUID_NITROGEN, 4)).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.NITROGEN_TRIIODIDE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                Items.LIQUID_NITROGEN, new ItemStack(Material.DRIED_KELP), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.AZIDOAZIDE_AZIDE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                Items.PURIFIED_LIQUID_NITROGEN, new ItemStack(Material.COAL), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.ARSENIC, RecipeType.GEO_MINER, new ItemStack[9]
        ).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.THIOACETONE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                SlimefunItems.OIL_BUCKET, SlimefunItems.SULFATE, null,
                null, null, null,
                null, null, null
            }).register(addon);

        new Arsenic(Items.ARSENIC).register();

        new VanillaItem(
            Categories.EXPLOSIVES, new ItemStack(Material.GUNPOWDER),
            "GUNPOWDER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.SULFATE, new ItemStack(Material.BONE_MEAL), new ItemStack(Material.CHARCOAL),
            null, null, null,
            null, null, null
        }).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.PYRO_POWDER, RecipeType.GRIND_STONE,
            fillNulls(new ItemStack(Material.TNT)), new SlimefunItemStack(Items.PYRO_POWDER, 4)).register(addon);

        new RadioactiveItem(Categories.EXPLOSIVES, Items.ENRICHED_URANIUM, RecipeTypes.BOOMINATOR,
            fillNulls(SlimefunItems.BOOSTED_URANIUM), Radioactivity.VERY_DEADLY).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.EMPTY_GRENADE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.PYRO_POWDER, SlimefunItems.STEEL_INGOT, Items.PYRO_POWDER,
                SlimefunItems.STEEL_INGOT, SlimefunItems.TIN_CAN, SlimefunItems.STEEL_INGOT,
                Items.PYRO_POWDER, SlimefunItems.STEEL_INGOT, Items.PYRO_POWDER,
            }, new SlimefunItemStack(Items.EMPTY_GRENADE, 4)).register(addon);

        new Grenade(Items.NITROGEN_TRIIODIDE).register(addon);
        new Grenade(Items.AZIDOAZIDE_AZIDE).register(addon);
        new Grenade(Items.ARSENIC).register(addon);
        new Grenade(Items.PYRO_POWDER).register(addon);
        new Grenade(Items.THIOACETONE).register(addon);
        new Grenade(Items.OSMIUM_DUST).register(addon);

        new NuclearBomb(Categories.EXPLOSIVES, Items.NUCLEAR_BOMB, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.STEEL_PLATE, Items.ENRICHED_URANIUM, SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.PISTON), Items.ENRICHED_URANIUM, new ItemStack(Material.PISTON),
                SlimefunItems.STEEL_PLATE, Items.ENRICHED_URANIUM, SlimefunItems.STEEL_PLATE
            }).register(addon);
    }

    static void setupSpace(SlimefunWarfare addon) {
        new Meteor(Items.OSMIUM_METEOR).register(addon);
        new Meteor(Items.SEGGANESSON_METEOR).register(addon);

        new SlimefunItem(Categories.RESOURCES, Items.OSMIUM_DUST, RecipeType.ORE_CRUSHER, fillNulls(Items.OSMIUM_METEOR)).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.OSMIUM_INGOT, RecipeType.SMELTERY, fillNulls(Items.OSMIUM_DUST)).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.SEGGANESSON, RecipeType.ORE_CRUSHER, fillNulls(Items.SEGGANESSON_METEOR)).register(addon);

        new IonExchangeSeparator().energyPerTick(128).register(addon);

        new Monazite(Items.MONAZITE).register();
        new SlimefunItem(Categories.RESOURCES, Items.MONAZITE, RecipeType.GEO_MINER, new ItemStack[9]).register(addon);

        new Lanthanum().register(addon);
        new RareEarth(Items.NEODYMIUM_INGOT).register(addon);
        new RareEarth(Items.GADOLINIUM_INGOT).register(addon);
        new RareEarth(Items.TERBIUM_INGOT).register(addon);
        new RareEarth(Items.DYSPROSIUM_INGOT).register(addon);
        new RareEarth(Items.HOLMIUM_INGOT).register(addon);
        new RareEarth(Items.ERBIUM_INGOT).register(addon);
        new RareEarth(Items.YTTERBIUM_INGOT).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.TERFENOL_D, RecipeType.SMELTERY, fillNulls(
            new SlimefunItemStack(Items.TERBIUM_INGOT, 3), new SlimefunItemStack(Items.GADOLINIUM_INGOT, 2), new ItemStack(Material.IRON_INGOT),
            SlimefunItems.COBALT_INGOT, new SlimefunItemStack(Items.DYSPROSIUM_INGOT, 2)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.TERFENOL_D_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.TERFENOL_D, Items.TERFENOL_D, Items.TERFENOL_D,
            Items.TERFENOL_D, Items.TERFENOL_D, Items.TERFENOL_D,
            Items.TERFENOL_D, Items.TERFENOL_D, Items.TERFENOL_D
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.NDFEB_ALLOY, RecipeType.SMELTERY, fillNulls(
            new SlimefunItemStack(Items.NEODYMIUM_INGOT, 4), Items.BORON,
            new ItemStack(Material.IRON_INGOT), Items.DYSPROSIUM_INGOT
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.NDFEB_ALLOY_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.NDFEB_ALLOY, Items.NDFEB_ALLOY, Items.NDFEB_ALLOY,
            Items.NDFEB_ALLOY, Items.NDFEB_ALLOY, Items.NDFEB_ALLOY,
            Items.NDFEB_ALLOY, Items.NDFEB_ALLOY, Items.NDFEB_ALLOY
        }).register(addon);

        new MeteorAttractor().register(addon);

        new ElementalReactor().register(addon);
    }

    static void setupSuits(SlimefunWarfare addon) {
        new SlimefunItem(Categories.RESOURCES, Items.UNPATENTABLIUM, RecipeTypes.ELEMENT_FORGE, new ItemStack[]{
            Items.SEGGANESSON, Items.ARSENIC, Items.SEGGANESSON,
            Items.SEGGANESSON, Items.OSMIUM_INGOT, Items.SEGGANESSON,
            Items.SEGGANESSON, Items.ARSENIC, Items.SEGGANESSON
        }).register(addon);

        new SlimefunItem(Categories.POWER_SUITS, Items.POWER_SUIT_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.UNPATENTABLIUM, Items.UNPATENTABLIUM, Items.UNPATENTABLIUM,
            Items.UNPATENTABLIUM, SlimefunItems.NETHER_STAR_REACTOR, Items.UNPATENTABLIUM,
            Items.UNPATENTABLIUM, Items.LASER_DIODE, Items.UNPATENTABLIUM
        }).register(addon);

        new SlimefunItem(Categories.POWER_SUITS, Items.MODULE_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, Items.OSMIUM_INGOT, null,
            Items.FIBER_OPTIC_CABLE, Items.POWER_SUIT_GENERATOR, Items.FIBER_OPTIC_CABLE,
            null, Items.OSMIUM_INGOT, null
        }, new SlimefunItemStack(Items.MODULE_CASE, 2)).register(addon);

        new PowerSuit(Items.POWER_SUIT_HELMET, new ItemStack[]{
            SlimefunItems.ADVANCED_CIRCUIT_BOARD, Items.POWER_SUIT_GENERATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
            Items.OSMIUM_SUPERALLOY, SlimefunItems.SCUBA_HELMET, Items.OSMIUM_SUPERALLOY,
            Items.FIBER_OPTIC_CABLE, Items.OSMIUM_SUPERALLOY, Items.FIBER_OPTIC_CABLE
        }, ArmorPiece.HEAD).register(addon);

        new PowerSuit(Items.POWER_SUIT_CHESTPLATE, new ItemStack[]{
            Items.OSMIUM_SUPERALLOY, Items.ULTRA_MAGNET, Items.OSMIUM_SUPERALLOY,
            Items.POWER_SUIT_GENERATOR, SlimefunItems.HAZMAT_CHESTPLATE, Items.POWER_SUIT_GENERATOR,
            Items.LASER_DIODE, Items.SEGGANESSON, Items.LASER_DIODE
        }, ArmorPiece.CHEST).register(addon);

        new PowerSuit(Items.POWER_SUIT_LEGGINGS, new ItemStack[]{
            SlimefunItems.ELECTRIC_MOTOR, Items.POWER_SUIT_GENERATOR, SlimefunItems.ELECTRIC_MOTOR,
            Items.OSMIUM_SUPERALLOY, SlimefunItems.HAZMAT_LEGGINGS, Items.OSMIUM_SUPERALLOY,
            Items.FIBER_OPTIC_CABLE, null, Items.FIBER_OPTIC_CABLE
        }, ArmorPiece.LEGS).register(addon);

        new PowerSuit(Items.POWER_SUIT_BOOTS, new ItemStack[]{
            null, null, null,
            Items.OSMIUM_SUPERALLOY, SlimefunItems.HAZMAT_BOOTS, Items.OSMIUM_SUPERALLOY,
            Items.OSMIUM_SUPERALLOY, Items.POWER_SUIT_GENERATOR, Items.OSMIUM_SUPERALLOY
        }, ArmorPiece.FEET).register(addon);

        new ModuleManipulator().register(addon);
        new ModuleManipulator().register(addon);
    }

    static void setupResearches() {
        addResearch("Weapons of Mass Destruction", 70, Items.NUCLEAR_BOMB, Items.BOOMINATOR_9000);
        addResearch("I am Iron Man", 70, Items.POWER_SUIT_HELMET, Items.POWER_SUIT_CHESTPLATE, Items.POWER_SUIT_LEGGINGS, Items.POWER_SUIT_BOOTS);
        addResearch("Energy Weapons", 45, Items.ENERGY_BLADE, Items.ENERGY_RECTIFIER, Items.ENERGY_RIFLE);
        addResearch("Alien Metals", 40, Items.OSMIUM_DUST, Items.OSMIUM_INGOT, Items.OSMIUM_SUPERALLOY);
        addResearch("Rare Earths", 50, Items.MONAZITE, Items.LANTHANUM_INGOT, Items.NEODYMIUM_INGOT, Items.GADOLINIUM_INGOT, Items.TERBIUM_INGOT);
    }

    private static void addResearch(String name, int xp, SlimefunItemStack... items) {
        Research research = new Research(
            AbstractAddon.createKey(name.toLowerCase(Locale.ROOT).replace(' ', '_')),
            researchId++,
            name,
            xp
        );
        research.addItems(items);
        research.register();
    }

    @SafeVarargs
    private static <T> T[] fillNulls(T... original) {
        return Arrays.copyOf(original, 9);
    }
}
