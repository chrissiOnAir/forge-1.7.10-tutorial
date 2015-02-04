package com.chrissionair.tutorial;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TutorialBlock extends Block {

	public TutorialBlock(Material material) {
		
		super(material);
		setCreativeTab(Tutorial.tutorialTabBlocks);
		// setHarvestLevel("shovel", 0);
		
	}
	
}
