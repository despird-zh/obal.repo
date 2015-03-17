package com.doccube.repo;

import com.doccube.admin.EntityAdmin;
import com.doccube.meta.EntityAttr;
import com.doccube.meta.EntityAttr.AttrType;
import com.doccube.meta.EntityAttr.AttrMode;
import com.doccube.meta.EntityMeta;
import com.doccube.meta.GenericEntity;

public class RepoSetup {
	
	public RepoSetup() {}

	/**
	 * Setup the necessary schemas 
	 **/
	public void setup() {

		setupFSPathSchema();
		
		setupFSContentSchema();
	}
	
	//
	private void setupFSPathSchema() {

		EntityAdmin eadmin = EntityAdmin.getInstance();
		
		EntityMeta meta = new EntityMeta("dcube.repo.path");
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
		
		eadmin.setupSchema(meta);
	}
	
	public EntityMeta getFileEntitySchema(String entityName, String description) {

		EntityMeta meta = new EntityMeta(entityName);
		meta.setSchemaClass(RepoFileEntity.class.getName());
		meta.setDescription(description);
		meta.setTraceable(true);
		meta.setAccessControllable(true);
		
		EntityAttr attr = new EntityAttr("i_name", "c0", "name");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_entity", "c0", "entity");
		meta.addAttr(attr);
		
		attr = new EntityAttr("i_file", "c0", "file");
		meta.addAttr(attr);

		return meta;
	}
	
	private void setupFSContentSchema() {

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
				
		eadmin.setupSchema(meta);
	}
}
