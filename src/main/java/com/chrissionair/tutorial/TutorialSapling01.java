package com.chrissionair.tutorial;

import java.util.List;
import java.util.Random;

import com.chrissionair.tutorial.world.WorldGenCanopyTree_Tut;
import com.chrissionair.tutorial.world.WorldGenFNBeech;
import com.chrissionair.tutorial.world.WorldGenFNDesert;
import com.chrissionair.tutorial.world.WorldGenFNSequoia;
import com.chrissionair.tutorial.world.WorldGenFNWTEucalyptus;
import com.chrissionair.tutorial.world.WorldGenFNWillow;
import com.chrissionair.tutorial.world.WorldGenMegaJungle_Tut;
import com.chrissionair.tutorial.world.WorldGenMegaPineTree_Tut;
import com.chrissionair.tutorial.world.WorldGenSavannaTree_Tut;
import com.chrissionair.tutorial.world.WorldGenTaiga1_Tut;
import com.chrissionair.tutorial.world.WorldGenTaiga2_Tut;
import com.chrissionair.tutorial.world.WorldGenTreesYellow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TutorialSapling01 extends BlockBush implements IGrowable
{
    public static final String[] field_149882_a = new String[] {"yellow", "blue", "acacia", "desert"};
    private static final IIcon[] field_149881_b = new IIcon[field_149882_a.length];
    private static final String __OBFID = "CL_00000305";

    protected TutorialSapling01()
    {
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(Tutorial.tutorialTabBlocks);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

            if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_.nextInt(7) == 0)
            {
                this.func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
            }
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        p_149691_2_ &= 7;
        return field_149881_b[MathHelper.clamp_int(p_149691_2_, 0, 5)];
    }

    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_)
    {
        int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);

        if ((l & 8) == 0)
        {
            p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        }
        else
        {
            this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public void func_149878_d(World p_149878_1_, int p_149878_2_, int p_149878_3_, int p_149878_4_, Random p_149878_5_)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(p_149878_1_, p_149878_5_, p_149878_2_, p_149878_3_, p_149878_4_)) return;
        int l = p_149878_1_.getBlockMetadata(p_149878_2_, p_149878_3_, p_149878_4_) & 7;
        Object object = new WorldGenTreesYellow(true);
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        switch (l)
        {
            case 0:
            default:
                break;
            case 1:
                if (!flag)
                {
                    j1 = 0;
                    i1 = 0;
                    object = new WorldGenTaiga2(true);
                }

                break;
            case 2:
                // object = new WorldGenSavannaTree_Tut(true);
            	
            	// WorldGenMegaJungle: Vines wachsen durch Generator im Biome Jungle
            	// es gibt für die normalen Trees auch Vines, aber ich weiss momentan nicht, welche Option in Jungles genutzt wird
            	// object = new WorldGenMegaJungle_Tut(true, 20, 10, 3, 3); // inkl. WorldGenHugeTrees
            	
            	// object = new WorldGenTaiga1_Tut();
            	// object = new WorldGenTaiga2_Tut(true);
            	// object = new WorldGenMegaPineTree_Tut(true, false); // inkl. WorldGenHugeTrees
            	
            	object = new WorldGenCanopyTree_Tut(true);
                break;
            case 3:
                // object = new WorldGenFNDesert(3, 3);
            	// object = new WorldGenFNWillow(Blocks.leaves, 2, Blocks.log, 2, 8);
            	// object = new WorldGenFNBeech(Blocks.leaves, 3, Blocks.log, 3);
            	// object = new WorldGenFNWTEucalyptus(Blocks.leaves, 2, Blocks.log, 2);
            	object = new WorldGenFNSequoia(Blocks.leaves, 1, Blocks.log, 1);
                break;
        }

        Block block = Blocks.air;

        p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, block, 0, 4);


        if (!((WorldGenerator)object).generate(p_149878_1_, p_149878_5_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1))
        {

        	p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, this, l, 4);

        }
    }

    public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_, int p_149880_5_)
    {
        return p_149880_1_.getBlock(p_149880_2_, p_149880_3_, p_149880_4_) == this && (p_149880_1_.getBlockMetadata(p_149880_2_, p_149880_3_, p_149880_4_) & 7) == p_149880_5_;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
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
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for (int i = 0; i < field_149881_b.length; ++i)
        {
            field_149881_b[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + field_149882_a[i]);
        }
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return (double)p_149852_1_.rand.nextFloat() < 0.45D;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
}