package com.dcube.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.helpers.MessageFormatter;

import com.dcube.exception.BaseException;
import com.dcube.exception.EntityException;


public class RepoException  extends BaseException{

	private static final long serialVersionUID = 1L;
	
	private static Properties repo_exceps = null;
	
	static{
		
		Class<?> selfclazz = EntityException.class;
		String fullname = selfclazz.getName();
		fullname = fullname.replace('.', '/');
		fullname = fullname + ".properties";
		InputStream is = selfclazz.getClassLoader().getResourceAsStream(fullname);
		repo_exceps = new Properties();
		try {
			repo_exceps.load(is);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public RepoException(String errorcode, String... param) {
		super(errorcode, param);
		this.message = findMessage(errorcode, param);
	}
	
    public RepoException(String errorcode, Throwable cause,String ...param) {
        super(errorcode, cause);
        this.message = findMessage(errorcode, param);
    }
    
    public RepoException(Throwable cause) {
        super(cause);
    }
    
    @Override
	protected String findMessage(String errorcode,String ... param){
		
		String messagePattern = repo_exceps.getProperty(errorcode, errorcode);
		if(errorcode.equals(messagePattern)){
			return super.findMessage(errorcode, param);
		}
		return MessageFormatter.arrayFormat(messagePattern, param).getMessage();
	}

}
