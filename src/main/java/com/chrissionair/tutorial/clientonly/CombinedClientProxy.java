package com.chrissionair.tutorial.clientonly;

import com.chrissionair.tutorial.common.CommonProxy;

public class CombinedClientProxy extends CommonProxy {
	@Override
	public void doSomething() {
		super.doSomething();   // do something on both
		// do something on normal Minecraft only
	} 
}