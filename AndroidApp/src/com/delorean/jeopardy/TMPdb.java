package com.delorean.jeopardy;

import java.util.List;

public class TMPdb {
	public List<TMPuser> users;
	
	public TMPdb() {
		addUser("albert","albert1");
		addUser("bertil","bertil1");
		addUser("carl","carl1");
	}
	
	
	public boolean addUser(String username, String password) {
		if(containsUser(username)) {
			return false;
		} else {
			users.add(new TMPuser(username,password,users.size()+10000));
			return true;
		}
		
		
	}
	
	public boolean containsUser(String username) {
		for(int i=0;i<users.size();i++){
			if(users.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
} // End TMPdb
