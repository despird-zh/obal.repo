package com.dcube.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dcube.core.EntryKey;
import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAce;
import com.dcube.core.security.EntryAcl;

public class FolderNode implements RepoNode{

	AccessControlEntry entry = null;
	String repoName = null;
	EntryKey key = null;
	
	public FolderNode(String repoName, AccessControlEntry entry) {
		this.repoName = repoName;
		this.entry = entry;
		this.key = entry.getEntryKey();
	}

	@Override
	public String getId() {
		
		return key.getKey();
	}

	@Override
	public String getName() {

		return entry.getAttrValue(RepoConstants.FolderEnum.Name.attribute, String.class);
	}

	@Override
	public boolean isDirectory() {
		
		return entry.getAttrValue(RepoConstants.FolderEnum.IsDirectory.attribute, Boolean.class);
	}

	@Override
	public EntryAcl getEntryAcl() {
		
		return entry.getEntryAcl();
	}

	@Override
	public void grant(AceType type, String name, String... permission) {
		
		EntryAcl acl = entry.getEntryAcl();
		EntryAce ace = new EntryAce(type, name, permission);
		acl.addEntryAce(ace, true);
	}

	@Override
	public void revoke(AceType type, String name, String permission) {
		EntryAcl acl = entry.getEntryAcl();
		EntryAce ace = acl.getEntryAce(type, name);
		ace.revoke(permission);
	}

	@Override
	public void grant(AceType type, String name, AcePrivilege privilege) {
		
		EntryAcl acl = entry.getEntryAcl();
		EntryAce ace = acl.getEntryAce(type, name);
		ace.setPrivilege(privilege);
	}
	
	@Override
	public String getOwner() {
		
		return null;
	}

	@Override
	public void setOwner(String owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModifier(String modifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAttributes(String... attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K> K getAttribute(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FolderNode> getFolderNodes(){
		
		return null;
	}
	
	public List<FileNode> getFileNodes(){
		
		return null;
	}


}
