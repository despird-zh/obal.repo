package com.dcube.repo;

import com.dcube.core.security.AclConstants;
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
	public static final String ENTITY_BASE_FILE = EntityConstants.ENTITY_PREFIX + "repo.base.file";
	public static final String ENTITY_BASE_FOLDER = EntityConstants.ENTITY_PREFIX + "repo.base.folder";
	public static final String ENTITY_TAG = EntityConstants.ENTITY_PREFIX + "repo.tag";
	public static final String ENTITY_CONTENT = EntityConstants.ENTITY_PREFIX + "repo.content";
	/** Repository view is stored in one table. */
	public static final String ENTITY_REPOVIEW = EntityConstants.ENTITY_PREFIX + "repo.view";
	
	
	/** File and folder are stored in same table */
	public static final String SCHEMA_FILE = EntityConstants.ENTITY_PREFIX + "repo.primary";
	public static final String SCHEMA_FOLDER = EntityConstants.ENTITY_PREFIX + "repo.primary";
	
	public static final String SCHEMA_TAG = EntityConstants.ENTITY_PREFIX + "repo.tag";
	public static final String SCHEMA_CONTENT = EntityConstants.ENTITY_PREFIX + "repo.content";
	
	/** Repository view is stored in one table. */
	public static final String SCHEMA_REPOVIEW = EntityConstants.ENTITY_PREFIX + "repo.view";
	public static final String SCHEMA_REPOVIEW_HIER = EntityConstants.ENTITY_PREFIX + "repo.viewhier";
	
	/** Unclassified tag category */
	public static final String TAG_UNCLASSIFIED = "default";
	
	/** File content lock mode:READ,WRITE */
	public static enum ContentLock{
		
		NONE,
		READ,
		WRITE;
	}
	
	/**
	 * The meta info enumerator 
	 **/
	public static enum FileEnum{

		NodeName(   "i_name", "name"),
		IsDirectory(  "i_isdirectory",   "isdirectory"),
		Keywards(  "i_keywards",   "keywards"), // Set data
		Description(  "i_description",   "description"),
		Owner("i_owner", AclConstants.CF_ACL , "owner"),
		Parent("i_parent",  "parent"), // parent folder
		Tags( "i_tags", "tags"), // map
		Entity( "i_entity", "entity"),// used to get the attribute list 
		RendContents( "i_rend_contents", "rendcontents"),// map(rendition name,content id)
		PrimaryContent( "i_primary_content", "primarycontent"), // primary content id
		PrimaryFormat( "i_primary_format", "primaryformat"), // primary content format
		Locked("i_locked",  "locked"), // locked or not
		LockedBy("i_lockedby",  "lockedby"); // locked by
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private FileEnum(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
		
		private FileEnum(String attribute, String colfamily, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = colfamily;
	    }
	}

	/**
	 * The meta info enumerator 
	 **/
	public static enum FolderEnum{

		NodeName(   "i_name",    "name"),
		Description(  "i_description",   "description"),
		IsDirectory(  "i_isdirectory",   "isdirectory"),
		Owner("i_owner", AclConstants.CF_ACL, "owner"),
		Entity( "i_entity", "entity"),
		Parent("i_parent",  "parent"),
		ChildFiles( "i_child_files", "childfiles"),		
		ChildFolders( "i_child_folders", "childfolders");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private FolderEnum(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
		
		private FolderEnum(String attribute, String colfamily, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = colfamily;
	    }
	}
	
	/** The content enum */
	public static enum ContentEnum{

		Name(   "i_name",    "name"),
		FileIds(   "i_files",    "files"),
		MD5(  "i_md5",   "md5"),
		StorePath("i_storepath",  "storepath"),
		Size("i_size",  "size"),
		Lock("i_lock","lock"), // WRITE/READ
		LockFile("i_lock_file","lockfile") ,
		Format(  "i_format",   "format");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private ContentEnum(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }

	}
	
	/** The repository view enum */
	public static enum RepoViewEnum{
		
		Name(  "i_name",   "name"),
		Description(  "i_description",   "description"),
		IsPrimary(  "i_isprimary",   "isprimary"),
		Presents(  "i_presents",   "presents"),//
		Profile(  "i_profile",   "profile");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private RepoViewEnum(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
	}

	/** The File Tag enum */
	public static enum TagEnum{
		
		Name(  "i_name",   "name"),
		Category(  "i_category",   "category");
		
		public final String attribute;
		public final String qualifier;
		public final String colfamily;
		
		/**
		 * Hide default constructor 
		 **/
		private TagEnum(String attribute, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = EntityConstants.ATTR_DFT_COLUMN;
	    }
		
		private TagEnum(String attribute, String colfamily, String qualifier){  
			this.attribute = attribute;
			this.qualifier = qualifier;
			this.colfamily = colfamily;
	    }
	}
}
