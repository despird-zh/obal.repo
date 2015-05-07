package com.dcube.repo;

import com.dcube.core.accessor.EntryParser;
import com.dcube.core.accessor.TraceableEntry;
import com.dcube.repo.RepoConstants.TagEnum;

/**
 * EntryTag is the tag attached on DocEntry
 * name is unique like unique key
 *  
 **/
public class FileTag extends EntryParser{

	/**
	 * Constructor with AccessControlEntry
	 **/
	public FileTag(TraceableEntry rawEntry){

		super(rawEntry);
	}	
	/**
	 * Default constructor 
	 **/
	public FileTag(String name){
		super(new TraceableEntry(RepoConstants.ENTITY_TAG,null));
		setTagName(name);
		setCategory(RepoConstants.TAG_UNCLASSIFIED);
	}
	
	/**
	 * Constructor with name and category
	 **/
	public FileTag(String name,String category){
		super(new TraceableEntry(RepoConstants.ENTITY_TAG,null));
		setTagName(name);
		setCategory(category);
	}
	
	/**
	 * Get the tag name 
	 **/
	public String getTagName() {
		return getAttrValue(TagEnum.Name.attribute, String.class);
	}

	/**
	 * Set the tag name 
	 **/
	public void setTagName(String name) {
		setAttrValue(TagEnum.Name.attribute, name);
	}

	/**
	 * Get the category 
	 **/
	public String getCategory() {
		return getAttrValue(TagEnum.Category.attribute, String.class);
	}

	/**
	 * Set the category 
	 **/
	public void setCategory(String category) {
		setAttrValue(TagEnum.Category.attribute, category);
	}
}
