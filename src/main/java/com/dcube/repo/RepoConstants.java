package com.dcube.repo;

import com.dcube.meta.EntityConstants;

public class RepoConstants {
	
	public static String ACCESSOR_ENTITY_CONTENT = "entity.content";
	public static String ACCESSOR_ENTITY_FILE = "entity.file";
	public static String ACCESSOR_ENTITY_REPOVIEW = "entity.repoview";
	
	/**
	 * The meta info enumerator 
	 **/
	public static enum FileInfo{

		Name(   "i_name",    "name"),
		IsDirectory(  "i_isdirectory",   "isdirectory"),
		Owner("i_owner",  "owner"),
		Tags( "i_tags", "tags"),
		Entity( "i_entity", "entity"),
		RendContents( "i_rendcontents", "rendcontents"),
		PrimaryContent( "i_primarycontent", "primarycontent");
		
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
