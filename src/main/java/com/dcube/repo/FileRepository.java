package com.dcube.repo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.dcube.core.CoreConstants;
import com.dcube.core.accessor.EntryParser;
import com.dcube.core.accessor.TraceableEntry;
import com.dcube.repo.RepoConstants.RepoViewEnum;

public class FileRepository extends EntryParser{

	/**
	 * Default build the primary repository
	 **/
	public FileRepository(TraceableEntry rawEntry){
		
		super(rawEntry);
	}
	
	/**
	 * Build view repository 
	 **/
	public FileRepository(String name){
		
		super(new TraceableEntry(RepoConstants.ENTITY_REPOVIEW,null));
	}
	
	public boolean isPrimary(){
		
		return getAttrValue(RepoViewEnum.IsPrimary.attribute,Boolean.class);
	}

	public String getRepoName(){
		
		return getAttrValue(RepoViewEnum.Name.attribute,String.class);
	}
	
	public void setRepoName(String name){
		
		setAttrValue(RepoViewEnum.Name.attribute, name);
	}
	
	public String getDescription(){
		
		return getAttrValue(RepoViewEnum.Description.attribute,String.class);
	}
	
	public void setDescription(String descr){
		
		setAttrValue(RepoViewEnum.Description.attribute, descr);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getProfile(){
		
		return getAttrValue(RepoViewEnum.Profile.attribute,Map.class);
	}
	
	public void setProfile(String key, String value){
		
		@SuppressWarnings("unchecked")
		Map<String,String> profile = getAttrValue(RepoViewEnum.Profile.attribute,Map.class);
		if(profile == null)
			profile = new HashMap<String,String>();
		
		profile.put(key, value);
		
		setAttrValue(RepoViewEnum.Profile.attribute, profile);
	}
	
	public Set<String> getPresentAttrs(String entityname){
		
		@SuppressWarnings("unchecked")
		Map<String,String> presentmap = getAttrValue(RepoViewEnum.Profile.attribute,Map.class);
		//StringUtils.join(list, ";");
		String arraystr = presentmap.get(entityname);
		List<String> rtv = Arrays.asList(StringUtils.split(arraystr, CoreConstants.COLLECT_ELM_SEPARATOR));
		
		return new HashSet<String>(rtv);
	}
	
	public void setPresentAttrs(String entityname, boolean merge, String ...attrs){
		
		@SuppressWarnings("unchecked")
		Map<String,String> presentmap = getAttrValue(RepoViewEnum.Profile.attribute,Map.class);
		String str = null;
		if(merge){
			String arraystr = presentmap.get(entityname);
			List<String> rtv = Arrays.asList(StringUtils.split(arraystr, CoreConstants.COLLECT_ELM_SEPARATOR));
			for(String elm:attrs)
				rtv.add(elm);
			
			str = StringUtils.join(rtv, CoreConstants.COLLECT_ELM_SEPARATOR);
			
		}else{
			str = StringUtils.join(Arrays.asList(attrs), CoreConstants.COLLECT_ELM_SEPARATOR);
			
		}
		presentmap.put(entityname, str);
		setAttrValue(RepoViewEnum.Profile.attribute, presentmap);
	}
}
