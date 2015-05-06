package com.dcube.repo;

import java.util.Set;

import com.dcube.core.accessor.TraceableEntry;
import com.dcube.core.accessor.EntryParser;
import com.dcube.repo.RepoConstants.ContentEnum;
import com.dcube.repo.RepoConstants.ContentLock;

/**
 *
 **/
public class FileContent extends EntryParser {

	/**
	 * Constructor with the TraceableEntry
	 **/
	public FileContent(TraceableEntry rawEntry){
		super(rawEntry);
	}
	
	/**
	 * Default constructor 
	 **/
	public FileContent(){

		super(new TraceableEntry(RepoConstants.ENTITY_CONTENT,null));
	}
	/**
	 * the constructor 
	 * @param name the content name
	 * @param key the key  
	 **/
	public FileContent(String name, String key) {
		super(new TraceableEntry(RepoConstants.ENTITY_CONTENT, key));
		setAttrValue(ContentEnum.Name.attribute, name);
	}

	/**
	 * the constructor 
	 * @param name the content name
	 **/
	public FileContent(String name){
		
		super(new TraceableEntry(RepoConstants.ENTITY_CONTENT, null));
		setAttrValue(ContentEnum.Name.attribute, name);
	}
	
	/**
	 * Get the content id 
	 **/
	public String getContentId(){
		
		return getEntryKey().getKey();
	}
	
	/**
	 * Get the name of content 
	 **/
	public String getName(){
		
		return getAttrValue(ContentEnum.Name.attribute, String.class);
	}
	
	/**
	 * Get the file ids which hold this content 
	 **/
	@SuppressWarnings("unchecked")
	public Set<String> getFileIds(){
		
		return getAttrValue(ContentEnum.FileIds.attribute, Set.class);
	}
	
	/**
	 * Get store path of this content binary data 
	 **/
	public String getStorePath(){
		
		return getAttrValue(ContentEnum.StorePath.attribute, String.class);
	}
	
	/**
	 * Get the md5 verification information 
	 **/
	public String getMD5(){
		
		return getAttrValue(ContentEnum.MD5.attribute, String.class);
	}
	
	/**
	 * Get the size of file content
	 **/
	public int getSize(){
		
		return getAttrValue(ContentEnum.Size.attribute, Integer.class);
	}
	
	/**
	 * Get the format of file content
	 **/
	public String getFormat(){
		
		return getAttrValue(ContentEnum.Format.attribute, String.class);
	}
	
	/**
	 * Get the lock of content 
	 **/
	public ContentLock getLock(){
		
		String lock = getAttrValue(ContentEnum.Lock.attribute, String.class);
		return ContentLock.valueOf(lock);
	}
	
	/**
	 * Get the lock file id 
	 **/
	public String getLockFile(){
		
		return getAttrValue(ContentEnum.LockFile.attribute, String.class);
	}
}
