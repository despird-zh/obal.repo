package com.doccube.fs;

import java.io.IOException;
import java.nio.file.ClosedDirectoryStreamException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipPath;

public class CubeDirectoryStream implements DirectoryStream<Path>{

    private final CubeFileSystem ubfs;
    private final DirectoryStream.Filter<? super Path> filter;
    private volatile boolean isClosed;
    private volatile Iterator<Path> itr;
    private byte[] path ;
	public CubeDirectoryStream(CubePath ubPath,
            DirectoryStream.Filter<? super java.nio.file.Path> filter)
            		throws IOException{
		this.path = ubPath.getResolvedPath();
        this.ubfs = ubPath.getFileSystem();
        this.filter = filter;
        // sanity check        
        if (!Files.isDirectory(ubPath))
            throw new NotDirectoryException(ubPath.toString());
	}
	
	@Override
	public void close() throws IOException {
		isClosed = true;
	}

	@Override
	public Iterator<Path> iterator() {
        if (isClosed)
            throw new ClosedDirectoryStreamException();
        if (itr != null)
            throw new IllegalStateException("Iterator has already been returned");
        // use adapter to retrieve the children of folder
        itr = new CubeRepoAdapter(ubfs).getChilds(path).iterator();
        return new Iterator<Path>() {

            @Override
            public boolean hasNext() {
                if (isClosed)
                    return false;
                return itr.hasNext();
            }

            @Override
            public synchronized Path next() {
                if (isClosed)
                    throw new NoSuchElementException();
                return itr.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
	}

}
