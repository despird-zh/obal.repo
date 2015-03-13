package com.doccube.repo;

import com.doccube.core.security.Principal;

public class RepoManager {
	
	public static NodeHierarchy<ViewNode> getViewRepo(){
		return null;
				
	}
	
	public static NodeHierarchy<FolderNode> getPublicRepo(){
		return null;
		
		
	}
	
	public static NodeHierarchy<FolderNode> getPrivateRepo(Principal principal){
		return null;
		
		
	}
}
