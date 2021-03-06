package com.dcube.base;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.dcube.launcher.CoreFacade;
import com.dcube.repo.RepoSetup;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class RepoMainTest extends BaseTester{
	
	static{
		debug("---==: initial log4j");
		initLog4j();
		setSwitch(0,true);// prepare
		setSwitch(1,true);// prepare

		setSwitch(999,true); // drop schema, clear
	}
	
    public void test000Initial() throws Exception{     
    	if(!switchOn(0))
    		return;
    	
    	debug("---==: Test 000 initial ");

		CoreFacade.initial();
		CoreFacade.startup();

    }
    
    public void test001SetupTables() {     
    	if(!switchOn(1))
    		return;
    	
    	debug("---==: Test 001 schema create ");
    	RepoSetup setup = new RepoSetup();
    	setup.setup();
    }
     
    public void test999End() throws Exception{       
    	if(!switchOn(999))
    		return;
    	
    	debug("---==: Test 999 schema drop");
    	CoreFacade.shutdown();
    }
}
