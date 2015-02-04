package com.chrissionair.tutorial.event;

import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import com.chrissionair.tutorial.world.WorldGenTreesYellow;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Tutorial_Decorate_Event {
	
	@SubscribeEvent
	public void onDecorateTrees_yellow(Decorate e) {
		
		if(e.type == TREE && BiomeDictionary.isBiomeOfType(e.world.getBiomeGenForCoords(e.chunkX, e.chunkZ), FOREST)) {
			
	        if(e.rand.nextInt(200) == 0)
	        {
	        	for(int j = 0; j < e.rand.nextInt(5); j++) {
	        		
		            int k = e.chunkX + e.rand.nextInt(16) + 8;
		            int l = e.chunkZ + e.rand.nextInt(16) + 8;
		            int i1 = e.world.getHeightValue(k, l);
		            WorldGenAbstractTree worldgenabstracttree = new WorldGenTreesYellow(false);
		            
		            worldgenabstracttree.generate(e.world, e.rand, k, i1, l);
	        	}
	        }
		}
		
		
		/*
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
        for (j = 0; doGen && j < i; ++j)
        {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.currentWorld.getHeightValue(k, l);
            WorldGenAbstractTree worldgenabstracttree = p_150513_1_.func_150567_a(this.randomGenerator);
            worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

            if (worldgenabstracttree.generate(this.currentWorld, this.randomGenerator, k, i1, l))
            {
                worldgenabstracttree.func_150524_b(this.currentWorld, this.randomGenerator, k, i1, l);
            }
        }
		 */
	}
}
