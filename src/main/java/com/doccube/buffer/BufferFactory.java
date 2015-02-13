package com.doccube.buffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.lmax.disruptor.EventFactory;

public class BufferFactory implements EventFactory<BufferEvent>{

	private long chunkSize = 1024 * 1024 * 2;
	private FileChannel diskBufferFile = null;
	private int chunkCount = 0;

	boolean ready = true;
	public BufferFactory(long chunkSize){
		
		this.chunkSize = chunkSize;
		File f = new File("c:/tmp/mapped_chunk_buffer.tmp");
		f.delete();
		try {
			diskBufferFile = new RandomAccessFile(f, "rw").getChannel();
			
		} catch (FileNotFoundException e) {
			ready = false;
			e.printStackTrace();
		}
	}
	
	public BufferEvent newInstance() {
		MappedByteBuffer mem = getMappedBuffer();
		
		return new BufferEvent(mem);
	}

	private MappedByteBuffer getMappedBuffer(){
		MappedByteBuffer mem = null;
		try {
			mem = diskBufferFile.map(FileChannel.MapMode.READ_WRITE, chunkCount * chunkSize, chunkSize);
			int avail = mem.remaining();
			while(mem.hasRemaining()){
				
				if( avail >=8 )
					mem.putLong(0l);
				else
					mem.put((byte)0);
				
				avail = mem.remaining();
			}
			
			chunkCount++;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mem;
	}
}
