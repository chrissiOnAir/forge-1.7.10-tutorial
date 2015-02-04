package com.chrissionair.tutorial.serveronly;

import com.chrissionair.tutorial.common.CommonProxy;

public class DedicatedServerProxy extends CommonProxy {
	@Override
	public void doSomething() {
		super.doSomething();   // do something on both
		// do something on dedicated server only
	} 
}
