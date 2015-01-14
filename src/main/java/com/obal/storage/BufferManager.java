package com.obal.storage;

class BufferManager {

	public static byte FLAG_USED = 1;
	public static byte FLAG_FREE = 0;
	
	private byte[] bufferFlags = new byte[1024];
	

}
