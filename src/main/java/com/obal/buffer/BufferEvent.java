package com.obal.buffer;

import java.io.File;
import java.nio.MappedByteBuffer;

import com.lmax.disruptor.EventFactory;

public class BufferEvent {
	
	private MappedByteBuffer mapbbuf = null;
	
	public BufferEvent(MappedByteBuffer mapbbuf){
		
		this.mapbbuf = mapbbuf;
	}
	
}
