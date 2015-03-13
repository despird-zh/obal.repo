package com.doccube.fs;

import java.io.IOException;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
public class CubeAclFileAttributeView implements AclFileAttributeView {

	private String name = "ubacl";
	
	@Override
	public UserPrincipal getOwner() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwner(UserPrincipal arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AclEntry> getAcl() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public void setAcl(List<AclEntry> acl) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
