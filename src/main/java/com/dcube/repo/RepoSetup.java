package com.dcube.repo;

import com.dcube.admin.EntityAdmin;
import com.dcube.meta.EntityAttr;
import com.dcube.meta.EntityConstants;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;
import com.dcube.meta.EntityAttr.AttrMode;
import com.dcube.meta.EntityAttr.AttrType;

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

		setupRepoPrimarySchema();		
		setupFileContentSchema();
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
		
		return buildRepoViewSchema("pview");
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
		attr = new EntityAttr("i_child_folders", AttrMode.JMAP, AttrType.STRING, "c0", "child_folders");
		meta.addAttr(attr);
		attr = new EntityAttr("i_child_files", AttrMode.JMAP, AttrType.STRING, "c0", "child_files");
		meta.addAttr(attr);
		
		return meta;
	}
	
	/**
	 * Build the file entity schema, the schema includes default attributes.
	 * <ol>
	 * 	<li>i_name - the name of file</li>
	 *  <li>i_entity - the entity meta name of file meta info entry</li>
	 *  <li>i_content - the file content id</li>
	 *  <li>i_format - the format of file</li>
	 *  <li>i_tags - the tags of file</li>
	 * </ol>
	 * @param entityName the name of entity
	 * @param description the description of entity
	 * @param attrs the EntityAttr array
	 * 
	 **/
	public EntityMeta buildFileEntitySchema(String entityName, String description, EntityAttr ...attrs ) {

		EntityMeta meta = new EntityMeta(entityName);
		meta.setEntityClass(RepoFileEntity.class.getName());
		meta.setSchema(RepoConstants.SCHEMA_FILE);
		meta.setDescription(description);
		meta.setTraceable(true);
		meta.setAccessControllable(true);
		
		EntityAttr attr = new EntityAttr("i_name", "c0", "name");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_entity", "c0", "entity");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_content", "c0", "content");
		meta.addAttr(attr);

		attr = new EntityAttr("i_format", "c0", "format");
		meta.addAttr(attr);

		attr = new EntityAttr("i_tags", AttrMode.JLIST, AttrType.STRING, "c0", "tags");
		meta.addAttr(attr);
		
		for(EntityAttr a1: attrs)
			meta.addAttr(a1);
		
		return meta;
	}
	
	/**
	 * Set up the content schema
	 **/
	private void setupFileContentSchema() {

		EntityAdmin eadmin = EntityAdmin.getInstance();
		
		EntityMeta meta = new EntityMeta(RepoConstants.ENTITY_CONTENT);
		meta.setEntityClass(RepoContentEntity.class.getName());
		meta.setDescription("File System content schema");
		meta.setTraceable(true);
		meta.setSchema(RepoConstants.SCHEMA_CONTENT);
		
		EntityAttr attr = new EntityAttr("i_fileids",AttrMode.JSET, AttrType.STRING, "c0", "name");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_md5", "c0", "md5");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_storepath", "c0", "storepath");		
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_size", "c0", "size");		
		meta.addAttr(attr);

		eadmin.setupSchema(meta);
	}
}
