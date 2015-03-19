package com.dcube.repo;

import java.util.List;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.security.EntryAcl;

public class NodeInfo extends AccessControlEntry{

	private String nodeName = null;
		
	public NodeInfo(String nodeName){
		
		super("",null);
		this.nodeName = nodeName;
	}
	
	public NodeInfo(String entityName, String key){
		
		super(entityName,key);
	}
	
	public String getNodeName(){
		
		return this.nodeName;
	}
	
	public List<NodeInfo> childNodes(){
		return null;
		
	}
	
	public NodeInfo parentNode(){
		return null;	
		
	}
	
	public EntryAcl getEntryAcl(){
		
		return null;
	}
}
