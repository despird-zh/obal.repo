package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntryInfo;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.repo.RepoConstants;

public class RepoViewAccessor extends HEntityAccessor<EntryInfo>{

	public RepoViewAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW);
	}
	
	public RepoViewAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW,context);
	}

	@Override
	public HEntryWrapper<EntryInfo> getEntryWrapper() {
		return null;
	}

}
