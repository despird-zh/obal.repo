package com.obal.buffer;

import java.io.File;
import java.nio.MappedByteBuffer;

import com.lmax.disruptor.EventFactory;

public class ChunkBufferEvent {
	
	private MappedByteBuffer mapbbuf = null;
	
	public ChunkBufferEvent(MappedByteBuffer mapbbuf){
		
		this.mapbbuf = mapbbuf;
	}
	
}
