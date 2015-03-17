package com.doccube.buffer;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventHandler;

public class BufferHandler implements EventHandler<BufferEvent> {

	public void onEvent(BufferEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		
		BufferOperation operation = event.getOperation();
		if(operation == null)
			return;
		
		ByteBuffer buffer = event.getByteBuffer();
		buffer.clear();// prepare
		operation.loadBuffer(buffer);// write data to buffer
		operation.dumpBuffer(buffer); // read data from buffer
		// clear operation binding
		event.setOperation(null);
	}	
}
