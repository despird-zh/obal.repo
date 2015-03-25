package com.dcube.repo;

/**
 * EntryTag is the tag attached on DocEntry
 * name is unique like unique key
 *  
 **/

public class FileTag {

	private String name;
	
	private String tagCategory;
	
	private String tagClass;
	
	private String tagSubclass;
	
	public FileTag(String name){
		
		this.name = name;
	}
	
	public String getName(){
		
		return name;				
	}

	public String getTagCategory() {
		return tagCategory;
	}

	public void setTagCategory(String tagCategory) {
		this.tagCategory = tagCategory;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public String getTagSubclass() {
		return tagSubclass;
	}

	public void setTagSubclass(String tagSubclass) {
		this.tagSubclass = tagSubclass;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
