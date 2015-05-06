package com.dcube.repo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.accessor.EntryParser;
import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAce;
import com.dcube.core.security.EntryAcl;
import com.dcube.repo.RepoConstants.FileEnum;

/**
 * FileNode delegate the file entry in repository
 * 
 * @author desprid
 * @version 0.1 2014-3-2
 **/
public class FileNode extends EntryParser implements RepoNode{

	/**
	 * Constructor with AccessControlEntry
	 **/
	public FileNode(AccessControlEntry rawEntry){

		super(rawEntry);
	}
	
	/**
	 * Constructor with entity name 
	 **/
	public FileNode(String fileEntity){

		super(new AccessControlEntry(fileEntity,null));
	}
	
	/**
	 * the constructor 
	 * @param group the group name
	 * @param key the key  
	 **/
	public FileNode(String fileEntity,String name, String key) {
		super(new AccessControlEntry(fileEntity, key));
		setAttrValue(FileEnum.NodeName.attribute, name);
	}

	/**
	 * the constructor 
	 * @param group the group name
	 **/
	public FileNode(String fileEntity,String name){
		
		super(new AccessControlEntry(fileEntity, null));
		setAttrValue(FileEnum.NodeName.attribute, name);
	}
	
	@Override
	public String getNodeId() {
		
		return getEntryKey().getKey();
	}

	@Override
	public String getNodeName() {
		
		return getAttrValue(FileEnum.NodeName.attribute, String.class);
	}

	@Override
	public boolean isDirectory() {
		
		return getAttrValue(FileEnum.IsDirectory.attribute, Boolean.class);
	}

	@Override
	public String getOwner() {
		
		return getAttrValue(FileEnum.Owner.attribute, String.class);
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
	public <K> K getAttribute(String attribute,Class<K> type) {
		
		return getAttrValue(attribute, type);
	}
	
	/**
	 * Get the file tags attached on this file entry 
	 **/
	public Set<FileTag> getFileTags(){
		
		Set<FileTag> tags = new HashSet<FileTag>();
		@SuppressWarnings("unchecked")
		Map<String,String> tagmap = getAttrValue(FileEnum.Tags.attribute, Map.class);
		for(Map.Entry<String,String> item: tagmap.entrySet()){
			tags.add(new FileTag(item.getKey(),item.getValue()));
		}
		return tags;
	}
	
	/**
	 * Get the key of primary content 
	 **/
	public String getPrimaryContent(){
		
		return getAttrValue(FileEnum.PrimaryContent.attribute, String.class);
	}
	
	/**
	 * Get the rendition of file
	 * @return  Map<String, String> the map key is the name of rendition, the value is the key the rendition content
	 **/
	@SuppressWarnings("unchecked")
	public Map<String, String> getRendContents(){
		
		return getAttrValue(FileEnum.RendContents.attribute, Map.class);
	}

	/**
	 * Get the keywords of file 
	 **/
	@SuppressWarnings("unchecked")
	public Set<String> getKeywords(){
		
		return getAttrValue(FileEnum.Keywards.attribute, Set.class);
	}
	
	/**
	 * Get the parent node key, i.e. the key of parent folder 
	 **/
	public String getParentNode(){
		return getAttrValue(FileEnum.Parent.attribute, String.class);
	}
	
	/**
	 * Get the entity name of file node, this entity includes some extra attributes
	 * i.e. the user defined attributes. 
	 **/
	public String getEntity(){
		return getAttrValue(FileEnum.Entity.attribute, String.class);
	}
	
	/**
	 * Get the primary content format 
	 **/
	public String getPrimaryFormat(){
		return getAttrValue(FileEnum.PrimaryFormat.attribute, String.class);
	}
	
	/**
	 * Check if the file node is locked 
	 **/
	public boolean isLocked(){
		return getAttrValue(FileEnum.Lock.attribute, Boolean.class);
	}
	
	/**
	 * Get the lock by information
	 * @return String the account that lock the file node. 
	 **/
	public String getLockby(){
		return getAttrValue(FileEnum.Lockby.attribute, String.class);
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

	/**
	 * Get the description of file node 
	 **/
	public String getDescription(){
		return  getAttrValue(FileEnum.Description.attribute, String.class);
	}
}
