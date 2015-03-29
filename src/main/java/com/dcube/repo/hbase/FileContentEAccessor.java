package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntryInfo;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.core.hbase.HRawWrapper;
import com.dcube.repo.RepoConstants;

public class FileContentEAccessor extends HEntityAccessor<EntryInfo>{

	public FileContentEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_CONTENT);
	}
	
	public FileContentEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_CONTENT,context);
	}

	@Override
	public HEntryWrapper<EntryInfo> getEntryWrapper() {
		
		return new HRawWrapper();
	}

}
