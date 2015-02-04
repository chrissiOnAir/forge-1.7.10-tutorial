package com.chrissionair.tutorial;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TutorialItem extends Item {

	public TutorialItem() {
		
		maxStackSize = 64;
		setCreativeTab(Tutorial.tutorialTab);
		setUnlocalizedName("tutorialItem");
		
	}
	
}
