package com.doccube.buffer;

import java.nio.ByteBuffer;

public interface BufferOperation {
	
	public void dumpBuffer(final ByteBuffer buffer);
	
	public void loadBuffer(final ByteBuffer buffer);
}
