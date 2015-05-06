package com.dcube.repo;

import java.util.Date;
import java.util.Map;

import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAcl;

/**
 * RepoNode defines the basic normal operation with repository entry, i.e. file or folder.
 * 
 * @author despird
 * @version 0.1 2014-3-1
 **/
public interface RepoNode {

	/**
	 * Get the repository node id 
	 **/
	public String getNodeId();
	
	/**
	 * Get the repository node name 
	 **/
	public String getNodeName();
	
	/**
	 * Is the directory 
	 **/
	public boolean isDirectory();
	
	/**
	 * Get the entry acl object 
	 **/
	public EntryAcl getEntryAcl();
	
	/**
	 * Grant the permissions to subject(user or group) 
	 **/
	public void grant(AceType type, String name, String ... permission);
	
	/**
	 * Revoke the permissions from subject(user or group) 
	 **/
	public void revoke(AceType type, String name, String permission);
	
	/**
	 * Grant the privilege to subject(user or group), i.e reset the privilege
	 **/
	public void grant(AceType type, String name, AcePrivilege privilege);

	/**
	 * Get the owner of entry 
	 **/
	public String getOwner();
	
	/**
	 * Set the owner of entry 
	 **/
	public void setOwner(String owner);
	
	/**
	 * Get the node modifier 
	 **/
	public String getModifier();
	
	/**
	 * Set the node modifier 
	 **/
	public void setModifier(String modifier);
	
	/**
	 * Get the create date 
	 **/
	public Date getCreateDate();
	
	/**
	 * Get last modify date 
	 **/
	public Date getLastModify();
	
	/**
	 * Get the attribute values 
	 **/
	public Map<String, Object> getAttributes(String ...attributes);
	
	/**
	 * Get the attribute value
	 **/
	public <K> K getAttribute(String attribute,Class<K> clazz);
}
