package com.dcube.buffer;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

public class BufferEvent {
	
	private MappedByteBuffer mapbytebuf = null;
	
	private BufferOperation operation = null;
	
	public BufferEvent(MappedByteBuffer mapbytebuf){
		
		this.mapbytebuf = mapbytebuf;
	}
	
	public ByteBuffer getByteBuffer(){
		
		return this.mapbytebuf;
	}
	
	public BufferOperation getOperation(){
		
		return this.operation;
	}
	
	public void setOperation(BufferOperation operation){
		
		this.operation = operation;
	}
}
