package com.delorean.jeopardy;

import java.util.Arrays;
import java.util.List;

public class Question {
	public List<Hint> hints;
	public Answer correctAnswer;
	public Answer[] incorrectAnswers;
	public int id;
	public String question;

	public Question(List<Hint> hints, Answer correctAnswer,
			Answer[] incorrectAnswers, int id, String question) {

		this.hints = hints;
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = new Answer[3];
		this.incorrectAnswers = incorrectAnswers;
		this.id = id;
		this.question = question;
	}

	public Question() {
		this.incorrectAnswers = new Answer[3];
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [hints=" + hints + ", correctAnswer=" + correctAnswer
				+ ", incorrectAnswers=" + Arrays.toString(incorrectAnswers)
				+ ", id=" + id + ", question=" + question + "]";
	}

	/**
	 * Request additional hints from the database
	 */
	public void fetchExtraHints() {
		//TODO
	}

	/**
	 * Removes and returns the first hint
	 * @return the first hint
	 */
	public Hint popHint() {
		if (hasHints()) {
			return hints.remove(0);
		} else {
			return null;
		}
	}

	/**
	 * @return true if there are additional hints left.
	 */
	public boolean hasHints() {
		return !hints.isEmpty();
	}

	/**
	 * @return the hints
	 */
	public List<Hint> getHints() {
		return hints;
	}

	/**
	 * @param hints the hints to set
	 */
	public void setHints(List<Hint> hints) {
		this.hints = hints;
	}

	/**
	 * @return the correctAnswer
	 */
	public Answer getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(Answer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the incorrectAnswers
	 */
	public Answer[] getIncorrectAnswers() {
		return incorrectAnswers;
	}

	/**
	 * If the length of @param is greater than 3, it only sets the first 3 elements
	 * 
	 * @param incorrectAnswers the incorrectAnswers to set
	 */
	public void setIncorrectAnswers(Answer[] incorrectAnswers) {
		if (incorrectAnswers.length > 3) { 
			for(int i=0;i<3;i++) {
				this.incorrectAnswers[i] = incorrectAnswers[i];
			}
		} else {
			this.incorrectAnswers = incorrectAnswers;
		}
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

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}







} // End Question class
