package com.dcube.repo;

/**
 * EntryTag is the tag attached on DocEntry
 * name is unique like unique key
 *  
 **/
public class FileTag {

	/** Tag name */
	private String name;
	/** Tag category */
	private String category = RepoConstants.TAG_UNCLASSIFIED;

	/**
	 * Default constructor 
	 **/
	public FileTag(String name){
		this.name = name;
	}
	
	/**
	 * Constructor with name and category
	 **/
	public FileTag(String name,String category){
		this.name = name;
		this.category = category;
	}
	
	/**
	 * Get the tag name 
	 **/
	public String getTagName() {
		return name;
	}

	/**
	 * Set the tag name 
	 **/
	public void setTagName(String name) {
		this.name = name;
	}

	/**
	 * Get the category 
	 **/
	public String getCategory() {
		return category;
	}

	/**
	 * Set the category 
	 **/
	public void setCategory(String category) {
		this.category = category;
	}
}
