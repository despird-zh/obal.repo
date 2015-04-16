package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.repo.RepoConstants;

public class FolderEAccessor extends HEntityAccessor<AccessControlEntry>{

	public FolderEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FOLDER);
	}
	
	public FolderEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FOLDER,context);
	}

	@Override
	public AccessControlEntry newEntryObject() {
		
		return new AccessControlEntry();
	}

}
