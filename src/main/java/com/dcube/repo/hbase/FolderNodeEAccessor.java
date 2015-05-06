package com.dcube.repo.hbase;

import com.dcube.core.IEntryConverter;
import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.exception.BaseException;
import com.dcube.repo.FolderNode;
import com.dcube.repo.RepoConstants;

public class FolderNodeEAccessor extends HEntityAccessor<AccessControlEntry>{

	public FolderNodeEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FOLDER);
	}
	
	public FolderNodeEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FOLDER,context);
	}

	@Override
	public AccessControlEntry newEntryObject() {
		
		return new AccessControlEntry();
	}

	@SuppressWarnings("unchecked")
	@Override 
	public <To> IEntryConverter<AccessControlEntry, To> getEntryConverter(Class<To> cto){
		
		if(cto.equals(FolderNode.class)){
			
			IEntryConverter<AccessControlEntry,FolderNode> converter = new IEntryConverter<AccessControlEntry,FolderNode>(){

				@Override
				public FolderNode toTarget(AccessControlEntry fromObject)
						throws BaseException {

					FolderNode fnode = new FolderNode(fromObject);
					return fnode;
				}

				@Override
				public AccessControlEntry toSource(FolderNode toObject)
						throws BaseException {
					
					AccessControlEntry entry = (AccessControlEntry)toObject.getGenericEntry();
					return entry;
				}
				
			};
			
			return (IEntryConverter<AccessControlEntry, To>) converter;
		}
		
		return super.getEntryConverter(cto);
	}
}
