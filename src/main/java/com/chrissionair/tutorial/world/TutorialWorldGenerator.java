package com.chrissionair.tutorial.world;

import java.util.Random;

import com.chrissionair.tutorial.Tutorial;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class TutorialWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId) {
		
		       case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateSurface(World world, Random random, int x, int z) {
		
		// WorldGenMinable(block, maxVeinSize)
		// this.addSpawn(world, random, x, z, 16, 16, 10, 15, 100, new WorldGenMinable(Tutorial.tutorialOre, 3 + random.nextInt(6)));
		
		// this.addSpawn(world, random, x, z, 16, 16, 20, 64, 90, new WorldGenTreesYellow(false));
		// this.addSpawn(world, random, x, z, 16, 16, 20, 64, 90, WorldGenTreesYellow.class);
		this.addWorldGenSpawn(world, random, x, z, 16, 16, 10, 15, 100);	
	}

	/**
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addWorldGenSpawn(World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int chancesToSpawn, int minY, int maxY) {
		
		assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X 1 - 16";
		assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z 1 - 16";
		
		for(int i = 0; i < chancesToSpawn; i++) {
			   
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(Tutorial.tutorialOre, 3 + random.nextInt(6))).generate(world, random, posX, posY, posZ);
		}
	}

}
