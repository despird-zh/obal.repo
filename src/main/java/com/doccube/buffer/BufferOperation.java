package com.doccube.buffer;

import java.nio.ByteBuffer;

public interface BufferOperation {
		
	public void writeBuffer(final ByteBuffer buffer);
	
	public void readBuffer(final ByteBuffer buffer);
}
