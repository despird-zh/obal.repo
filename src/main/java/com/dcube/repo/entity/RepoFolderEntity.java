package com.dcube.repo.entity;

import com.dcube.core.EntryKey;
import com.dcube.core.security.Principal;
import com.dcube.exception.MetaException;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;
import com.dcube.repo.RepoConstants;

public class RepoFolderEntity extends GenericEntity{
	
	public RepoFolderEntity(EntityMeta meta) {
		super(meta);
	}

	@Override
	public String getSchema(Principal principal, EntryKey key) {
		
		return RepoConstants.SCHEMA_REPOVIEW;
	}

	@Override
	public EntryKey newEntryKey(Principal principal, Object... parameter) throws MetaException {
		
		String key = String.valueOf(System.currentTimeMillis());		
		return new EntryKey(getEntityMeta().getEntityName(),key);
		
	}

	@Override
	public EntryKey newEntryKey(Principal principal) throws MetaException {
		
		return newEntryKey(principal,new Object());
	}
}
