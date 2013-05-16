package com.delorean.jeopardy;

import android.os.Parcel;
import android.os.Parcelable;

public class TMPuser implements Parcelable {
	public String username;
	public String password;
	public int id;
	
	public TMPuser(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public TMPuser(Parcel in) {
		username = in.readString();
		password = in.readString();
		id = in.readInt();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMPuser [username=" + username + ", password=" + password
				+ ", id=" + id + "]";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(username);
		out.writeString(password);
		out.writeInt(id);
	}

	public static final Parcelable.Creator<TMPuser> CREATOR = new Parcelable.Creator<TMPuser>() {
        public TMPuser createFromParcel(Parcel in) {
            return new TMPuser(in);
        }

        public TMPuser[] newArray(int size) {
            return new TMPuser[size];
        }
    };
	
	
}
