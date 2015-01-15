package com.obal.buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.obal.disruptor.RingEvent;

public class BufferManager {
	
	/** the executor pool */
	private ExecutorService executor = null;

	/** the disruptor instance */
	private Disruptor<BufferEvent> disruptor = null;

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
}
