package com.dcube.repo.hbase;

import com.dcube.core.IEntryConverter;
import com.dcube.core.accessor.AccessorContext;
import com.dcube.core.accessor.TraceableEntry;
import com.dcube.core.hbase.HEntityAccessor;
import com.dcube.exception.BaseException;
import com.dcube.repo.FileRepository;
import com.dcube.repo.RepoConstants;

/**
 * RepoViewEAccessor be used to access the hierarchy data of different repository view
 **/
public class RepoViewEAccessor extends HEntityAccessor<TraceableEntry>{

	public RepoViewEAccessor() {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW);
	}
	
	public RepoViewEAccessor(AccessorContext context) {
		super(RepoConstants.ACCESSOR_ENTITY_REPOVIEW,context);
	}

	@Override
	public TraceableEntry newEntryObject() {
		
		return new TraceableEntry();
	}

	@SuppressWarnings("unchecked")
	@Override 
	public <To> IEntryConverter<TraceableEntry, To> getEntryConverter(Class<To> cto){
		
		if(cto.equals(FileRepository.class)){
			
			IEntryConverter<TraceableEntry,FileRepository> converter = new IEntryConverter<TraceableEntry,FileRepository>(){

				@Override
				public FileRepository toTarget(TraceableEntry fromObject)
						throws BaseException {

					FileRepository fnode = new FileRepository(fromObject);
					return fnode;
				}

				@Override
				public TraceableEntry toSource(FileRepository toObject)
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
