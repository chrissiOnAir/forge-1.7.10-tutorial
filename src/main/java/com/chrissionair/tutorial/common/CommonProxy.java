package com.chrissionair.tutorial.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.chrissionair.tutorial.Tutorial;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
	public static void loadRecipes() {
		
		ItemStack blackWoolStack = new ItemStack(Blocks.wool, 32, 15);
		ItemStack logStack = new ItemStack(Blocks.log, 8);
		ItemStack leavesStack = new ItemStack(Blocks.leaves, 16);
		ItemStack goldIngotStack = new ItemStack(Items.gold_ingot, 3);
		ItemStack dirtStack = new ItemStack(Blocks.dirt);
		
		GameRegistry.addShapelessRecipe(dirtStack, new Object[]{
				blackWoolStack, logStack, leavesStack, goldIngotStack
		});
		
		GameRegistry.addRecipe(dirtStack, new Object[]{
				"A  ", " B ", "  C", 'A', Blocks.log, 'B', Blocks.leaves, 'C', Items.gold_ingot
		});
		
		// FurnaceRecipes.smelting().func_151393_a(Blocks.leaves, new ItemStack(Items.gold_ingot), 0.35f);
		GameRegistry.addSmelting(Blocks.leaves, new ItemStack(Items.gold_ingot), 0.35f);
		
		for (int i = 0; i < 3; i++) {
			ItemStack wool = new ItemStack(Blocks.wool, 1, i);
			ItemStack multiBlockStack = new ItemStack(Tutorial.tutorialMultiBlock, 1, i);
			
			GameRegistry.addShapelessRecipe(multiBlockStack, wool, wool);
		}
		
	}
	
	public void doSomething() {
		// do something on both 
	}
	
	public void preInit() {
		// register my Items, Blocks, Entities, etc
			
	}
	  
	public void init() {
		// theoretisch könnte das FMLInitializationEvent event mitgegeben werden, aber wozu?
		// register my Recipies
		loadRecipes();
	}
	  
	public void postInit() {
		
	}

}
