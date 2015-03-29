package com.dcube.repo;

public class FileRepository {

	private String name;
	
	public FileRepository(String name){
		
		this.name = name;
	}
	
	public boolean isPrimary(){
		
		return false;
	}
	
	public String getName(){
		
		return name;
	}
}
