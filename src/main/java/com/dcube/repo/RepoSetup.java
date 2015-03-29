package com.dcube.repo;

import com.dcube.admin.EntityAdmin;
import com.dcube.meta.EntityAttr;
import com.dcube.meta.EntityConstants;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;
import com.dcube.meta.EntityAttr.AttrMode;
import com.dcube.meta.EntityAttr.AttrType;

public class RepoSetup {
	
	public final static String REPO_PREFIX = EntityConstants.ENTITY_PREFIX + "repo.";
	public final static String REPO_FILE = REPO_PREFIX + "file";
	public final static String REPO_CONTENT = REPO_PREFIX + "content";
	
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

		EntityMeta meta = new EntityMeta(REPO_PREFIX + viewname);
		meta.setEntityClass(GenericEntity.class.getName());
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
		meta.setSchema(REPO_FILE);
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
		
		EntityMeta meta = new EntityMeta(REPO_CONTENT);
		meta.setEntityClass(GenericEntity.class.getName());
		meta.setDescription("File System content schema");
		meta.setTraceable(true);
		
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