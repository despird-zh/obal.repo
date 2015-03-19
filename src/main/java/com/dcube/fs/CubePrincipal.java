package com.dcube.fs;

import java.nio.file.attribute.UserPrincipal;

public class CubePrincipal implements UserPrincipal {

	String name ;
	String password;
	
	public CubePrincipal(String name, String password){
		
		this.name = name;
		this.password = password;
	}
	
	@Override
	public String getName() {
		
		return this.name;
	}

	public String getPassword(){
		
		return this.password;
	}
	
	@Override
	public String toString(){
		
		return "principal[name:" + name + " / password:******]";
	}
}
