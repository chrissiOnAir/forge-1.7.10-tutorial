package com.chrissionair.tutorial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class TutorialSaplingItem extends ItemBlock {

	public TutorialSaplingItem(Block metaBlock) {
		super(metaBlock);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1)
    {
    	// this.Block.getIcon
        return this.field_150939_a.func_149735_b(2, par1);
    }
	
    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int par1)
    {
    	// System.out.println(par1);
        return par1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
    	//getItemDamage()
        int i = par1ItemStack.getItemDamage();
        return super.getUnlocalizedName() + "." + TutorialSapling01.field_149882_a[i];
    }

}
