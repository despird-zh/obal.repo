package com.dcube.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.accessor.EntryParser;
import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAce;
import com.dcube.core.security.EntryAcl;
import com.dcube.meta.EntityConstants.GroupEnum;
import com.dcube.repo.RepoConstants.FileEnum;

public class FileNode extends EntryParser implements RepoNode{

	public FileNode(){
		super();
		rawEntry = new AccessControlEntry(RepoConstants.ENTITY_FILE,null);
	}
	/**
	 * the constructor 
	 * @param group the group name
	 * @param key the key  
	 **/
	public FileNode(String name, String key) {
		super();
		rawEntry = new AccessControlEntry(RepoConstants.ENTITY_FILE,key);
		setAttrValue(GroupEnum.Name.attribute, name);
	}

	/**
	 * the constructor 
	 * @param group the group name
	 **/
	public FileNode(String name){
		
		super();
		rawEntry = new AccessControlEntry(RepoConstants.ENTITY_FILE,null);
		setAttrValue(GroupEnum.Name.attribute, name);
	}
	
	@Override
	public String getNodeId() {
		// TODO Auto-generated method stub
		return getEntryKey().getKey();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getAttrValue(FileEnum.Name.attribute, String.class);
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return getAttrValue(FileEnum.IsDirectory.attribute, Boolean.class);
	}

	@Override
	public EntryAcl getEntryAcl() {
		// TODO Auto-generated method stub
		return ((AccessControlEntry)rawEntry).getEntryAcl();
	}

	@Override
	public String getOwner() {
		
		return getAttrValue(FileEnum.Owner.attribute, String.class);
	}

	@Override
	public void setOwner(String owner) {
		EntryAcl acl = ((AccessControlEntry)rawEntry).getEntryAcl();
		EntryAce ace = acl.getEntryAce(AceType.Owner, null);		
		ace.setName(owner);
		
		setAttrValue(FileEnum.Owner.attribute, owner);
	}

	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return ((AccessControlEntry)rawEntry).getModifier();
	}

	@Override
	public void setModifier(String modifier) {
		// TODO Auto-generated method stub
		((AccessControlEntry)rawEntry).setModifier(modifier);
	}

	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return ((AccessControlEntry)rawEntry).getNewCreate();
	}

	@Override
	public Date getLastModify() {
		// TODO Auto-generated method stub
		return ((AccessControlEntry)rawEntry).getLastModify();
	}

	@Override
	public Map<String, Object> getAttributes(String... attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K> K getAttribute(String attribute,Class<K> type) {
		EntityEntry temp = (EntityEntry)rawEntry;
		return temp.getAttrValue(attribute, type);
	}
	
	public boolean isGroup(){
		
		return getAttrValue(FileEnum.IsGroup.attribute, Boolean.class);
	}
	
	public List<FileNode> getFileNodes(){
		
		throw new UnsupportedOperationException("");
	}
	
	public List<String> getFileTags(){
		
		return getAttrValue(FileEnum.Tags.attribute, List.class);
	}
	
	public FileContent getPrimaryContent(){
		
		return null;
	}
	
	public List<FileContent> getRendContents(){
		
		return null;
	}

	@Override
	public void grant(AceType type, String name, String... permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revoke(AceType type, String name, String permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grant(AceType type, String name, AcePrivilege privilege) {
		// TODO Auto-generated method stub
		
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
