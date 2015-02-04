package com.chrissionair.tutorial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TutorialOre extends Block {

	public TutorialOre(Material material) {
		
		super(material);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("tutorialOre");
		setCreativeTab(Tutorial.tutorialTabBlocks);
		setHarvestLevel("pickaxe", 3);
		
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
	        return Tutorial.tutorialIngot;
	}
	
}
