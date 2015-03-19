package com.dcube.buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class BufferManager {
	
	/** the executor pool */
	private ExecutorService executor = null;

	/** the disruptor instance */
	private Disruptor<BufferEvent> disruptor = null;

	@SuppressWarnings("unchecked")
	public void initial(){
		
		// Executor that will be used to construct new threads for consumers
		this.executor = Executors.newCachedThreadPool();
		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;
		BufferFactory eventbuilder = new BufferFactory(1l);
		// Construct the Disruptor
		disruptor = new Disruptor<BufferEvent>(eventbuilder, bufferSize, executor);
		BufferHandler handler = new BufferHandler();
		// Connect the handler
		disruptor.handleEventsWith(handler);
		
	}
	
	public void publishOperation(BufferOperation operation){
		
		RingBuffer<BufferEvent> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();  // Grab the next sequence
	    try
	    {
	    	BufferEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
	        event.setOperation(operation);
	    }
	    finally
	    {
	        ringBuffer.publish(sequence);// for the sequence
	    }
	}
}
