package com.obal.repo;

import java.util.List;

import com.obal.core.accessor.AccessControlEntry;
import com.obal.core.security.EntryAcl;

public class NodeInfo extends AccessControlEntry{
	
	private static final long serialVersionUID = 1L;
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
