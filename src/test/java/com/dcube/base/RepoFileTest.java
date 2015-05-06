package com.dcube.base;

import org.apache.hadoop.hbase.filter.Filter;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.dcube.core.AccessorFactory;
import com.dcube.core.CoreConfigs;
import com.dcube.core.EntryFilter;
import com.dcube.core.EntryKey;
import com.dcube.core.IEntryConverter;
import com.dcube.launcher.CoreFacade;
import com.dcube.repo.FileNode;
import com.dcube.repo.RepoConstants;
import com.dcube.repo.hbase.FileNodeEAccessor;
import com.dcube.util.AccessorUtils;
import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.EntryCollection;
import com.dcube.core.hbase.HAclBrowseFilter;
import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAce;
import com.dcube.core.security.EntryAcl;
import com.dcube.core.security.Principal;
import com.dcube.exception.BaseException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class RepoFileTest extends BaseTester{
	
	static{
		debug("---==: initial log4j");
		initLog4j();
		setSwitch(0,true);// prepare
		setSwitch(1,false);// prepare
		setSwitch(2,true);// prepare
		setSwitch(999,true); // drop schema, clear
	}
	
    public void test000Initial() throws Exception{     
    	if(!switchOn(0))
    		return;
    	
    	debug("---==: Test 000 initial ");

		CoreFacade.initial();
		CoreFacade.start();

    }
    
    public void test001SetupTables() {     
    	if(!switchOn(1))
    		return;
    	
    	Principal princ = CoreConfigs.getAdminPrincipal();
    	FileNodeEAccessor fnea = null;
    	FileNode filenode = new FileNode(RepoConstants.ENTITY_BASE_FILE, "demofile1");
    	try{
    		fnea = AccessorFactory.buildEntityAccessor(princ, RepoConstants.ENTITY_BASE_FILE);
    		EntryKey entryKey = fnea.newKey();
    		filenode.setOwner("demoowner");   	
    		filenode.setEntryKey(entryKey);
    		IEntryConverter<AccessControlEntry, FileNode> converter = fnea.getEntryConverter(FileNode.class);
    		EntryAcl acl = filenode.getEntryAcl();
    		EntryAce ace = new EntryAce(AceType.User, "nusr1", AcePrivilege.READ, "oper1","oper2","oper3");
    		acl.addEntryAce(ace, true);
    		ace = new EntryAce(AceType.User, "nusr2", AcePrivilege.WRITE, "oper1","oper2","oper3");
    		acl.addEntryAce(ace, true);
    		ace = new EntryAce(AceType.Group, "ngrp1", AcePrivilege.NONE, "oper1","oper2");
    		acl.addEntryAce(ace, true);
    		ace = new EntryAce(AceType.Group, "ngrp1", AcePrivilege.READ, "oper3");
    		acl.addEntryAce(ace, true);
    		ace = new EntryAce(AceType.Group, "ngrp2", AcePrivilege.NONE, "oper3");
    		acl.addEntryAce(ace, true);
    		AccessControlEntry acentry = converter.toSource(filenode);
    		
    		fnea.doPutEntry(acentry, false);
    		
    	} catch (BaseException e) {
			
			e.printStackTrace();
		}finally{
    		AccessorUtils.closeAccessor(fnea);
    	}
    }
    
    public void test002ScanTest(){
    	
    	if(!switchOn(2))
    		return;
    	
    	Principal princ = CoreConfigs.getAdminPrincipal();
    	FileNodeEAccessor fnea = null;
    	FileNode filenode = new FileNode(RepoConstants.ENTITY_BASE_FILE, "demofile");
    	try{
    		
    		fnea = AccessorFactory.buildEntityAccessor(princ, RepoConstants.ENTITY_BASE_FILE);
    		EntryFilter<HAclBrowseFilter> filter = new EntryFilter<HAclBrowseFilter>();
    		HAclBrowseFilter hf = new HAclBrowseFilter(null, new String[]{"ngrp2"});
    		filter.setFilter(hf);
    		IEntryConverter<AccessControlEntry, FileNode> converter = fnea.getEntryConverter(FileNode.class);
    		EntryCollection<AccessControlEntry> coll = fnea.doScanEntry(filter);
    		
    		for(AccessControlEntry acentry:coll){
    			
    			FileNode fn = converter.toTarget(acentry);
    			System.out.println("file name:" + fn.getNodeName());
    		}
    		
    	} catch (BaseException e) {
			
			e.printStackTrace();
		}finally{
    		AccessorUtils.closeAccessor(fnea);
    	}
    }
    
    public void test999End() throws Exception{       
    	if(!switchOn(999))
    		return;
    	
    	debug("---==: Test 999 schema drop");
    	CoreFacade.stop();
    }
}
