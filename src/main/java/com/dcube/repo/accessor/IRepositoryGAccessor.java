package com.dcube.repo.accessor;

import java.util.List;

import com.dcube.exception.RepoException;
import com.dcube.repo.FileTag;
import com.dcube.repo.IRepoNode;

/**
 * Focus on repository content maintenance operation
 * 
 * */
public interface IRepositoryGAccessor {

	public IRepoNode getRepoNodeByPath(String repoName, String path)throws RepoException;
	
	public IRepoNode getRepoNodeById(String repoName, String fileId)throws RepoException;
	
	public void moveRepoNode(String source, String target)throws RepoException;
	
	public String createFileNode(String parentPath, String fileName) throws RepoException;
	
	public String createFolderNode(String parentPath, String fileName) throws RepoException;
	
	public void deleteFileNode(String ...fileId) throws RepoException;
	
	public void deleteFolderNode(String folderId, boolean force) throws RepoException;
	
	
}
