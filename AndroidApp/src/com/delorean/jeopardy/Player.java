package com.delorean.jeopardy;

public class Player {
	public String userName;
	public int id;
	public Score hiScore;
	
	
	public Player(String username, int ID, Score HiScore) {
		this.userName = username;
		this.id = ID;
		this.hiScore = HiScore;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [UserName=" + userName + ", ID=" + id + ", HiScore="
				+ hiScore + "]";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Score getHiScore() {
		return hiScore;
	}

	public void setHiScore(Score hiScore) {
		this.hiScore = hiScore;
	}

	public Player(String username, int ID, int numberOfHintsUsed, int numberOfCorrectAnswers, double score) {
		this.userName = username;
		this.id = ID;
		this.hiScore.setNumberOfCorrectAnswers(numberOfCorrectAnswers);
		this.hiScore.setNumberOfHintsUsed(numberOfHintsUsed);
		this.hiScore.setScore(score);
	}
	
	
	
	
	
	
	
	
	
	
} // End Player Class
