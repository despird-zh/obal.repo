package com.dcube.repo;

import java.util.Date;
import java.util.Map;

import com.dcube.core.security.AclPrivilege;
import com.dcube.core.security.EntryAcl;

public interface IRepoNode {

	public String getId();
	
	public String getName();
	
	public boolean isDirectory();
	
	public EntryAcl getEntryAcl();
	
	public void grant(String ... permission);
	
	public void revoke(String permission);
	
	public void grant(AclPrivilege privilege);
	
	public void revoke(AclPrivilege privilege);
	
	public String getOwner();
	
	public void setOwner(String owner);
	
	public String getModifier();
	
	public void setModifier(String modifier);
	
	public Date getCreateDate();
	
	public Date getLastModify();
	
	public Map<String, Object> getAttributes(String ...attributes);
	
	public <K> K getAttribute(String attribute);
}
