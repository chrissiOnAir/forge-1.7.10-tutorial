package com.chrissionair.tutorial;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TutorialLeavesItem extends ItemBlock {

	public TutorialLeavesItem(Block metaBlock) {
		super(metaBlock);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = par1ItemStack.getItemDamage();
        return super.getUnlocalizedName() + "." + TutorialLeaves01.field_150131_O[i];
    }

}
