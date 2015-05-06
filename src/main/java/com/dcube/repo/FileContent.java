package com.dcube.repo;

import java.util.Set;

import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.accessor.TraceableEntry;
import com.dcube.core.accessor.EntryParser;
import com.dcube.repo.RepoConstants.ContentEnum;

/**
		FileIds(   "i_files",    "files"),
		MD5(  "i_md5",   "md5"),
		StorePath("i_storepath",  "storepath"),
		Size("i_size",  "size"),
		Lock("i_lock","lock"), // WRITE/READ
		LockFile("i_lock_file","lockfile") ,
		Format(  "i_format",   "format");
 **/
public class FileContent extends EntryParser {

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
	
	
	/** get the attribute value */
	@SuppressWarnings("unused")
	private <K> K getAttrValue(String attribute, Class<K> type){
		EntityEntry temp = (EntityEntry)rawEntry;
		return temp.getAttrValue(attribute, type);
	}
	
	/** set the attribute value */
	private void setAttrValue(String attribute, Object value){
		EntityEntry temp = (EntityEntry)rawEntry;		
		temp.changeAttrValue(attribute, value);
	}
}
