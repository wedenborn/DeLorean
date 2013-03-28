package com.delorean.jeopardy;

public class Hint {
	public String hint;
	
	public Hint(String hint) {
		this.hint = hint;
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
	
} // End Hint class
