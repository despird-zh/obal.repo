package com.dcube.repo.hbase;

import com.dcube.core.IEntryConverter;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.TraceableEntry;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.exception.BaseException;
import com.dcube.repo.FileNode;
import com.dcube.repo.FileTag;
import com.dcube.repo.RepoConstants;

public class FileTagEAccessor extends HEntityAccessor<TraceableEntry>{

	public FileTagEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_FILE);
	}
	
	public FileTagEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_FILE,context);
	}

	@Override
	public TraceableEntry newEntryObject() {
		
		return new TraceableEntry();
	}

	@SuppressWarnings("unchecked")
	@Override 
	public <To> IEntryConverter<TraceableEntry, To> getEntryConverter(Class<To> cto){
		
		if(cto.equals(FileNode.class)){
			
			IEntryConverter<TraceableEntry,FileTag> converter = new IEntryConverter<TraceableEntry,FileTag>(){

				@Override
				public FileTag toTarget(TraceableEntry fromObject)
						throws BaseException {

					FileTag fnode = new FileTag(fromObject);
					return fnode;
				}

				@Override
				public TraceableEntry toSource(FileTag toObject)
						throws BaseException {
					
					TraceableEntry entry = (TraceableEntry)toObject.getGenericEntry();
					return entry;
				}
				
			};
			
			return (IEntryConverter<TraceableEntry, To>) converter;
		}
		
		return super.getEntryConverter(cto);
	}
	
}
