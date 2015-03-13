package com.doccube.fs;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
/**
 * CubeFileSystemProvider support the calling from FileSystem 
 * All the access to repository trigers here.
 * 
 * @author despird-zh
 * @version 0.1 2015-2-3
 * 
 **/
public class CubeFileSystemProvider extends FileSystemProvider{

    private final Map<String, CubeFileSystem> filesystems = new HashMap<>();
	private String scheme = "dcube";
    private ThreadLocal<CubePrincipal> local = new ThreadLocal<CubePrincipal>();
    
	public CubeFileSystemProvider(){}
	
	@Override
	public void checkAccess(Path path, AccessMode... modes) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(Path source, Path target, CopyOption... options)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createDirectory(Path dir, FileAttribute<?>... attrs)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Path path) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <V extends FileAttributeView> V getFileAttributeView(Path path,
			Class<V> type, LinkOption... options) {
		System.out.println("calling attribute view:" + type.getName());
		if(type == CubeAclFileAttributeView.class){
			CubeAclFileAttributeView rtv = new CubeAclFileAttributeView();
			return (V) rtv;
		}
		
		return null;
	}

	@Override
	public FileStore getFileStore(Path path) throws IOException {
		
		return null;
	}

	@Override
	public FileSystem getFileSystem(URI uri) {
		
        String repo = uri.getHost();
        if (repo == null) {
            throw new IllegalArgumentException("URI reposition '" + repo + "' not specified.");
        }
        
        synchronized (filesystems) {
            CubeFileSystem ubfs = filesystems.get(repo);
 
            if (ubfs == null)
                throw new FileSystemNotFoundException();
            return ubfs;
        }
	}

	@Override
	public Path getPath(URI uri) {
        String path = uri.getPath();

        if (path == null)
            throw new IllegalArgumentException("URI: "
                + uri
                + " does not contain path info");
        
        return getFileSystem(uri).getPath(path);
	}

	@Override
	public String getScheme() {
		
		return this.scheme;
	}

	@Override
	public boolean isHidden(Path path) throws IOException {
		
		return false;
	}

	@Override
	public boolean isSameFile(Path path, Path path2) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move(Path source, Path target, CopyOption... options)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SeekableByteChannel newByteChannel(Path path,
			Set<? extends OpenOption> options, FileAttribute<?>... attrs)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectoryStream<Path> newDirectoryStream(Path dir,
			Filter<? super Path> filter) throws IOException {
		if(dir instanceof CubePath)
			return new CubeDirectoryStream((CubePath)dir, filter);
		
		return null;
	}

	/**
	 * Utilize the host part of URI string to identify the repository name
	 *  
	 **/
	@Override
	public FileSystem newFileSystem(URI uri, Map<String, ?> env){
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equalsIgnoreCase(getScheme())) {
            throw new IllegalArgumentException("URI scheme is not '" + getScheme() + "'");
        }
        String repo = uri.getHost();
        if (repo == null) {
            throw new IllegalArgumentException("URI reposition '" + repo + "' not specified.");
        }
        
        synchronized(filesystems) {
            if (filesystems.containsKey(repo))
                    throw new FileSystemAlreadyExistsException();
            CubeFileSystem ubfs = null;
            try {
            	ubfs = new CubeFileSystem(this, repo, env);
            } catch (Exception ze) {
                
                throw new UnsupportedOperationException();
            }
            filesystems.put(repo, ubfs);
            return ubfs;
        }
	}

    /**
	 * Ignore this function
	 */
//	@Override
//	public FileSystem newFileSystem(Path path, Map<String, ?> env)
//			throws IOException {
//	}
	
	@Override
	public <A extends BasicFileAttributes> A readAttributes(Path path,
			Class<A> type, LinkOption... options) throws IOException {
        if (type == BasicFileAttributes.class || type == CubeBasicFileAttributes.class){
            CubePath upath = toUbPath(path);
        	CubeRepoAdapter adapter = new CubeRepoAdapter(upath.getFileSystem());
        	return (A)adapter.getAttributes(upath.getResolvedPath());
        }
        return null;
	}

	@Override
	public Map<String, Object> readAttributes(Path path, String attributes,
			LinkOption... options) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Path path, String attribute, Object value,
			LinkOption... options) throws IOException {
		
	}

	/**
	 * Parse the UserPrincipal from URI String
	 **/
	private CubePrincipal parsePrincipal(URI uri){
		Objects.requireNonNull(uri);
		String userinfo = uri.getUserInfo();
		
		String[] strarray = userinfo.split(":");
		
		if(strarray.length < 2){
			
			throw new IllegalArgumentException("missing user account or password..");
		}
		
		return new CubePrincipal(strarray[0],strarray[1]);
	}
	//////////////////////////////////////////////
    // Checks that the given file is a UnixPath
    static final CubePath toUbPath(Path path) {
        if (path == null)
            throw new NullPointerException();
        if (!(path instanceof CubePath))
            throw new ProviderMismatchException();
        return (CubePath)path;
    }
	
    /**
     * Set Principal to ThreadLocal variable 
     **/
    protected void setPrincipal(CubePrincipal principal){
    	local.set(principal);
    }
    
    /**
     * Set principal as ThreadLocal variable 
     **/
    protected CubePrincipal getPrincipal(){
    	return local.get();
    }
    
    /**
     * Remove the principal from ThreadLocal variable 
     **/
    protected void removePrincipal(){
    	
    	local.remove();
    }
}
