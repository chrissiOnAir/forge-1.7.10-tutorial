package com.chrissionair.tutorial.world;

import java.util.Random;

import com.chrissionair.tutorial.Tutorial;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenTutorial extends BiomeGenBase {

	public BiomeGenTutorial(int par1) {
		super(par1);
		
		// momentan wächst auf Tutorial.tutorialGrass kein Baum
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.clay;
		
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 20;

	}

    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return (WorldGenAbstractTree) (random.nextInt(3) == 0 ? new WorldGenSavannaTree(false) : new WorldGenTreesYellow(false));
    }
    
}
