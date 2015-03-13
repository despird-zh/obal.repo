package com.doccube.fs;

import java.io.IOException;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

public class CubeUserPrincipalLookupService extends UserPrincipalLookupService{

	@Override
	public GroupPrincipal lookupPrincipalByGroupName(String group)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPrincipal lookupPrincipalByName(String name) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
