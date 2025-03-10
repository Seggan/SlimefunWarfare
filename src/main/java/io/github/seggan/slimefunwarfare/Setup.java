package io.github.seggan.slimefunwarfare;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.georesources.Arsenic;
import io.github.seggan.slimefunwarfare.georesources.Monazite;
import io.github.seggan.slimefunwarfare.items.*;
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
import io.github.seggan.slimefunwarfare.machines.*;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.VanillaItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public final class Setup {

    private static int researchId = 3478;

    static void setupItems(SlimefunWarfare addon) {
        new SlimefunItem(Categories.RESOURCES, Items.BORAX, RecipeType.NULL, fillNulls(
            CustomItemStack.create(
                Material.STONE,
                "&fStone drop",
                "",
                "&7This item is dropped from stone"
            )
        )).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.BORON, RecipeType.SMELTERY, fillNulls(Items.BORAX.item())).register(addon);
        new SlimefunItem(Categories.GENERAL, Items.SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
            SlimefunItems.STEEL_INGOT.item(), new ItemStack(Material.SLIME_BALL), null,
            null, null, null,
            null, null, null
        }).register(addon);

        new SlimefunItem(
            Categories.GENERAL, Items.REINFORCED_SLIMESTEEL, RecipeType.SMELTERY, new ItemStack[]{
            Items.SLIMESTEEL.item(), new ItemStack(Material.SLIME_BLOCK), SlimefunItems.DAMASCUS_STEEL_INGOT.item(),
            SlimefunItems.HARDENED_METAL_INGOT.item(), SlimefunItems.CORINTHIAN_BRONZE_INGOT.item(), SlimefunItems.ALUMINUM_BRONZE_INGOT.item(),
            null, null, null
        }).register(addon);

        new VanillaItem(Categories.GENERAL, new ItemStack(Material.SLIME_BALL), "SLIME_BALL", RecipeType.ENHANCED_CRAFTING_TABLE, fillNulls(
            Items.BORAX.item(), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.WATER_BUCKET)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.SCOPE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.PLASTIC_SHEET.item(), SlimefunItems.MULTIMETER.item(), SlimefunItems.PLASTIC_SHEET.item(),
            SlimefunItems.HARDENED_GLASS.item(), null, SlimefunItems.HARDENED_GLASS.item(),
            SlimefunItems.PLASTIC_SHEET.item(), SlimefunItems.PLASTIC_SHEET.item(), SlimefunItems.PLASTIC_SHEET.item()
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(),
            null, null, null,
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item()
        }).register(addon);

        new SlimefunItem(
            Categories.GENERAL, Items.ADVANCED_BARREL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL.item(), Items.REINFORCED_SLIMESTEEL.item(), Items.REINFORCED_SLIMESTEEL.item(),
            Items.BARREL.item(), Items.BARREL.item(), Items.BARREL.item(),
            Items.REINFORCED_SLIMESTEEL.item(), Items.REINFORCED_SLIMESTEEL.item(), Items.REINFORCED_SLIMESTEEL.item()
        }).register(addon);

        new ElementForge(Categories.POWER_SUITS, Items.ELEMENT_FORGE).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.FIBER_OPTIC_GLASS, RecipeType.SMELTERY, fillNulls(
            Items.ERBIUM_INGOT.item(), SlimefunItems.SILICON.item(), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS_PANE)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.FIBER_OPTIC_CABLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.SILVER_INGOT.item(), SlimefunItems.SILVER_INGOT.item(), SlimefunItems.SILVER_INGOT.item(),
            Items.FIBER_OPTIC_GLASS.item(), Items.FIBER_OPTIC_GLASS.item(), Items.FIBER_OPTIC_GLASS.item(),
            SlimefunItems.SILVER_INGOT.item(), SlimefunItems.SILVER_INGOT.item(), SlimefunItems.SILVER_INGOT.item()
        }, CustomItemStack.create(Items.FIBER_OPTIC_CABLE.item(), 3)).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.LASER_DIODE, RecipeType.SMELTERY, fillNulls(
            SlimefunItems.SYNTHETIC_SAPPHIRE.item(), new ItemStack(Material.GLOWSTONE),
            CustomItemStack.create(Items.YTTERBIUM_INGOT.item(), 2), Items.FIBER_OPTIC_GLASS.item()
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.ULTRA_MAGNET, RecipeType.SMELTERY, fillNulls(
            SlimefunItems.MAGNET.item(), Items.NDFEB_ALLOY.item(), CustomItemStack.create(Items.HOLMIUM_INGOT.item(), 2)
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
            null, Items.OSMIUM_INGOT.item(), null,
            null, Items.OSMIUM_INGOT.item(), null,
            null, new ItemStack(Material.STICK), null
        }).register(addon);

        // Energy
        new SlimefunItem(Categories.GENERAL, Items.OSMIUM_SUPERALLOY, RecipeType.SMELTERY, fillNulls(
            Items.OSMIUM_INGOT.item(), Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.REINFORCED_ALLOY_INGOT.item(),
            Items.OSMIUM_DUST.item(), Items.GADOLINIUM_INGOT.item(), Items.TERBIUM_INGOT.item()
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.ENERGY_RECTIFIER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.CARBONADO_EDGED_CAPACITOR.item(), Items.OSMIUM_SUPERALLOY.item(),
            SlimefunItems.POWER_CRYSTAL.item(), SlimefunItems.ENERGY_REGULATOR.item(), SlimefunItems.POWER_CRYSTAL.item(),
            Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.CARBONADO_EDGED_CAPACITOR.item(), Items.OSMIUM_SUPERALLOY.item()
        }).register(addon);

        new EnergyBlade().register(addon);

        // Misc
        new Dummy().register(addon);
    }

    static void setupBullets(SlimefunWarfare addon) {
        new BulletPress().register(addon);
        new Bullet(Items.IRON_BULLET, new ItemStack(Material.IRON_INGOT), 0.75, false).register(addon);
        new Bullet(Items.LEAD_BULLET, SlimefunItems.LEAD_INGOT.item(), 1, false).register(addon);
        new Bullet(Items.DU_BULLET, SlimefunItems.SMALL_URANIUM.item(), 1.5, true).register(addon);
        new Bullet(Items.GOLD_BULLET, SlimefunItems.GOLD_12K.item(), 2, false).register(addon);
        new Bullet(Items.TRINITROBULLETENE, Items.PYRO_POWDER.item(), 2.75, true).register(addon);
    }

    static void setupGuns(SlimefunWarfare addon) {
        new SlimefunItem(Categories.GENERAL, Items.GUN_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.SLIMESTEEL.item(), new ItemStack(Material.GUNPOWDER), Items.SLIMESTEEL.item(),
            Items.SLIMESTEEL.item(), new ItemStack(Material.FLINT_AND_STEEL), Items.SLIMESTEEL.item(),
            SlimefunItems.PLASTIC_SHEET.item(), new ItemStack(Material.CROSSBOW), SlimefunItems.PLASTIC_SHEET.item()
        }).register(addon);

        new Gun(Items.PISTOL, new ItemStack[]{
            null, Items.SLIMESTEEL.item(), null,
            null, Items.GUN_CASE.item(), Items.SLIMESTEEL.item(),
            null, Items.SLIMESTEEL.item(), new ItemStack(Material.STICK)
        }, 10, 6, 0.5).register(addon);

        new Gun(Items.REVOLVER, new ItemStack[]{
            null, Items.SLIMESTEEL.item(), null,
            null, Items.PISTOL.item(), Items.SLIMESTEEL.item(),
            null, Items.SLIMESTEEL.item(), null
        }, 10, 6, 0.3).register(addon);

        new Gun(Items.MACHINE_GUN, new ItemStack[]{
            Items.SLIMESTEEL.item(), Items.SCOPE.item(), null,
            Items.BARREL.item(), Items.REVOLVER.item(), Items.SLIMESTEEL.item(),
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 30, 5, 6, 0.15).register(addon);

        new Gun(Items.MINIGUN, new ItemStack[]{
            Items.REINFORCED_SLIMESTEEL.item(), Items.SCOPE.item(), Items.REINFORCED_SLIMESTEEL.item(),
            Items.ADVANCED_BARREL.item(), Items.MACHINE_GUN.item(), Items.REINFORCED_SLIMESTEEL.item(),
            Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 40, 5, 8, 0).register(addon);

        new Gun(Items.RIFLE, new ItemStack[]{
            null, Items.SCOPE.item(), null,
            Items.BARREL.item(), Items.GUN_CASE.item(), Items.SLIMESTEEL.item(),
            null, Items.SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 40, 5, 8, 0.75).register(addon);

        new Gun(Items.SHOTGUN, new ItemStack[]{
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), null,
            Items.BARREL.item(), Items.BARREL.item(), Items.GUN_CASE.item(),
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 25, 5, 13, 1.25).register(addon);

        new Gun(Items.ASSAULT_RIFLE, new ItemStack[]{
            Items.SLIMESTEEL.item(), Items.SCOPE.item(), new ItemStack(Material.OAK_PLANKS),
            Items.BARREL.item(), Items.RIFLE.item(), Items.REINFORCED_SLIMESTEEL.item(),
            Items.SLIMESTEEL.item(), Items.SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 50, 3, 13, 0.3).register(addon);

        new Gun(Items.SNIPER, new ItemStack[]{
            null, Items.REINFORCED_SLIMESTEEL.item(), Items.REINFORCED_SLIMESTEEL.item(),
            Items.ADVANCED_BARREL.item(), Items.ADVANCED_BARREL.item(), Items.ASSAULT_RIFLE.item(),
            new ItemStack(Material.STICK), Items.REINFORCED_SLIMESTEEL.item(), SlimefunItems.PLASTIC_SHEET.item()
        }, 130, 50, 22, 8).register(addon);

        new EnergyRifle().register(addon);
    }

    static void setupExplosives(SlimefunWarfare addon) {
        new AirLiquefier().register(addon);
        new ExplosiveSynthesizer().register(addon);
        new Boominator9000().register(addon);

        new SlimefunItem(Categories.GENERAL, Items.REINFORCED_CONCRETE, RecipeType.SMELTERY,
            new ItemStack[]{
                SlimefunItems.IRON_DUST.item(), new ItemStack(Material.GRAY_CONCRETE_POWDER), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.LIQUID_AIR, RecipeTypes.AIR_LIQUEFIER,
            new ItemStack[]{
                SlimefunItems.TIN_CAN.item(), null, null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.LIQUID_NITROGEN, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.LIQUID_AIR.item(), Items.LIQUID_AIR.item(), Items.LIQUID_AIR.item(),
                Items.LIQUID_AIR.item(), Items.LIQUID_AIR.item(), Items.LIQUID_AIR.item(),
                Items.LIQUID_AIR.item(), null, null
            }, CustomItemStack.create(Items.LIQUID_NITROGEN.item(), 4)).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.PURIFIED_LIQUID_NITROGEN, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item(),
                Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item(),
                Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item(), Items.LIQUID_NITROGEN.item()
            }, CustomItemStack.create(Items.PURIFIED_LIQUID_NITROGEN.item(), 4)).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.NITROGEN_TRIIODIDE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                Items.LIQUID_NITROGEN.item(), new ItemStack(Material.DRIED_KELP), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.AZIDOAZIDE_AZIDE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                Items.PURIFIED_LIQUID_NITROGEN.item(), new ItemStack(Material.COAL), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.ARSENIC, RecipeType.GEO_MINER, new ItemStack[9]
        ).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.THIOACETONE, RecipeTypes.EXPLOSIVE_SYNTHESIZER,
            new ItemStack[]{
                SlimefunItems.OIL_BUCKET.item(), SlimefunItems.SULFATE.item(), null,
                null, null, null,
                null, null, null
            }).register(addon);

        new Arsenic(Items.ARSENIC.item()).register();

        new VanillaItem(
            Categories.EXPLOSIVES, new ItemStack(Material.GUNPOWDER),
            "GUNPOWDER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.SULFATE.item(), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.CHARCOAL),
            null, null, null,
            null, null, null
        }).register(addon);

        new SlimefunItem(Categories.EXPLOSIVES, Items.PYRO_POWDER, RecipeType.GRIND_STONE,
            fillNulls(new ItemStack(Material.TNT)), CustomItemStack.create(Items.PYRO_POWDER.item(), 4)).register(addon);

        new RadioactiveItem(Categories.EXPLOSIVES, Items.ENRICHED_URANIUM, RecipeTypes.BOOMINATOR,
            fillNulls(SlimefunItems.BOOSTED_URANIUM.item()), Radioactivity.VERY_DEADLY).register(addon);

        new SlimefunItem(
            Categories.EXPLOSIVES, Items.EMPTY_GRENADE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                Items.PYRO_POWDER.item(), SlimefunItems.STEEL_INGOT.item(), Items.PYRO_POWDER.item(),
                SlimefunItems.STEEL_INGOT.item(), SlimefunItems.TIN_CAN.item(), SlimefunItems.STEEL_INGOT.item(),
                Items.PYRO_POWDER.item(), SlimefunItems.STEEL_INGOT.item(), Items.PYRO_POWDER.item(),
            }, CustomItemStack.create(Items.EMPTY_GRENADE.item(), 4)).register(addon);

        new Grenade(Items.NITROGEN_TRIIODIDE).register(addon);
        new Grenade(Items.AZIDOAZIDE_AZIDE).register(addon);
        new Grenade(Items.ARSENIC).register(addon);
        new Grenade(Items.PYRO_POWDER).register(addon);
        new Grenade(Items.THIOACETONE).register(addon);
        new Grenade(Items.OSMIUM_DUST).register(addon);

        new NuclearBomb(Categories.EXPLOSIVES, Items.NUCLEAR_BOMB, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.STEEL_PLATE.item(), Items.ENRICHED_URANIUM.item(), SlimefunItems.STEEL_PLATE.item(),
                new ItemStack(Material.PISTON), Items.ENRICHED_URANIUM.item(), new ItemStack(Material.PISTON),
                SlimefunItems.STEEL_PLATE.item(), Items.ENRICHED_URANIUM.item(), SlimefunItems.STEEL_PLATE.item()
            }).register(addon);
    }

    static void setupSpace(SlimefunWarfare addon) {
        new Meteor(Items.OSMIUM_METEOR).register(addon);
        new Meteor(Items.SEGGANESSON_METEOR).register(addon);

        new SlimefunItem(Categories.RESOURCES, Items.OSMIUM_DUST, RecipeType.ORE_CRUSHER, fillNulls(Items.OSMIUM_METEOR.item())).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.OSMIUM_INGOT, RecipeType.SMELTERY, fillNulls(Items.OSMIUM_DUST.item())).register(addon);
        new SlimefunItem(Categories.RESOURCES, Items.SEGGANESSON, RecipeType.ORE_CRUSHER, fillNulls(Items.SEGGANESSON_METEOR.item())).register(addon);

        new IonExchangeSeparator().energyPerTick(128).register(addon);

        new Monazite(Items.MONAZITE.item()).register();
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
            CustomItemStack.create(Items.TERBIUM_INGOT.item(), 3), CustomItemStack.create(Items.GADOLINIUM_INGOT.item(), 2), new ItemStack(Material.IRON_INGOT),
            SlimefunItems.COBALT_INGOT.item(), CustomItemStack.create(Items.DYSPROSIUM_INGOT.item(), 2)
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.TERFENOL_D_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.TERFENOL_D.item(), Items.TERFENOL_D.item(), Items.TERFENOL_D.item(),
            Items.TERFENOL_D.item(), Items.TERFENOL_D.item(), Items.TERFENOL_D.item(),
            Items.TERFENOL_D.item(), Items.TERFENOL_D.item(), Items.TERFENOL_D.item()
        }).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.NDFEB_ALLOY, RecipeType.SMELTERY, fillNulls(
            CustomItemStack.create(Items.NEODYMIUM_INGOT.item(), 4), Items.BORON.item(),
            new ItemStack(Material.IRON_INGOT), Items.DYSPROSIUM_INGOT.item()
        )).register(addon);

        new SlimefunItem(Categories.GENERAL, Items.NDFEB_ALLOY_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item(),
            Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item(),
            Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item(), Items.NDFEB_ALLOY.item()
        }).register(addon);

        new MeteorAttractor().register(addon);

        new ElementalReactor().register(addon);
    }

    static void setupSuits(SlimefunWarfare addon) {
        new SlimefunItem(Categories.RESOURCES, Items.UNPATENTABLIUM, RecipeTypes.ELEMENT_FORGE, new ItemStack[]{
            Items.SEGGANESSON.item(), Items.ARSENIC.item(), Items.SEGGANESSON.item(),
            Items.OSMIUM_DUST.item(), Items.OSMIUM_INGOT.item(), Items.OSMIUM_DUST.item(),
            Items.SEGGANESSON.item(), Items.ARSENIC.item(), Items.SEGGANESSON.item()
        }).register(addon);

        new SlimefunItem(Categories.POWER_SUITS, Items.POWER_SUIT_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.UNPATENTABLIUM.item(), Items.UNPATENTABLIUM.item(), Items.UNPATENTABLIUM.item(),
            Items.SEGGANESSON.item(), SlimefunItems.NETHER_STAR_REACTOR.item(), Items.SEGGANESSON.item(),
            Items.UNPATENTABLIUM.item(), Items.LASER_DIODE.item(), Items.UNPATENTABLIUM.item()
        }).register(addon);

        new SlimefunItem(Categories.POWER_SUITS, Items.MODULE_CASE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, Items.OSMIUM_INGOT.item(), null,
            Items.FIBER_OPTIC_CABLE.item(), Items.POWER_SUIT_GENERATOR.item(), Items.FIBER_OPTIC_CABLE.item(),
            null, Items.OSMIUM_INGOT.item(), null
        }, CustomItemStack.create(Items.MODULE_CASE.item(), 2)).register(addon);

        new PowerSuit(Items.POWER_SUIT_HELMET, new ItemStack[]{
            SlimefunItems.ADVANCED_CIRCUIT_BOARD.item(), Items.POWER_SUIT_GENERATOR.item(), SlimefunItems.ADVANCED_CIRCUIT_BOARD.item(),
            Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.SCUBA_HELMET.item(), Items.OSMIUM_SUPERALLOY.item(),
            Items.FIBER_OPTIC_CABLE.item(), Items.OSMIUM_SUPERALLOY.item(), Items.FIBER_OPTIC_CABLE.item()
        }, ArmorPiece.HEAD).register(addon);

        new PowerSuit(Items.POWER_SUIT_CHESTPLATE, new ItemStack[]{
            Items.OSMIUM_SUPERALLOY.item(), Items.ULTRA_MAGNET.item(), Items.OSMIUM_SUPERALLOY.item(),
            Items.POWER_SUIT_GENERATOR.item(), SlimefunItems.HAZMAT_CHESTPLATE.item(), Items.POWER_SUIT_GENERATOR.item(),
            Items.LASER_DIODE.item(), Items.SEGGANESSON.item(), Items.LASER_DIODE.item()
        }, ArmorPiece.CHEST).register(addon);

        new PowerSuit(Items.POWER_SUIT_LEGGINGS, new ItemStack[]{
            SlimefunItems.ELECTRIC_MOTOR.item(), Items.POWER_SUIT_GENERATOR.item(), SlimefunItems.ELECTRIC_MOTOR.item(),
            Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.HAZMAT_LEGGINGS.item(), Items.OSMIUM_SUPERALLOY.item(),
            Items.FIBER_OPTIC_CABLE.item(), null, Items.FIBER_OPTIC_CABLE.item()
        }, ArmorPiece.LEGS).register(addon);

        new PowerSuit(Items.POWER_SUIT_BOOTS, new ItemStack[]{
            null, null, null,
            Items.OSMIUM_SUPERALLOY.item(), SlimefunItems.HAZMAT_BOOTS.item(), Items.OSMIUM_SUPERALLOY.item(),
            Items.OSMIUM_SUPERALLOY.item(), Items.POWER_SUIT_GENERATOR.item(), Items.OSMIUM_SUPERALLOY.item()
        }, ArmorPiece.FEET).register(addon);

        new ModuleManipulator().register(addon);
    }

    static void setupResearches() {
        addResearch("Weapons of Mass Destruction", 70, Items.NUCLEAR_BOMB.item(), Items.BOOMINATOR_9000.item());
        addResearch("I am Iron Man", 70, Items.POWER_SUIT_HELMET.item(), Items.POWER_SUIT_CHESTPLATE.item(), Items.POWER_SUIT_LEGGINGS.item(), Items.POWER_SUIT_BOOTS.item());
        addResearch("Energy Weapons", 45, Items.ENERGY_BLADE.item(), Items.ENERGY_RECTIFIER.item(), Items.ENERGY_RIFLE.item());
        addResearch("Alien Metals", 40, Items.OSMIUM_DUST.item(), Items.OSMIUM_INGOT.item(), Items.OSMIUM_SUPERALLOY.item());
        addResearch("Rare Earths", 50, Items.MONAZITE.item(), Items.LANTHANUM_INGOT.item(), Items.NEODYMIUM_INGOT.item(), Items.GADOLINIUM_INGOT.item(), Items.TERBIUM_INGOT.item());
    }

    private static void addResearch(String name, int xp, ItemStack... items) {
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
