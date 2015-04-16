package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.repo.RepoConstants;

public class FileContentEAccessor extends HEntityAccessor<EntityEntry>{

	public FileContentEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_CONTENT);
	}
	
	public FileContentEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_CONTENT,context);
	}

	@Override
	public EntityEntry newEntryObject() {
		
		return new EntityEntry();
	}

}
