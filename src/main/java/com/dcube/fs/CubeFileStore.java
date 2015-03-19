package com.dcube.fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;

public class CubeFileStore extends FileStore {

	private final CubeFileSystem ubfs;

    CubeFileStore(CubePath upath) {
        this.ubfs = (CubeFileSystem)upath.getFileSystem();
    }
	
	@Override
	public Object getAttribute(String attribute) throws IOException {
		 if (attribute.equals("totalSpace"))
             return getTotalSpace();
       if (attribute.equals("usableSpace"))
             return getUsableSpace();
       if (attribute.equals("unallocatedSpace"))
             return getUnallocatedSpace();
       throw new UnsupportedOperationException("does not support the given attribute");
	}

	@Override
	public <V extends FileStoreAttributeView> V getFileStoreAttributeView(
			Class<V> type) {
	       if (type == null)
	            throw new NullPointerException();
	        return (V)null;
	}

	@Override
	public long getTotalSpace() throws IOException {
		
		return 1024*1024*8;
	}

	@Override
	public long getUnallocatedSpace() throws IOException {
		
		return Double.valueOf(1024*1024*8*0.2).longValue();
	}

	@Override
	public long getUsableSpace() throws IOException {
		
		return Double.valueOf(1024*1024*8*0.8).longValue();
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String name() {
		return "ubrepo/";
	}

	@Override
	public boolean supportsFileAttributeView(
			Class<? extends FileAttributeView> type) {
		return (type == CubeFileAttributeView.class ||
                type == CubeAclFileAttributeView.class);
	}

	@Override
	public boolean supportsFileAttributeView(String name) {
		return name.equals("basic") || name.equals("acl");
	}

	@Override
	public String type() {
		return "ubfs";
	}

}
