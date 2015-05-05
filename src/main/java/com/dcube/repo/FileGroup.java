package com.dcube.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FileGroup is a collection of multiple files.
 * Sometimes need to access files that's located at different position.
 * 
 **/
public class FileGroup extends FileNode{
	
	public FileGroup(String fileEntity) {
		super(fileEntity);
		// TODO Auto-generated constructor stub
	}

	Set<FileNode> files = new HashSet<FileNode>();
	
	@Override
	public boolean isGroup(){
		
		return true;
	}
	
	@Override
	public List<FileNode> getFileNodes(){
		
		return null;
	}
}
