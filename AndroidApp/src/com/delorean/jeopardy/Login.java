package com.delorean.jeopardy;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class Login {
	public String userName;
	public String password;
	public TMPdb db;

	public Login(String userName, String password, Context context) {
		this.userName = userName;
		this.password = password;
	}

	public Login(AssetManager as) {
		this.db = new TMPdb(as);

	}
	/*
	public void createDB (Context context) {
		this.db = new TMPdb(context);
	}
*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + "]";
	}

	/**
	 * @param username Username to be registered
	 * @param password Password to be registered
	 * @return true if the username was available
	 */
	public boolean registerAccount(String username, String password) {
		return db.addUser(username, password);
	}

	
	
	/**
	 * To be expanded later
	 */
	public int hashPassword() {
		return password.hashCode();
	}
	
	public boolean negotiateCredentials() {
		Log.d(HomeActivity.LOG_TAG, "loginU: " + userName);
		Log.d(HomeActivity.LOG_TAG, "loginP: " + password);
		return db.checkLogin(userName, password);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

} // End Login Class
