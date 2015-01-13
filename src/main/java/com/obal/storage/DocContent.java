package com.obal.storage;

import com.obal.exception.StorageException;

public abstract class DocContent {

	private StorageInfo storage;	
	private String path  = null;
	
	public DocContent(StorageInfo storage, String path){
		
		this.storage = storage;
		this.path = path;
	}
	
	public abstract void read(byte[] data, int offset, int length) throws StorageException;
	
	public abstract void write(byte[] data, int offset, int length) throws StorageException;

	public abstract void cleanup()throws StorageException;
	
	public abstract int dataLength();
	
	public abstract int chunkAmount( int chunkSize);
	
}
