package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HAccessControlWrapper;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.repo.RepoConstants;

public class FolderEAccessor extends HEntityAccessor<AccessControlEntry>{

	public FolderEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FILE);
	}
	
	public FolderEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FILE,context);
	}

	@Override
	public HEntryWrapper<AccessControlEntry> getEntryWrapper() {
		
		return new HAccessControlWrapper();
	}

}
