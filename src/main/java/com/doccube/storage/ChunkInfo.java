package com.doccube.storage;

public class ChunkInfo {

	public ChunkInfo(Long fileSize, int chunkSize){
		
		this.fileSize = fileSize;
		this.chunkSize = chunkSize;
		calculate();
	}
	
	private long fileSize = -1l; // size of file	
	private long chunkAmount = -1l; // amount of total chunks
	private int chunkIndex = -1; // index of expected chunk
	private int chunkSize = -1; // average size of chunk
	private byte[] chunkData = null; // data
	private int chunkLength = -1; // length of expected chunk data
	
	public long chunkOffset(){
		
		return chunkIndex * chunkSize;
	}
	
	public long chunkLength(){
		
		return chunkLength;
	}
			
	public void setChunkIndex(int index){
		this.chunkIndex = index;
		calculate();
	}
	
	public long chunkAmount(){
		
		return chunkAmount;
	}
	
	public void setChunkData(byte[] chunkData){
		
		this.chunkData = chunkData;
	}
	
	public byte[] getChunkData(){
		
		return this.chunkData;
	}
	
	public byte[] takeChunkData(){
		
		byte[] rtv = this.chunkData;
		this.chunkData = null;
		
		return rtv;
	}
	
	private void calculate(){
		// index set 
		if(chunkIndex >= 0){
			long bytesRemaining = fileSize - chunkIndex * chunkSize;
			// re-calculate the current chunk length
			if(bytesRemaining <= 0 ) {
				this.chunkLength = 0;
				
			}else{			
				this.chunkLength = (bytesRemaining > (long)chunkSize) ? 
						chunkSize : (int)bytesRemaining;
			}
		}
		// re-calculate the chunks amount
		chunkAmount = (int) (fileSize / chunkSize);
		if (fileSize % chunkSize > 0) {
			chunkAmount++;
		}
	}
}
