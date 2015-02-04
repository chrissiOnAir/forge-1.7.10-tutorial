package com.chrissionair.tutorial;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TutorialMultiBlock extends Block {

	public TutorialMultiBlock(Material material) {
		
		super(material);
		setCreativeTab(Tutorial.tutorialTabBlocks);
		setBlockName("tutorialMultiBlock");
		
	}
	
	@Override
	public int damageDropped(int metadata) {

		return metadata;
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	// registerBlockIcons	func_149651_a
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
	
		icons = new IIcon[3];
        
		for(int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(Tutorial.MODID + ":" + Tutorial.MODID + "_multi_" + TutorialItemMultiBlock.subNames[i]);
		}
	}
	
	// getIcon func_149691_a
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		
		return icons[par2];
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		
		for (int i = 0; i < 3; i++) {
			subItems.add(new ItemStack(item, 1, i));
		}
	}
	
}
