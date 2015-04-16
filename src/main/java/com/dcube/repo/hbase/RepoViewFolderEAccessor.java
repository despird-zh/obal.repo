package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.repo.RepoConstants;

public class RepoViewFolderEAccessor extends HEntityAccessor<AccessControlEntry>{

	public RepoViewFolderEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW_FOLER);
	}
	
	public RepoViewFolderEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW_FOLER,context);
	}

	@Override
	public AccessControlEntry newEntryObject() {
		
		return new AccessControlEntry();
	}

}
