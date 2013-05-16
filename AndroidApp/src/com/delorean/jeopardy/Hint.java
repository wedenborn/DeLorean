package com.delorean.jeopardy;

import android.os.Parcel;
import android.os.Parcelable;

public class Hint implements Parcelable {
	public String hint;
	
	public Hint(String hint) {
		this.hint = hint;
	}
	
	public Hint(Parcel in) {
		hint = in.readString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hint [Hint=" + hint + "]";
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(hint);
	}

	public static final Parcelable.Creator<Hint> CREATOR = new Parcelable.Creator<Hint>() {
		public Hint createFromParcel(Parcel in) {
			return new Hint(in);
		}

		public Hint[] newArray(int size) {
			return new Hint[size];
		}
	};
	
} // End Hint class
