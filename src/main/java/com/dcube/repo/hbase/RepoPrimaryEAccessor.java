package com.dcube.repo.hbase;

import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.EntryInfo;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.core.hbase.HEntryWrapper;
import com.dcube.core.hbase.HRawWrapper;
import com.dcube.repo.RepoConstants;

/**
 * RepoPrimaryEAccessor be used to access the Primary Repository 
 * hierarchy data.
 **/
public class RepoPrimaryEAccessor extends HEntityAccessor<EntryInfo>{

	public RepoPrimaryEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOPRIMARY);
	}
	
	public RepoPrimaryEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOPRIMARY,context);
	}

	@Override
	public HEntryWrapper<EntryInfo> getEntryWrapper() {

		return new HRawWrapper();
	}
}
