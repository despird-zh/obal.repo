package com.doccube.repo;

import com.doccube.admin.EntityAdmin;
import com.doccube.meta.EntityAttr;
import com.doccube.meta.EntityAttr.AttrType;
import com.doccube.meta.EntityAttr.AttrMode;
import com.doccube.meta.EntityMeta;
import com.doccube.meta.GenericEntity;

public class RepoSetup {
	
	public final static String REPO_PREFIX = "dcube.repo.";
	public final static String REPO_FILE = REPO_PREFIX + "file";
	public final static String REPO_CONTENT = REPO_PREFIX + "content";
	
	public RepoSetup() {}

	/**
	 * Setup the necessary schemas 
	 **/
	public void setup() {

		setupRepoPrimarySchema();		
		setupRepoContentSchema();
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

		EntityMeta meta = new EntityMeta(REPO_PREFIX + viewname);
		meta.setSchemaClass(GenericEntity.class.getName());
		meta.setDescription("File System path schema");
		meta.setTraceable(true);
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
		meta.setSchemaClass(RepoFileEntity.class.getName());
		meta.setSchema("dcube.repo.file");
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

		attr = new EntityAttr("i_tags", AttrMode.LIST, AttrType.STRING, "c0", "tags");
		meta.addAttr(attr);
		
		for(EntityAttr a1: attrs)
			meta.addAttr(a1);
		
		return meta;
	}
	
	/**
	 * Set up the content schema
	 **/
	private void setupRepoContentSchema() {

		EntityAdmin eadmin = EntityAdmin.getInstance();
		
		EntityMeta meta = new EntityMeta("dcube.repo.content");
		meta.setSchemaClass(GenericEntity.class.getName());
		meta.setDescription("File System path schema");
		meta.setTraceable(true);
		
		EntityAttr attr = new EntityAttr("i_fileids",AttrMode.SET, AttrType.STRING, "c0", "name");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_md5", "c0", "md5");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_storepath", "c0", "storepath");		
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_size", "c0", "size");		
		meta.addAttr(attr);

		attr = new EntityAttr("i_files", "c0", "files");		
		meta.addAttr(attr);
		
		eadmin.setupSchema(meta);
	}
}
