package com.dcube.repo.accessor;

import java.util.List;

import com.dcube.exception.RepoException;
import com.dcube.repo.FileNode;
import com.dcube.repo.FileTag;
import com.dcube.repo.FileContent;
import com.dcube.repo.FolderNode;
public interface IRepoNodeGAccessor {

	/**
	 * Get primary content of file node.
	 * @param fileId 
	 **/
	public FileContent getPrimaryContent(String fileId) throws RepoException;
	
	/**
	 * Get the file tags of specified file 
	 **/
	public List<FileTag> getFileTags(String fileId) throws RepoException;
	
	/**
	 * Get the file nodes of specified file group 
	 **/
	public List<FileNode> getFileNodesByGroup(String groupFileId) throws RepoException;

	/**
	 * Get the md5 of file content 
	 **/
	public String getFileContentMd5(String fileId) throws RepoException;
	
	/**
	 * Get the file nodes under specified folder 
	 **/
	public List<FileNode> getFileNodesByFolder(String folderId) throws RepoException;

	/**
	 * Get the parent node of file node. 
	 **/
	public FolderNode getParentNode(String nodeId)throws RepoException;
	
	public FolderNode getParentNode(String repoView, String nodeId)throws RepoException;
}
