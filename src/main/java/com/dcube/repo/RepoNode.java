package com.dcube.repo;

import java.util.Date;
import java.util.Map;

import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAcl;

public interface RepoNode {

	public String getId();
	
	public String getName();
	
	public boolean isDirectory();
	
	public EntryAcl getEntryAcl();
	
	public void grant(AceType type, String name, String ... permission);
	
	public void revoke(AceType type, String name, String permission);
	
	public void grant(AceType type, String name, AcePrivilege privilege);

	public String getOwner();
	
	public void setOwner(String owner);
	
	public String getModifier();
	
	public void setModifier(String modifier);
	
	public Date getCreateDate();
	
	public Date getLastModify();
	
	public Map<String, Object> getAttributes(String ...attributes);
	
	public <K> K getAttribute(String attribute);
}
