package com.delorean.jeopardy;

public class Score {

	public int numberOfHintsUsed;
	public int numberOfCorrectAnswers;
	public double score;

	public Score () {
		this.numberOfHintsUsed = 0;
		this.numberOfCorrectAnswers = 0;
		this.score = 0;
	}

	public Score (int numberOfHintsUsed, int numberOfCorrectAnswers, double score) {
		this.numberOfHintsUsed = numberOfHintsUsed;
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
		this.score = score;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Score [numberOfHintsUsed=" + numberOfHintsUsed
				+ ", numberOfCorrectAnswers=" + numberOfCorrectAnswers
				+ ", score=" + score + ", average()=" + average() + "]";
	}

	public double average() {
		double avg;
		avg = numberOfHintsUsed / (double) numberOfCorrectAnswers;
		return avg;
	}

	public int getNumberOfHintsUsed() {
		return numberOfHintsUsed;
	}

	public void setNumberOfHintsUsed(int numberOfHintsUsed) {
		this.numberOfHintsUsed = numberOfHintsUsed;
	}

	public int getNumberOfCorrectAnswers() {
		return numberOfCorrectAnswers;
	}

	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

} // END Score class
