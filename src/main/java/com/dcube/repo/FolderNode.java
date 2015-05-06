package com.dcube.repo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.accessor.EntryParser;
import com.dcube.core.security.EntryAce;
import com.dcube.core.security.EntryAcl;
import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.repo.RepoConstants.FileEnum;
import com.dcube.repo.RepoConstants.FolderEnum;

public class FolderNode extends EntryParser  implements RepoNode{

	/**
	 * Constructor with AccessControlEntry
	 **/
	public FolderNode(AccessControlEntry rawEntry){

		super(rawEntry);
	}
	
	/**
	 * Constructor with entity name 
	 **/
	public FolderNode(String fileEntity){

		super(new AccessControlEntry(fileEntity,null));
	}
	/**
	 * the constructor 
	 * @param group the group name
	 * @param key the key  
	 **/
	public FolderNode(String fileEntity,String name, String key) {
		super(new AccessControlEntry(fileEntity, key));
		setAttrValue(FileEnum.NodeName.attribute, name);
	}

	/**
	 * the constructor 
	 * @param group the group name
	 **/
	public FolderNode(String fileEntity,String name){
		
		super(new AccessControlEntry(fileEntity, null));
		setAttrValue(FileEnum.NodeName.attribute, name);
	}
	
	@Override
	public String getNodeId() {
		
		return getEntryKey().getKey();
	}

	@Override
	public String getNodeName() {
		
		return getAttrValue(FolderEnum.NodeName.attribute, String.class);
	}

	@Override
	public boolean isDirectory() {
		
		return getAttrValue(FolderEnum.IsDirectory.attribute, Boolean.class);
	}

	@Override
	public void grant(AceType type, String name, String... permission) {
		
		EntryAcl acl = super.getEntryAcl();		
		EntryAce ace = acl.getEntryAce(type, name);
		if(ace != null){
			ace.grant(permission);
		}else{
			ace = new EntryAce(type, name, permission);
			acl.addEntryAce(ace, true);
		}
	}

	@Override
	public void revoke(AceType type, String name, String permission) {
		
		EntryAcl acl = super.getEntryAcl();	
		EntryAce ace = acl.getEntryAce(type, name);
		if(ace != null)
			ace.revoke(permission);
	}

	@Override
	public void grant(AceType type, String name, AcePrivilege privilege) {
		
		EntryAcl acl = super.getEntryAcl();	
		EntryAce ace = acl.getEntryAce(type, name);
		
		if(ace != null){
			ace.setPrivilege(privilege);
		}else{
			ace = new EntryAce(type, name, privilege);
			acl.addEntryAce(ace, true);
		}
	}	

	@Override
	public String getOwner() {
		
		return getAttrValue(FolderEnum.Owner.attribute, String.class);
	}

	@Override
	public void setOwner(String owner) {
		EntryAcl acl = getAccessControlEntry().getEntryAcl();
		EntryAce ace = acl.getEntryAce(AceType.Owner, null);		
		ace.setName(owner);
		
		setAttrValue(FileEnum.Owner.attribute, owner);
	}

	@Override
	public String getModifier() {
		
		return getAccessControlEntry().getModifier();
	}

	@Override
	public void setModifier(String modifier) {
		
		getAccessControlEntry().setModifier(modifier);
	}

	@Override
	public Date getCreateDate() {
	
		return getAccessControlEntry().getNewCreate();
	}

	@Override
	public Date getLastModify() {
		
		return getAccessControlEntry().getLastModify();
	}

	@Override
	public Map<String, Object> getAttributes(String... attributes) {
		Map<String, Object> result = new HashMap<String, Object>();
		EntityEntry temp = (EntityEntry)rawEntry;
		for(String attribute:attributes){
			Object val = temp.getAttrValue(attribute);
			result.put(attribute, val);
		}
		return result;
	}

	@Override
	public <K> K getAttribute(String attribute, Class<K> clazz) {
		
		return getAttrValue(attribute, clazz);
	}


}
