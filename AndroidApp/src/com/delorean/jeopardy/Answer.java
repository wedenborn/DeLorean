package com.delorean.jeopardy;

public class Answer {
	public String answer;
	
	public Answer(String answer) {
		this.answer = answer;
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

	
	
	
} // End Answer Class
