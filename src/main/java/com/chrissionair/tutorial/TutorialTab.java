package com.chrissionair.tutorial;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TutorialTab extends CreativeTabs {

	public TutorialTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return Tutorial.tutorialApple;
	}

}
