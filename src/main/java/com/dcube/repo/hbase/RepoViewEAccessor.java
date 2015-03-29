package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntryInfo;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.core.hbase.HRawWrapper;
import com.dcube.repo.RepoConstants;

/**
 * RepoViewEAccessor be used to access the hierarchy data of different repository view
 **/
public class RepoViewEAccessor extends HEntityAccessor<EntryInfo>{

	public RepoViewEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW);
	}
	
	public RepoViewEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW,context);
	}

	@Override
	public HEntryWrapper<EntryInfo> getEntryWrapper() {

		return new HRawWrapper();
	}

}
