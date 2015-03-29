package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntryInfo;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.repo.RepoConstants;

public class FileEAccessor extends HEntityAccessor<EntryInfo>{

	public FileEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FILE);
	}
	
	public FileEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FILE,context);
	}

	@Override
	public HEntryWrapper<EntryInfo> getEntryWrapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
