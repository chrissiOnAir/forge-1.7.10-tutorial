package com.chrissionair.tutorial;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TutorialTabBlocks extends CreativeTabs {

	public TutorialTabBlocks(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return Tutorial.tutorialIngot;
	}

}
