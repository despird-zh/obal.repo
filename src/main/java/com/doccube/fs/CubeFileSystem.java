package com.doccube.fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CubeFileSystem extends FileSystem{

	private final CubeFileSystemProvider provider;
    // configurable by env map
    private final String  defaultDir;    // default dir for the file system
    private final CubePath defaultdir;
    private String cubename;
    private boolean isOpen = true;
    private boolean readOnly = false;
    public CubeFileSystem(CubeFileSystemProvider provider,
            String cubename,
            Map<String, ?> env) throws Exception{
		this.cubename = cubename;
        this.defaultDir   = (env != null && env.containsKey("default.dir")) ? (String)env.get("default.dir") : "/";
        if (this.defaultDir.charAt(0) != '/')
            throw new IllegalArgumentException("default dir should be absolute");

        this.provider = provider;
        
        this.defaultdir = new CubePath(this, defaultDir.getBytes());
	}
	
	@Override
	public void close() throws IOException {
		provider.removePrincipal();
	}

	@Override
	public Iterable<FileStore> getFileStores() {
		ArrayList<FileStore> list = new ArrayList<>(1);
        list.add(new CubeFileStore(new CubePath(this, new byte[]{'/'})));
        return list;
	}

	@Override
	public CubePath getPath(String first, String... more) {
		 String path;
	        if (more.length == 0) {
	            path = first;
	        } else {
	            StringBuilder sb = new StringBuilder();
	            sb.append(first);
	            for (String segment: more) {
	                if (segment.length() > 0) {
	                    if (sb.length() > 0)
	                        sb.append('/');
	                    sb.append(segment);
	                }
	            }
	            path = sb.toString();
	        }
	        return new CubePath(this, path.getBytes());
	}

    private static final String GLOB_SYNTAX = "glob";
    private static final String REGEX_SYNTAX = "regex";
    
	@Override
	public PathMatcher getPathMatcher(String syntaxAndPattern) {
        int pos = syntaxAndPattern.indexOf(':');
        if (pos <= 0 || pos == syntaxAndPattern.length()) {
            throw new IllegalArgumentException();
        }
        String syntax = syntaxAndPattern.substring(0, pos);
        String input = syntaxAndPattern.substring(pos + 1);
        String expr;
        if (syntax.equals(GLOB_SYNTAX)) {
            expr = toRegexPattern(input);
        } else {
            if (syntax.equals(REGEX_SYNTAX)) {
                expr = input;
            } else {
                throw new UnsupportedOperationException("Syntax '" + syntax +
                    "' not recognized");
            }
        }
        // return matcher
        final Pattern pattern = Pattern.compile(expr);
        return new PathMatcher() {
            @Override
            public boolean matches(Path path) {
            	System.out.print("path matcher testing : "+path);
            	Matcher m = pattern.matcher(path.toString());
            	System.out.println("result : "+m.matches());
                return m.matches();
            }
        };
	}

	@Override
	public Iterable<Path> getRootDirectories() {
		ArrayList<Path> pathArr = new ArrayList<>();
        pathArr.add(new CubePath(this, new byte[]{'/'}));
        return pathArr;
	}

	@Override
	public String getSeparator() {
		return "/";
	}

	@Override
	public UserPrincipalLookupService getUserPrincipalLookupService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		
		return isOpen;
	}

	@Override
	public boolean isReadOnly() {
		 return readOnly;
	}

	@Override
	public WatchService newWatchService() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileSystemProvider provider() {
		
		return provider;
	}

	@Override
	public Set<String> supportedFileAttributeViews() {

		return supportedFileAttributeViews;
	}

	/////////////////////
    private static final Set<String> supportedFileAttributeViews =
            Collections.unmodifiableSet(
                new HashSet<String>(Arrays.asList("basic", "acl")));
    
    private static final String regexMetaChars = ".^$+{[]|()";
    private static final String globMetaChars = "\\*?[{";
    private static boolean isRegexMeta(char c) {
        return regexMetaChars.indexOf(c) != -1;
    }
    private static boolean isGlobMeta(char c) {
        return globMetaChars.indexOf(c) != -1;
    }    
    
    private static char EOL = 0;  //TBD
    private static char next(String glob, int i) {
        if (i < glob.length()) {
            return glob.charAt(i);
        }
        return EOL;
    }

    /*
     * Creates a regex pattern from the given glob expression.
     *
     * @throws  PatternSyntaxException
     */
    public static String toRegexPattern(String globPattern) {
        boolean inGroup = false;
        StringBuilder regex = new StringBuilder("^");

        int i = 0;
        while (i < globPattern.length()) {
            char c = globPattern.charAt(i++);
            switch (c) {
                case '\\':
                    // escape special characters
                    if (i == globPattern.length()) {
                        throw new PatternSyntaxException("No character to escape",
                                globPattern, i - 1);
                    }
                    char next = globPattern.charAt(i++);
                    if (isGlobMeta(next) || isRegexMeta(next)) {
                        regex.append('\\');
                    }
                    regex.append(next);
                    break;
                case '/':
                    regex.append(c);
                    break;
                case '[':
                    // don't match name separator in class
                    regex.append("[[^/]&&[");
                    if (next(globPattern, i) == '^') {
                        // escape the regex negation char if it appears
                        regex.append("\\^");
                        i++;
                    } else {
                        // negation
                        if (next(globPattern, i) == '!') {
                            regex.append('^');
                            i++;
                        }
                        // hyphen allowed at start
                        if (next(globPattern, i) == '-') {
                            regex.append('-');
                            i++;
                        }
                    }
                    boolean hasRangeStart = false;
                    char last = 0;
                    while (i < globPattern.length()) {
                        c = globPattern.charAt(i++);
                        if (c == ']') {
                            break;
                        }
                        if (c == '/') {
                            throw new PatternSyntaxException("Explicit 'name separator' in class",
                                    globPattern, i - 1);
                        }
                        // TBD: how to specify ']' in a class?
                        if (c == '\\' || c == '[' ||
                                c == '&' && next(globPattern, i) == '&') {
                            // escape '\', '[' or "&&" for regex class
                            regex.append('\\');
                        }
                        regex.append(c);

                        if (c == '-') {
                            if (!hasRangeStart) {
                                throw new PatternSyntaxException("Invalid range",
                                        globPattern, i - 1);
                            }
                            if ((c = next(globPattern, i++)) == EOL || c == ']') {
                                break;
                            }
                            if (c < last) {
                                throw new PatternSyntaxException("Invalid range",
                                        globPattern, i - 3);
                            }
                            regex.append(c);
                            hasRangeStart = false;
                        } else {
                            hasRangeStart = true;
                            last = c;
                        }
                    }
                    if (c != ']') {
                        throw new PatternSyntaxException("Missing ']", globPattern, i - 1);
                    }
                    regex.append("]]");
                    break;
                case '{':
                    if (inGroup) {
                        throw new PatternSyntaxException("Cannot nest groups",
                                globPattern, i - 1);
                    }
                    regex.append("(?:(?:");
                    inGroup = true;
                    break;
                case '}':
                    if (inGroup) {
                        regex.append("))");
                        inGroup = false;
                    } else {
                        regex.append('}');
                    }
                    break;
                case ',':
                    if (inGroup) {
                        regex.append(")|(?:");
                    } else {
                        regex.append(',');
                    }
                    break;
                case '*':
                    if (next(globPattern, i) == '*') {
                        // crosses directory boundaries
                        regex.append(".*");
                        i++;
                    } else {
                        // within directory boundary
                        regex.append("[^/]*");
                    }
                    break;
                case '?':
                   regex.append("[^/]");
                   break;
                default:
                    if (isRegexMeta(c)) {
                        regex.append('\\');
                    }
                    regex.append(c);
            }
        }
        if (inGroup) {
            throw new PatternSyntaxException("Missing '}", globPattern, i - 1);
        }
        return regex.append('$').toString();
    }

}
