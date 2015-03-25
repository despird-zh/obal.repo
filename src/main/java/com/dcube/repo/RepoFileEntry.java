package com.dcube.repo;

import java.util.List;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.security.EntryAcl;

public class RepoFileEntry extends AccessControlEntry{

	private String nodeName = null;
		
	public RepoFileEntry(String nodeName){
		
		super("",null);
		this.nodeName = nodeName;
	}
	
	public RepoFileEntry(String entityName, String key){
		
		super(entityName,key);
	}
	
	public String getNodeName(){
		
		return this.nodeName;
	}
	
	public List<RepoFileEntry> childNodes(){
		return null;
		
	}
	
	public RepoFileEntry parentNode(){
		return null;	
		
	}
	
	public EntryAcl getEntryAcl(){
		
		return null;
	}
}
