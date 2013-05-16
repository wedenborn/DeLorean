package com.delorean.jeopardy;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer  implements Parcelable {
	public String answer;

	public Answer(String answer) {
		this.answer = answer;
	}

	public Answer(Parcel in) {
		answer = in.readString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [answer=" + answer + "]";
	}


	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(answer);
	}

	public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
		public Answer createFromParcel(Parcel in) {
			return new Answer(in);
		}

		public Answer[] newArray(int size) {
			return new Answer[size];
		}
	};


} // End Answer Class
