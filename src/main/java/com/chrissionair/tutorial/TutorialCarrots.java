package com.chrissionair.tutorial;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TutorialCarrots extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149868_a;
    private static final String __OBFID = "CL_00000212";

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 7)
        {
            if (p_149691_2_ == 6)
            {
                p_149691_2_ = 5;
            }

            return this.field_149868_a[p_149691_2_ >> 1];
        }
        else
        {
            return this.field_149868_a[3];
        }
    }

    protected Item func_149866_i()
    {
        return Tutorial.tutorialCarrot;
    }

    protected Item func_149865_P()
    {
        return Tutorial.tutorialCarrot;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149868_a = new IIcon[4];

        for (int i = 0; i < this.field_149868_a.length; ++i)
        {
            this.field_149868_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
}