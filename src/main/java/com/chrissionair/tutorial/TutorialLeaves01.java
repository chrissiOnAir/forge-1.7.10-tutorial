package com.chrissionair.tutorial;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TutorialLeaves01 extends BlockLeaves
{
    public static final String[][] field_150130_N = new String[][] {{"yellow", "blue"}, {"yellow_opaque", "blue_opaque"}};
    public static final String[] field_150131_O = new String[] {"yellow", "blue"};
    // private static final String __OBFID = "CL_00000280";

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
    	// getFoliageColorPine() = 0x619961 (6396257)
    	// getFoliageColorBirch() = 0x80a755 (8431445)
    	// getFoliageColorBasic() = 0x48b518 (4764952)
    	// gelb: FFFF00
    	// blue: 0000FF
    	// red: FF0000
    	
    	// return ColorizerFoliage.getFoliageColorBasic();
        // return (p_149741_1_ & 3) == 0 ? 0xFFFFFF : ((p_149741_1_ & 3) == 1 ? 0xFFFFFF : 0xFFFFFF);
    	/*
    	String hex = "2A"; //The answer is 42  
    	int intValue = Integer.parseInt(hex, 16); 
    	String hex = Integer.toHexString(42);
    	*/
    	//System.out.println(Integer.toHexString(ColorizerFoliage.getFoliageColorBasic()));
    	//System.out.println(Integer.parseInt("80a755", 16));
    	// super.getRenderColor(p_149741_1_)
    	return (p_149741_1_ & 3) == 0 ? 0xFFFFFF : ((p_149741_1_ & 3) == 1 ? 0x0000FF : super.getRenderColor(p_149741_1_));
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int l = p_149720_1_.getBlockMetadata(p_149720_2_, p_149720_3_, p_149720_4_);
        return (l & 3) == 0 ? 0xFFFFFF : ((l & 3) == 1 ? 0x0000FF : super.colorMultiplier(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_));
        // return (l & 3) == 0 ? 0xF9FE89 : ((l & 3) == 1 ? 0 : 0);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		
    	return Item.getItemFromBlock(Tutorial.tutorialSapling);
    }
    
    @Override
    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_)
    {
        if ((p_150124_5_ & 3) == 0 && p_150124_1_.rand.nextInt(p_150124_6_) == 0)
        {
            this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Tutorial.tutorialApple, 1, 0));
        }
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) & 3;
    }
    
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
    	// System.out.println(Blocks.leaves.isOpaqueCube());
    	this.setGraphicsLevel(!(Blocks.leaves.isOpaqueCube()));
    	
        return (p_149691_2_ & 3) == 0 ? this.field_150129_M[this.field_150127_b][0] : this.field_150129_M[this.field_150127_b][1];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for (int i = 0; i < field_150130_N.length; ++i)
        {
            this.field_150129_M[i] = new IIcon[field_150130_N[i].length];

            for (int j = 0; j < field_150130_N[i].length; ++j)
            {
                this.field_150129_M[i][j] = p_149651_1_.registerIcon(this.getTextureName() + "_" + field_150130_N[i][j]);
            }
        }
    }

    public String[] func_150125_e()
    {
        return field_150131_O;
    }
}