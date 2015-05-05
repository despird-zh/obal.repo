package com.dcube.repo;

import com.dcube.admin.EntityAdmin;
import com.dcube.core.security.AclConstants;
import com.dcube.meta.EntityAttr;
import com.dcube.meta.EntityConstants;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;
import com.dcube.meta.EntityAttr.AttrMode;
import com.dcube.meta.EntityAttr.AttrType;
import com.dcube.repo.RepoConstants.ContentEnum;
import com.dcube.repo.RepoConstants.FileEnum;
import com.dcube.repo.RepoConstants.FolderEnum;

/**
 * Following Entities to be created:
 * <ol>
 * 	<li>RepoView entity - single table, holds the Repository's view profile setting.</li>
 *  <li>Repository View Hierarchy entity - single table, hold the folder hierarchy of view </li>
 *  <li>Repository file entity - multiple entity in same table, hold the information of file</li>
 *  <li>Repository folder entity - multiple entity in same table(file), hold folder information</li>
 *  <li>Repository content entity - single table, hold folder information</li>
 *  <li>File tag entity - single table, hold tag information</li>
 * </ol>
 * 
 * <p>
 * Every file owns multiple contents, one is primary one, others are renditions.
 * Every rendition owns category, it will helps to organize the files
 * the rendition is not managed separately
 *
 * i_rendcontents = {"cate1":{"content0","content1","content2"},
 *                   "cate2":{"content3","content4","content5"}}
 * </p>
 * <p> 
 * The repository has one primary repository and more view repositories. 
 * All these data are stored in same table, while the primary repo name is constant.
 * </p>
 * <p>
 * File content delegate the binary data of a file entry. every file entry owns one primary content and other renditions
 * </p>
 **/
public class RepoSetup {
		
	public RepoSetup() {}

	/**
	 * Setup the necessary schemas 
	 **/
	public void setup() {

		EntityAdmin eadmin = EntityAdmin.getInstance();
		EntityMeta pmeta = buildFileNodeSchema(RepoConstants.ENTITY_BASE_FILE,null);
		eadmin.setupSchema(pmeta);
		//setupRepoPrimarySchema();		
		//setupFileContentSchema();
	}
	
	/**
	 * Set up the repository primary schema 
	 **/
	public void setupRepoPrimarySchema(){
		
		EntityAdmin eadmin = EntityAdmin.getInstance();
		EntityMeta pmeta = getRepoPrimaryViewSchema();
		eadmin.setupSchema(pmeta);
	}
	
	/**
	 * Get the repository primary view schema
	 * view name:pview.
	 **/
	public EntityMeta getRepoPrimaryViewSchema(){
		
		return buildRepoViewSchema(RepoConstants.REPO_PRIMARY);
	}
	
	/**
	 * build view schema 
	 * 
	 * @param viewname the name of repository view.
	 **/
	public EntityMeta buildRepoViewSchema(String viewname) {

		EntityMeta meta = new EntityMeta(RepoConstants.ENTITY_REPOVIEW);
		meta.setEntityClass(RepoViewEntity.class.getName());
		meta.setDescription("File System path schema");
		meta.setAccessorName(RepoConstants.ACCESSOR_ENTITY_REPOVIEW);
		meta.setTraceable(true);
		meta.setSchema(RepoConstants.SCHEMA_REPOVIEW);
		meta.setAccessControllable(true);
		EntityAttr attr = new EntityAttr("i_name", "c0", "name");
		meta.addAttr(attr);
		attr = new EntityAttr("i_fullpath", "c0", "fullpath");
		meta.addAttr(attr);
		attr = new EntityAttr("i_entity", "c0", "entity");
		meta.addAttr(attr);
		attr = new EntityAttr("i_parent_folders", "c0", "parent_folders");
		meta.addAttr(attr);
		attr = new EntityAttr("i_child_folders", AttrMode.MAP, AttrType.STRING, "c0", "child_folders");
		meta.addAttr(attr);
		attr = new EntityAttr("i_child_files", AttrMode.MAP, AttrType.STRING, "c0", "child_files");
		meta.addAttr(attr);
		
		return meta;
	}
	
	/**
	 * Build the file entity schema, the schema includes default attributes.
	 * 
	 * @param entityName the name of entity
	 * @param description the description of entity
	 * @param attrs the EntityAttr array
	 * 
	 **/
	public EntityMeta buildFileNodeSchema(String entityName, String description, EntityAttr ...attrs ) {

		EntityMeta meta = new EntityMeta(entityName);
		meta.setEntityClass(RepoFileEntity.class.getName());
		meta.setSchema(RepoConstants.SCHEMA_FILE);
		meta.setAccessorName(RepoConstants.ACCESSOR_ENTITY_FILE);
		meta.setDescription(description);
		meta.setTraceable(true);
		meta.setAccessControllable(true);
		
		EntityAttr attr = new EntityAttr(FileEnum.NodeName.attribute, 
				FileEnum.NodeName.colfamily, 
				FileEnum.NodeName.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.IsDirectory.attribute, 
				AttrType.BOOL,
				FileEnum.IsDirectory.colfamily, 
				FileEnum.IsDirectory.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.IsGroup.attribute, 
				AttrType.BOOL,
				FileEnum.IsGroup.colfamily, 
				FileEnum.IsGroup.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Keywards.attribute, 
				AttrMode.SET,
				AttrType.STRING,
				FileEnum.Keywards.colfamily, 
				FileEnum.Keywards.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Description.attribute, 
				FileEnum.Description.colfamily, 
				FileEnum.Description.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Owner.attribute, 
				FileEnum.Owner.colfamily, 
				FileEnum.Owner.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Parent.attribute, 
				FileEnum.Parent.colfamily, 
				FileEnum.Parent.qualifier);
		meta.addAttr(attr);
		
		/**
		 * tag attribute format:
		 * {
		 *  "xtag0" : "xtagcate1",
		 *  "xtag1" : "xtagcate1",
		 *  "xtag2" : "xtagcate2",
		 *  "xtag3" : "xtagcate3"
		 * }
		 **/
		attr = new EntityAttr(FileEnum.Tags.attribute, 
				AttrMode.MAP,
				AttrType.STRING,
				FileEnum.Tags.colfamily, 
				FileEnum.Tags.qualifier);
		meta.addAttr(attr);
		
		/**
		 * Because multiple file entry in same table schema, when fetch record by key only ,
		 * need to automatic identify out the entity, so every entry owns attribute to hold the entity 
		 **/
		attr = new EntityAttr(FileEnum.Entity.attribute, 
				FileEnum.Entity.colfamily, 
				FileEnum.Entity.qualifier);
		meta.addAttr(attr);
		
		/**
		 * rendition attribute format:
		 * {
		 *  "rend0" : "rendkey1",
		 *  "rend0" : "rendkey2",
		 *  "rend0" : "rendkey3",
		 *  "rend0" : "rendkey4"
		 * }
		 **/
		attr = new EntityAttr(FileEnum.RendContents.attribute, 
				AttrMode.MAP,
				AttrType.STRING,
				FileEnum.RendContents.colfamily, 
				FileEnum.RendContents.qualifier);
		meta.addAttr(attr);

		/**
		 * primary content is the key of content
		 **/
		attr = new EntityAttr(FileEnum.PrimaryContent.attribute, 
				FileEnum.PrimaryContent.colfamily, 
				FileEnum.PrimaryContent.qualifier);
		meta.addAttr(attr);

		attr = new EntityAttr(FileEnum.PrimaryFormat.attribute, 
				FileEnum.PrimaryFormat.colfamily, 
				FileEnum.PrimaryFormat.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Lock.attribute, 
				FileEnum.Lock.colfamily, 
				FileEnum.Lock.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FileEnum.Lockby.attribute, 
				FileEnum.Lockby.colfamily, 
				FileEnum.Lockby.qualifier);
		meta.addAttr(attr);
		
		// append other user defined attribute
		if(attrs != null){
			for(EntityAttr a1: attrs){
				meta.addAttr(a1);
			}
		}
		return meta;
	}
	
	/**
	 * Build the folder entity schema, the schema includes default attributes.
	 * 
	 * @param entityName the name of entity
	 * @param description the description of entity
	 * @param attrs the EntityAttr array
	 * 
	 **/
	public EntityMeta buildFolderNodeSchema(String entityName, String description, EntityAttr ...attrs ) {

		EntityMeta meta = new EntityMeta(entityName);
		meta.setEntityClass(RepoFileEntity.class.getName());
		meta.setSchema(RepoConstants.SCHEMA_FOLDER);
		meta.setAccessorName(RepoConstants.ACCESSOR_ENTITY_FOLDER);
		meta.setDescription(description);
		meta.setTraceable(true);
		meta.setAccessControllable(true);
		
		EntityAttr attr = new EntityAttr(FolderEnum.NodeName.attribute, 
				FolderEnum.NodeName.colfamily, 
				FolderEnum.NodeName.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FolderEnum.IsDirectory.attribute, 
				AttrType.BOOL,
				FolderEnum.IsDirectory.colfamily, 
				FolderEnum.IsDirectory.qualifier);
		meta.addAttr(attr);
						
		attr = new EntityAttr(FolderEnum.Description.attribute, 
				FolderEnum.Description.colfamily, 
				FolderEnum.Description.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FolderEnum.Owner.attribute, 
				FolderEnum.Owner.colfamily, 
				FolderEnum.Owner.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(FolderEnum.Parent.attribute, 
				FolderEnum.Parent.colfamily, 
				FolderEnum.Parent.qualifier);
		meta.addAttr(attr);
		
		/**
		 * Because multiple file entry in same table schema, when fetch record by key only ,
		 * need to automatic identify out the entity, so every entry owns attribute to hold the entity 
		 **/
		attr = new EntityAttr(FolderEnum.Entity.attribute, 
				FolderEnum.Entity.colfamily, 
				FolderEnum.Entity.qualifier);
		meta.addAttr(attr);

		/**
		 * {
		 *  "filename1" : "filekey1",
		 *  "filename2" : "filekey1",
		 * }
		 **/
		attr = new EntityAttr(FolderEnum.ChildFiles.attribute, 
				AttrMode.MAP,
				AttrType.STRING,
				FolderEnum.ChildFiles.colfamily, 
				FolderEnum.ChildFiles.qualifier);
		meta.addAttr(attr);
		
		/**
		 * {
		 *  "foldername1" : "folderkey1",
		 *  "foldername2" : "folderkey1",
		 * }
		 **/
		attr = new EntityAttr(FolderEnum.ChildFolders.attribute, 
				AttrMode.MAP,
				AttrType.STRING,
				FolderEnum.ChildFolders.colfamily, 
				FolderEnum.ChildFolders.qualifier);
		meta.addAttr(attr);
		
		// append other user defined attribute
		if(attrs != null){
			for(EntityAttr a1: attrs){
				meta.addAttr(a1);
			}
		}
		return meta;
	}
	
	/**
	 * Set up the content schema
	 **/
	private void setupFileContentSchema() {

		EntityAdmin eadmin = EntityAdmin.getInstance();
		
		EntityMeta meta = new EntityMeta(RepoConstants.ENTITY_CONTENT);
		meta.setEntityClass(RepoContentEntity.class.getName());
		meta.setAccessorName(RepoConstants.ACCESSOR_ENTITY_CONTENT);
		meta.setDescription("File System content schema");
		meta.setTraceable(true);
		meta.setSchema(RepoConstants.SCHEMA_CONTENT);
		
		EntityAttr attr = new EntityAttr(ContentEnum.FileIds.attribute,
				AttrMode.SET, 
				AttrType.STRING, 
				ContentEnum.FileIds.colfamily, 
				ContentEnum.FileIds.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(ContentEnum.MD5.attribute,
				ContentEnum.MD5.colfamily, 
				ContentEnum.MD5.qualifier);
		meta.addAttr(attr);
		
		attr = new EntityAttr(ContentEnum.StorePath.attribute,
				ContentEnum.StorePath.colfamily, 
				ContentEnum.StorePath.qualifier);		
		meta.addAttr(attr);
		
		attr = new EntityAttr(ContentEnum.Size.attribute,
				AttrType.INTEGER, 
				ContentEnum.Size.colfamily, 
				ContentEnum.Size.qualifier);		
		meta.addAttr(attr);

		attr = new EntityAttr(ContentEnum.Lock.attribute,
				AttrType.BOOL, 
				ContentEnum.Lock.colfamily, 
				ContentEnum.Lock.qualifier);		
		meta.addAttr(attr);
		
		attr = new EntityAttr(ContentEnum.LockFile.attribute,
				ContentEnum.LockFile.colfamily, 
				ContentEnum.LockFile.qualifier);		
		meta.addAttr(attr);
		
		attr = new EntityAttr(ContentEnum.Format.attribute,
				ContentEnum.Format.colfamily, 
				ContentEnum.Format.qualifier);		
		meta.addAttr(attr);
		
		eadmin.setupSchema(meta);
	}
}
