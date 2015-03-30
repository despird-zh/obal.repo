package com.dcube.repo;

public class FileRepository {

	private String name;
	
	/**
	 * Default build the primary repository
	 **/
	public FileRepository(){
		
		this.name = RepoConstants.REPO_PRIMARY;
	}
	
	/**
	 * Build view repository 
	 **/
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
