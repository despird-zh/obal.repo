package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HAccessControlWrapper;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.repo.RepoConstants;

public class FileEAccessor extends HEntityAccessor<AccessControlEntry>{

	public FileEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FILE);
	}
	
	public FileEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FILE,context);
	}

	@Override
	public HEntryWrapper<AccessControlEntry> getEntryWrapper() {
		
		return new HAccessControlWrapper();
	}

}
