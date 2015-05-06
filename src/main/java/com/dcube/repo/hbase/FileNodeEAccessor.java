package com.dcube.repo.hbase;

import com.dcube.core.IEntryConverter;
import com.dcube.core.accessor.AccessControlEntry;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.hbase.HAccessControlAccessor;
import com.dcube.exception.BaseException;
import com.dcube.repo.FileNode;
import com.dcube.repo.RepoConstants;

public class FileNodeEAccessor extends HAccessControlAccessor<AccessControlEntry>{

	public FileNodeEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FILE);
	}
	
	public FileNodeEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FILE,context);
	}

	@Override
	public AccessControlEntry newEntryObject() {
		
		return new AccessControlEntry();
	}

	@SuppressWarnings("unchecked")
	@Override 
	public <To> IEntryConverter<AccessControlEntry, To> getEntryConverter(Class<To> cto){
		
		if(cto.equals(FileNode.class)){
			
			IEntryConverter<AccessControlEntry,FileNode> converter = new IEntryConverter<AccessControlEntry,FileNode>(){

				@Override
				public FileNode toTarget(AccessControlEntry fromObject)
						throws BaseException {

					FileNode fnode = new FileNode(fromObject);
					return fnode;
				}

				@Override
				public AccessControlEntry toSource(FileNode toObject)
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
