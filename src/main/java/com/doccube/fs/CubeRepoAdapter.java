package com.doccube.fs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CubeRepoAdapter {

	CubeFileSystem ubfs ;
	
	public CubeRepoAdapter(CubeFileSystem ubfs){
		this.ubfs = ubfs;
	}
	
	/**
	 * Get the child folders and files
	 **/
	public List<Path> getChilds(byte[] path){
		
		String sp = new String(path);
		System.out.println(" current root : "+ sp);
		List<Path> rtv = new ArrayList<Path>();
		if("/".equals(sp)||"".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d1".getBytes()));
			rtv.add(new CubePath(ubfs, "/d2".getBytes()));
			rtv.add(new CubePath(ubfs, "/d3".getBytes()));
			rtv.add(new CubePath(ubfs, "/f1".getBytes()));
			rtv.add(new CubePath(ubfs, "/f2".getBytes()));
		}
		if("d1".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d1/d11".getBytes()));
			rtv.add(new CubePath(ubfs, "/d1/f11".getBytes()));
		}
		if("d1/d11".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d1/d11/d111".getBytes()));
		}		
		if("d1/d11/d111".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d1/d11/d111/f1111".getBytes()));
			rtv.add(new CubePath(ubfs, "/d1/d11/d111/f1112".getBytes()));
			rtv.add(new CubePath(ubfs, "/d1/d11/d111/f1113".getBytes()));
		}
		if("d2".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d2/d21".getBytes()));
			rtv.add(new CubePath(ubfs, "/d2/f21".getBytes()));
		}		
		if("d3".equals(sp)){
			rtv.add(new CubePath(ubfs, "/d3/d31".getBytes()));
			rtv.add(new CubePath(ubfs, "/d3/f31".getBytes()));
		}
		return rtv;
	}
	
	public CubeBasicFileAttributes getAttributes(byte[] path){
		
		CubePath upath = new CubePath(ubfs,path);
		String name = upath.getFileName()== null?"d" :upath.getFileName().toString();
		CubeBasicFileAttributes fa = new CubeBasicFileAttributes(upath);
		return fa;
	}
}
