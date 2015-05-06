package com.dcube.repo;

import java.util.Date;
import java.util.List;
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
 * FileNode delegate the presentation of file entry in repository
 * 
 * @author desprid
 * @version 0.1 2014-3-2
 **/
public class FileNode extends EntryParser implements RepoNode{

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
		
		return null;
	}

	@Override
	public <K> K getAttribute(String attribute,Class<K> type) {
		EntityEntry temp = (EntityEntry)rawEntry;
		return temp.getAttrValue(attribute, type);
	}
	
	public Set<String> getFileTags(){
		
		Map<String,String> tagmap = getAttrValue(FileEnum.Tags.attribute, Map.class);
		return tagmap.keySet();
	}
	
	public String getPrimaryContent(){
		
		return getAttrValue(FileEnum.PrimaryContent.attribute, String.class);
	}
	
	public Map<String, String> getRendContents(){
		
		return getAttrValue(FileEnum.RendContents.attribute, Map.class);
	}

	public Set<String> getKeywords(){
		
		return getAttrValue(FileEnum.Keywards.attribute, Set.class);
	}
	
	public String getParentNode(){
		return getAttrValue(FileEnum.Parent.attribute, String.class);
	}
	
	public String getEntity(){
		return getAttrValue(FileEnum.Entity.attribute, String.class);
	}
	
	public String getPrimaryFormat(){
		return getAttrValue(FileEnum.PrimaryFormat.attribute, String.class);
	}
	
	public boolean isLocked(){
		return getAttrValue(FileEnum.Lock.attribute, Boolean.class);
	}
	
	public String getLockby(){
		return getAttrValue(FileEnum.Lockby.attribute, String.class);
	}
	
	@Override
	public void grant(AceType type, String name, String... permission) {
		
		EntryAcl acl = super.getEntryAcl();		
		EntryAce ace = acl.getEntryAce(type, name);
		ace.grant(permission);
	}

	@Override
	public void revoke(AceType type, String name, String permission) {
		
		EntryAcl acl = super.getEntryAcl();	
		EntryAce ace = acl.getEntryAce(type, name);
		ace.revoke(permission);
	}

	@Override
	public void grant(AceType type, String name, AcePrivilege privilege) {
		
		EntryAcl acl = super.getEntryAcl();	
		EntryAce ace = acl.getEntryAce(type, name);
		ace.setPrivilege(privilege);
	}	
	
	/** get the attribute value */
	private <K> K getAttrValue(String attribute, Class<K> type){
		EntityEntry temp = (EntityEntry)rawEntry;
		return temp.getAttrValue(attribute, type);
	}
	
	/** set the attribute value */
	private void setAttrValue(String attribute, Object value){
		EntityEntry temp = (EntityEntry)rawEntry;		
		temp.changeAttrValue(attribute, value);
	}
}
