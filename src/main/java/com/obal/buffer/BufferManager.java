package com.obal.buffer;

import java.util.concurrent.ExecutorService;

import com.lmax.disruptor.dsl.Disruptor;
import com.obal.disruptor.RingEvent;

public class BufferManager {
	
	/** the executor pool */
	private ExecutorService executor = null;

	/** the disruptor instance */
	private Disruptor<RingEvent> disruptor = null;

	public void initial(){
		
		
		
	}
}
