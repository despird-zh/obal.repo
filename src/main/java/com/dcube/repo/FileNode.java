package com.dcube.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dcube.core.security.AclConstants.AcePrivilege;
import com.dcube.core.security.AclConstants.AceType;
import com.dcube.core.security.EntryAcl;

public class FileNode implements RepoNode{

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

	public String getRevision(){
		
		return null;
	}
	
	public void setRevision(String revision){
		
	}
	
	public boolean isGroup(){
		
		return false;
	}
	
	public List<FileNode> getFileNodes(){
		
		throw new UnsupportedOperationException("");
	}
	
	public List<FileTag> getFileTags(){
		
		return null;
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

	
	
}
