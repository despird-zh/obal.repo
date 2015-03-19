package com.dcube.fs;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;

import com.sun.nio.zipfs.ZipFileAttributeView;
import com.sun.nio.zipfs.ZipPath;

public class CubeFileAttributeView implements BasicFileAttributeView{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "basic";
	}

	@Override
	public BasicFileAttributes readAttributes() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTimes(FileTime arg0, FileTime arg1, FileTime arg2)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

}
