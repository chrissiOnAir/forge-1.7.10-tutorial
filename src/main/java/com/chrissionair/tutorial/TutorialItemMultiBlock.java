package com.chrissionair.tutorial;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TutorialItemMultiBlock extends ItemBlock {
	
	// protected weil in FirstmodMultiBlock.registerIcons
	protected final static String[] subNames = {
		"first", "second",  "third"
	};
	
	public TutorialItemMultiBlock(Block multiBlock) {
		
		super(multiBlock);
		setHasSubtypes(true);
		// setUnlocalizedName("tutorialMultiBlock");
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}

}
