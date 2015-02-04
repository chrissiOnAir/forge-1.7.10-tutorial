package com.chrissionair.tutorial;

import static net.minecraft.world.biome.BiomeGenBase.forestHills;
import static net.minecraft.world.biome.BiomeGenBase.jungle;
import static net.minecraft.world.biome.BiomeGenBase.jungleHills;
import static net.minecraft.world.biome.BiomeGenBase.plains;
import static net.minecraft.world.biome.BiomeGenBase.taiga;
import static net.minecraft.world.biome.BiomeGenBase.taigaHills;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;

import com.chrissionair.tutorial.common.CommonProxy;
import com.chrissionair.tutorial.event.Tutorial_Biome_Event;
import com.chrissionair.tutorial.event.Tutorial_Bonemeal_Event;
import com.chrissionair.tutorial.event.Tutorial_Decorate_Event;
import com.chrissionair.tutorial.world.BiomeGenTutorial;
import com.chrissionair.tutorial.world.TutorialWorldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Tutorial.MODID, name = Tutorial.MODNAME, version = Tutorial.MODVERSION)
public class Tutorial
{
	public static final String MODBASE = "chrissionair";
    public static final String MODID = "tutorial";
    public static final String MODNAME = "My First Mod is a Tutorial Mod!";
    public static final String MODVERSION = "1.0";
    
    @Mod.Instance(MODID)
    public static Tutorial instance; 
    
    @SidedProxy(clientSide="com." + MODBASE + "." + MODID + ".clientonly.CombinedClientProxy", serverSide="com." + MODBASE + "." + MODID + ".serveronly.DedicatedServerProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tutorialTab = new TutorialTab(CreativeTabs.getNextID(), "tutorialTab");
    public static CreativeTabs tutorialTabBlocks = new TutorialTabBlocks(CreativeTabs.getNextID(), "tutorialTabBlocks");
  
    public static Item tutorialShears;
    public static Item tutorialApple;
    public static Item tutorialIngot;
    public static Item tutorialCarrot;
    
    public static Block tutorialGrass;
    public static Block tutorialDirt;
    public static Block tutorialOre;
    public static Block tutorialLog;
    public static Block tutorialLeaves;
    public static Block tutorialSapling;
    //public static Block tutorialSaplingAcacia;
    //public static Block tutorialSaplingFNDesert;
    // item carrot und block carrots
    public static Block tutorialCarrots;
    
    public static Block tutorialMultiBlock;
    
    public static BiomeGenBase tutorialBiome;
    
    TutorialWorldGenerator tutorialWorldGenerator = new TutorialWorldGenerator();
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		tutorialShears = new TutorialItem().setMaxStackSize(1).setUnlocalizedName("tutorialShears").setTextureName(MODID + ":" + MODID + "_shears");
		tutorialApple = new TutorialItem().setUnlocalizedName("tutorialApple").setTextureName(MODID + ":" + MODID + "_apple");
		tutorialIngot = new TutorialItem().setUnlocalizedName("tutorialIngot").setTextureName(MODID + ":" + MODID + "_ingot");
		
		GameRegistry.registerItem(tutorialShears, tutorialShears.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(tutorialApple, tutorialApple.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(tutorialIngot, tutorialIngot.getUnlocalizedName().substring(5));
		
		tutorialGrass = new TutorialGrass().setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("tutorialGrass").setBlockTextureName(MODID + ":" + MODID + "_grass");
		tutorialDirt = new TutorialBlock(Material.ground).setHardness(0.5F).setStepSound(Block.soundTypeGravel).setBlockName("tutorialDirt").setBlockTextureName(MODID + ":" + MODID + "_dirt");
		tutorialDirt.setHarvestLevel("shovel", 0);		
		tutorialOre = new TutorialOre(Material.rock).setBlockTextureName(MODID + ":" + MODID + "_ore");
		tutorialLog = new TutorialLogExt().setBlockName("tutorialLog").setBlockTextureName(MODID + ":" + MODID + "_log").setCreativeTab(tutorialTabBlocks);
		tutorialLeaves = new TutorialLeaves01().setBlockName("tutorialLeaves").setBlockTextureName(MODID + ":" + MODID + "_leaves").setCreativeTab(tutorialTabBlocks);
		tutorialSapling = new TutorialSapling01().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("tutorialSapling").setBlockTextureName(MODID + ":" + MODID + "_sapling");
		//tutorialSaplingAcacia = new TutorialSapling01().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("tutorialSaplingAcacia").setBlockTextureName(MODID + ":" + MODID + "_sapling");
		//tutorialSaplingFNDesert = new TutorialSapling01().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("tutorialSaplingFNDesert").setBlockTextureName(MODID + ":" + MODID + "_sapling");
		tutorialMultiBlock = new TutorialMultiBlock(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth);
		tutorialCarrots = new TutorialCarrots().setBlockName("tutorialCarrots").setBlockTextureName(MODID + ":" + MODID + "_carrots");
		
		GameRegistry.registerBlock(tutorialMultiBlock, TutorialItemMultiBlock.class, tutorialMultiBlock.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialGrass, tutorialGrass.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialDirt, tutorialDirt.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialOre, tutorialOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialLog, TutorialLogItem.class, tutorialLog.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialLeaves, TutorialLeavesItem.class, tutorialLeaves.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialSapling, TutorialSaplingItem.class, tutorialSapling.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(tutorialSaplingAcacia, TutorialSaplingItem.class, tutorialSaplingAcacia.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(tutorialSaplingFNDesert, TutorialSaplingItem.class, tutorialSaplingFNDesert.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tutorialCarrots, tutorialCarrots.getUnlocalizedName().substring(5));

		// Achtung: Block muss zuerst initialisiert sein!
		tutorialCarrot = (new ItemSeedFood(4, 0.6F, Tutorial.tutorialCarrots, Blocks.farmland)).setUnlocalizedName("tutorialCarrot").setTextureName(MODID + ":" + MODID + "_carrot").setCreativeTab(tutorialTab);
		GameRegistry.registerItem(tutorialCarrot, tutorialCarrot.getUnlocalizedName().substring(5));
				
		GameRegistry.registerWorldGenerator(tutorialWorldGenerator, 0);
		
		// tutorialBiome = (new BiomeGenPlains(100)).setColor(9286496).setBiomeName("TutorialBiome");
		tutorialBiome = (new BiomeGenTutorial(100)).setBiomeName("TutorialBiome").setColor(0x893F8B).setHeight(new BiomeGenBase.Height(0.1F, 0.5F)).setTemperatureRainfall(0.9F, 0.9F);
		
		BiomeDictionary.registerBiomeType(tutorialBiome, Type.PLAINS);
		
		// forest, plains, taiga, taigaHills, forestHills, jungle, jungleHills
		BiomeManager.addSpawnBiome(tutorialBiome);
		
		BiomeManager.removeSpawnBiome(taigaHills);
		BiomeManager.removeSpawnBiome(forestHills);
		BiomeManager.removeSpawnBiome(jungle);
		BiomeManager.removeSpawnBiome(jungleHills);
		// BiomeManager.removeSpawnBiome(forest);
		BiomeManager.removeSpawnBiome(plains);
		BiomeManager.removeSpawnBiome(taiga);
		/*
		for (int i = 0; i < WorldChunkManager.allowedBiomes.size(); i++) {
			System.out.println(WorldChunkManager.allowedBiomes.get(i).biomeName);
		}
		*/
		
		// Event
		MinecraftForge.EVENT_BUS.register(new Tutorial_Bonemeal_Event());
		
		MinecraftForge.TERRAIN_GEN_BUS.register(new Tutorial_Decorate_Event());
		//MinecraftForge.TERRAIN_GEN_BUS.register(new Tutorial_Biome_Event());
		
		// base color in Block ist FFFFFF = weiss!
		//System.out.println(Integer.toHexString(16777215));
		
	
		
	    proxy.preInit();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
	    proxy.init();
	    
	    
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    proxy.postInit();

	    
	}

	
    
}
