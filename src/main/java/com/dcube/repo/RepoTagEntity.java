package com.dcube.repo;

import com.dcube.core.EntryKey;
import com.dcube.core.security.Principal;
import com.dcube.exception.MetaException;
import com.dcube.meta.EntityMeta;
import com.dcube.meta.GenericEntity;

public class RepoTagEntity extends GenericEntity{

	
	
	public RepoTagEntity(EntityMeta meta) {
		super(meta);
	}

	@Override
	public String getSchema(Principal principal, EntryKey key) {
		
		return RepoConstants.SCHEMA_TAG;
	}

	@Override
	public EntryKey newKey(Principal principal, Object... parameter) throws MetaException {
		
		return newKey(principal);
	}

	@Override
	public EntryKey newKey(Principal principal) throws MetaException {
		
		String key = String.valueOf(System.currentTimeMillis());		
		return new EntryKey(getEntityMeta().getEntityName(),key);
	}
}
