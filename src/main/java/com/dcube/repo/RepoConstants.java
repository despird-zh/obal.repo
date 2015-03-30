package com.dcube.repo;

import com.dcube.meta.EntityConstants;

public class RepoConstants {
	
	public static final String ACCESSOR_ENTITY_CONTENT = "entity.content";
	public static final String ACCESSOR_ENTITY_FILE = "entity.file";
	public static final String ACCESSOR_ENTITY_FOLDER = "entity.folder";
	public static final String ACCESSOR_ENTITY_REPOPRIMARY = "entity.repoprimary";
	public static final String ACCESSOR_ENTITY_REPOVIEW = "entity.repoview";
	public static final String ACCESSOR_ENTITY_REPOVIEW_FOLER = "entity.repoview.folder";
	
	/** name of primary repository */
	public static final String REPO_PRIMARY = "primary";
	
	/** File and folder are stored in same table */
	public static final String ENTITY_FILE = EntityConstants.ENTITY_PREFIX + "repo.file";
	public static final String ENTITY_FOLDER = EntityConstants.ENTITY_PREFIX + "repo.folder";
	public static final String ENTITY_TAG = EntityConstants.ENTITY_PREFIX + "repo.tag";

	/** Repository view is stored in one table. */
	public static final String ENTITY_REPOVIEW = EntityConstants.ENTITY_PREFIX + "repo.view";
	
	
	/** File and folder are stored in same table */
	public static final String SCHEMA_FILE = EntityConstants.ENTITY_PREFIX + "repo.primary";
	public static final String SCHEMA_FOLDER = EntityConstants.ENTITY_PREFIX + "repo.primary";
	public static final String SCHEMA_TAG = EntityConstants.ENTITY_PREFIX + "repo.tag";
	public static final String SCHEMA_CONTENT = EntityConstants.ENTITY_PREFIX + "repo.content";
	
	/** Repository view is stored in one table. */
	public static final String SCHEMA_REPOVIEW = EntityConstants.ENTITY_PREFIX + "repo.view";
	
	/**
	 * The meta info enumerator 
	 **/
	public static enum FileInfo{

		Name(   "i_name",    "name"),
		IsDirectory(  "i_isdirectory",   "isdirectory"),
		IsGroup(  "i_isgroup",   "isgroup"),
		GroupFiles(  "i_groupfiles",   "groupfiles"),
		Owner("i_owner",  "owner"),
		Tags( "i_tags", "tags"),
		Entity( "i_entity", "entity"),
		RendContents( "i_rendcontents", "rendcontents"),
		PrimaryContent( "i_primarycontent", "primarycontent"),
		PrimaryFormat( "i_primaryformat", "primaryformat"),
		Lock("i_lock",  "lock"),
		Lockby("i_lockby",  "lockby");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private FileInfo(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
		
		private FileInfo(String attribute, String colfamily, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = colfamily;
	    }
	}
	
	public static enum ContentInfo{

		FileIds(   "i_files",    "files"),
		MD5(  "i_md5",   "md5"),
		StorePath("i_storepath",  "storepath"),
		Size("i_size",  "size"),
		Lock("i_lock","lock"),
		LockFile("i_lockfile","lockfile"),
		Format(  "i_format",   "format");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private ContentInfo(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
		
		private ContentInfo(String attribute, String colfamily, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = colfamily;
	    }
	}
}
