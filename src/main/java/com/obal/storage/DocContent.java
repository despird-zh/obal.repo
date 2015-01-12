package com.obal.storage;

import com.obal.exception.StorageException;

public abstract class DocContent {
		
	public abstract void read(byte[] data, int offset, int length) throws StorageException;
	
	public abstract void write(byte[] data, int offset, int length) throws StorageException;

	public abstract void cleanup()throws StorageException;
}
