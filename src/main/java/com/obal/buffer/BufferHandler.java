package com.obal.buffer;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventHandler;

public class BufferHandler implements EventHandler<BufferEvent> {

	public void onEvent(BufferEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		
		BufferOperation operation = event.getOperation();
		if(operation == null)
			return;
		
		ByteBuffer buffer = event.getByteBuffer();
		buffer.clear();
		operation.writeBuffer(buffer);		
		operation.readBuffer(buffer);
	}	
}
