package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HAccessControlWrapper;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.repo.RepoConstants;

public class RepoViewFolderEAccessor extends HEntityAccessor<AccessControlEntry>{

	public RepoViewFolderEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW_FOLER);
	}
	
	public RepoViewFolderEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW_FOLER,context);
	}

	@Override
	public HEntryWrapper<AccessControlEntry> getEntryWrapper() {
		
		return new HAccessControlWrapper();
	}

}
