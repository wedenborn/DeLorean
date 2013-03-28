package com.delorean.jeopardy;

public class Score {
	
	public int numberOfHintsUsed;
	public int numberOfCorrectAnswes;
	public double score;
	
	public Score () {
		numberOfHintsUsed = 0;
		numberOfCorrectAnswes = 0;
		score = 0;
	}
	
	public Score (int numberOfHintsUsed, int numberOfCorrectAnswers, double score) {
		this.numberOfHintsUsed = numberOfHintsUsed;
		this.numberOfCorrectAnswes = numberOfCorrectAnswers;
		this.score = score;
	}
	
	public double average() {
		double avg;
		avg = numberOfHintsUsed / (double) numberOfCorrectAnswes;
		return avg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
} // END Score class
