package com.chrissionair.tutorial.event;

import com.chrissionair.tutorial.Tutorial;
import com.chrissionair.tutorial.TutorialSapling01;

import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Tutorial_Bonemeal_Event {
	
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent e) {
		
		if(e.block == Tutorial.tutorialSapling) {
			if(!e.world.isRemote) {
				
		        int l = e.world.getBlockMetadata(e.x, e.y, e.z);

		        if ((l & 8) == 0)
		        {
		            e.world.setBlockMetadataWithNotify(e.x, e.y, e.z, l | 8, 4);
		        }
		        else
		        {
					((TutorialSapling01)Tutorial.tutorialSapling).func_149878_d(e.world, e.x, e.y, e.z, e.world.rand);
					e.setResult(Result.ALLOW);
		        }
			}
		}
		
		
	}

}
