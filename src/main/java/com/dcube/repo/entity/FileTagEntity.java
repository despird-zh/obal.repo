package com.dcube.repo.entity;

import com.dcube.core.EntryKey;
import com.dcube.core.security.Principal;
import com.dcube.exception.MetaException;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;
import com.dcube.repo.RepoConstants;

public class FileTagEntity extends GenericEntity{

	
	
	public FileTagEntity(EntityMeta meta) {
		super(meta);
	}

	@Override
	public String getSchema(Principal principal, EntryKey key) {
		
		return RepoConstants.SCHEMA_TAG;
	}

	@Override
	public EntryKey newEntryKey(Principal principal, Object... parameter) throws MetaException {
		
		return newEntryKey(principal);
	}

	@Override
	public EntryKey newEntryKey(Principal principal) throws MetaException {
		
		String key = String.valueOf(System.currentTimeMillis());		
		return new EntryKey(getEntityMeta().getEntityName(),key);
	}
}
