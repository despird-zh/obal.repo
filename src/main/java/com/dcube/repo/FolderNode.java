package com.dcube.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dcube.core.security.AclPrivilege;
import com.dcube.core.security.EntryAcl;

public class FolderNode implements IRepoNode{

	public FolderNode(String nodeName) {
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntryAcl getEntryAcl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void grant(String... permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revoke(String permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grant(AclPrivilege privilege) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revoke(AclPrivilege privilege) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
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
