package com.dcube.storage;

import java.nio.ByteBuffer;

import com.dcube.buffer.BufferOperation;
public class ChunkOperation implements BufferOperation{

	ChunkInfo chunk = null;
	
	public ChunkOperation(ChunkInfo chunk){
		this.chunk = chunk;
	}
	
	@Override
	public void dumpBuffer(ByteBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadBuffer(ByteBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

}
