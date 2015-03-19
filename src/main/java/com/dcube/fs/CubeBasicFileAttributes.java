package com.dcube.fs;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class CubeBasicFileAttributes implements BasicFileAttributes{
	
	CubePath path ;
	
	public CubeBasicFileAttributes(CubePath path){
		this.path  = path;
	}
	
	@Override
	public FileTime creationTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object fileKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirectory() {
		String name = path.getFileName()==null? "d": path.getFileName().toString();
		System.out.println("name :" + name );
		return name.startsWith("d");
	}

	@Override
	public boolean isOther() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegularFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSymbolicLink() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileTime lastAccessTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileTime lastModifiedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
