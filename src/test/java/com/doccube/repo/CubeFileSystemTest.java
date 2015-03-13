package com.doccube.repo;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import com.doccube.fs.CubeAclFileAttributeView;
import com.doccube.fs.CubeFileAttributeView;

public class CubeFileSystemTest {
	
	public static void main(String[] args) throws Throwable {
				
		URI uri = new URI("dcube://usr1:pw1@repo1/demo1/dmeo2/../demo3/ss");
		FileSystems.newFileSystem(uri, null);
		//testURI(uri);
		Path upath = Paths.get(uri);
		String fn = upath.getFileName().toString();
		for(int i = 0; i< upath.getNameCount(); i++){
			String n = upath.getName(i).toString();
			System.out.println("index:" + i +" / name:" + n);
		}
		Path ap = upath.toRealPath();
		System.out.println("--- real path : " + ap);
		
		fn = upath.getParent().toString();
		System.out.println(fn);
		fn = upath.getRoot().toString();
		System.out.println(fn);

		CubeAclFileAttributeView uafa = Files.getFileAttributeView(upath, CubeAclFileAttributeView.class);
		uafa.setOwner(new UserPrincipal(){

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "demouser";
			}
			
		});
		CubeFileAttributeView ufa = Files.getFileAttributeView(upath, CubeFileAttributeView.class);
		Path upath1 = Paths.get(uri);
		testFind(upath1);
	}
	
	/**
	 * ubrepo://usr1:pw1@repo1/demo1/dmeo2
	 * Authority = usr1:pw1@repo1
		Fragment = null
		Host = repo1
		Path = /demo1/dmeo2
		Port = -1
		Query = null
		Scheme = ubrepo
		Scheme-specific part = //usr1:pw1@repo1/demo1/dmeo2
		User Info = usr1:pw1
		URI is absolute: true
		URI is opaque: false
	 **/
	public static void testURI(URI uri){
		
	   System.out.println ("Authority = " +  uri.getAuthority ());
	   System.out.println ("Fragment = " + uri.getFragment ());
	   System.out.println ("Host = " + uri.getHost ());
	   System.out.println ("Path = " + uri.getPath ());
	   System.out.println ("Port = " + uri.getPort ());
	   System.out.println ("Query = " + uri.getQuery ());
	   System.out.println ("Scheme = " + uri.getScheme ());
	   System.out.println ("Scheme-specific part = " + uri.getSchemeSpecificPart ());
	   System.out.println ("User Info = " + uri.getUserInfo ());
	   System.out.println ("URI is absolute: " + uri.isAbsolute ());
	   System.out.println ("URI is opaque: " + uri.isOpaque ());
	}
	
	public static void testFind(Path path) throws IOException{
		
		Path startDir = path.getRoot();
		String pattern = "*.{txt,doc}";

		FileSystem fs = path.getFileSystem();
		final PathMatcher matcher = fs.getPathMatcher("glob:" + pattern);

		FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
		    @Override
		    public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
		        Path name = file.getFileName();
		        if (matcher.matches(name)) {
		            System.out.print(String.format("Found matched file: '%s'.%n", file));
		        }
		        return FileVisitResult.CONTINUE;
		    }
		};
		Files.walkFileTree(startDir, matcherVisitor);
		//Files.walkFileTree(startDir, EnumSet.noneOf(FileVisitOption.class), 4, matcherVisitor);
	}
}
