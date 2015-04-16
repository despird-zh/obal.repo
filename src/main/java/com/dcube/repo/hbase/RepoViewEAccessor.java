package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntityEntry;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.repo.RepoConstants;

/**
 * RepoViewEAccessor be used to access the hierarchy data of different repository view
 **/
public class RepoViewEAccessor extends HEntityAccessor<EntityEntry>{

	public RepoViewEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW);
	}
	
	public RepoViewEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW,context);
	}

	@Override
	public EntityEntry newEntryObject() {
		
		return new EntityEntry();
	}

}
