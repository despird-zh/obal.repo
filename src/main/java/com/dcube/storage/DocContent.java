package com.dcube.storage;

import com.dcube.exception.StorageException;

public abstract class DocContent {

	private StorageInfo storage;	
	private String path  = null;
	private boolean ready = false;
	
	public DocContent(StorageInfo storage, String path){
		
		this.storage = storage;
		this.path = path;
	}
	
	public abstract void read(byte[] data, int offset, int length) throws StorageException;
	
	public abstract void write(byte[] data, int offset, int length) throws StorageException;

	public abstract void cleanup()throws StorageException;
	
	public abstract long contentSize();
	
	public abstract long chunkAmount( long chunkSize);
	
}
