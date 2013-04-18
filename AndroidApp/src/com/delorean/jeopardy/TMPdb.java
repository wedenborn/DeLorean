package com.delorean.jeopardy;

import java.util.LinkedList;
import java.util.List;

public class TMPdb {
	public List<TMPuser> users;
	public List<Question> questions;

	public TMPdb() {
		users = new LinkedList<TMPuser>();
		addUser("adam","adam1");
		addUser("bertil","bertil1");
		addUser("carl","carl1");

		questions = new LinkedList<Question>();
		populateQustions();
	}


	public boolean addUser(String username, String password) {
		if(containsUser(username)) {
			return false;
		} else {
			users.add(new TMPuser(username,password,users.size()+10000));
			return true;
		}


	}

	public boolean containsUser(String username) {
		for(int i=0;i<users.size();i++){
			if(users.get(i).getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkLogin(String username, String password) {
		for(int i=0;i<users.size();i++){
			if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public Question getQuestion(int id) {
		return questions.get(id);
	}

	public void populateQustions () {
		Question q1 = new Question();
		q1.setQuestion("He is an Austrian and American former professional bodybuilder, actor, producer, director, businessman, investor, and politician.");
		q1.setCorrectAnswer(new Answer("Arnold Schwarzenegger"));
		Answer[] q1answers = {new Answer("Gray Davis"),  new Answer("Kirk Douglas"), new Answer("Danny DeVito")};
		q1.setIncorrectAnswers(q1answers);
		q1.setId(1);
		q1.addHint("He served two terms as the 38th Governor of California from 2003 until 2011.");
		q1.addHint("For many years, he wrote a monthly column for the bodybuilding magazines Muscle & Fitness and Flex.");
		q1.addHint("His breakthrough film was the sword-and-sorcery epic Conan the Barbarian in 1982.");
		questions.add(q1);

		Question q2 = new Question();
		q2.setQuestion("She is best known for her self-titled, multi-award-winning talk show.");
		q2.setCorrectAnswer(new Answer("Oprah Winfrey"));
		Answer[] q2answers = {new Answer("Sarah Palin"),  new Answer("Suzanne Somers"), new Answer("Halle Berry")};
		q2.setIncorrectAnswers(q2answers);
		q2.setId(2);
		q2.addHint("In 1985, she co-starred in Steven Spielberg's The Color Purple as distraught housewife, Sofia.");
		q2.addHint("In 2006, rappers Ludacris, 50 Cent and Ice Cube criticized her for what they perceived as an anti-hip hop bias.");
		q2.addHint("She has co-authored five books.");
		questions.add(q2);

		Question q3 = new Question();
		q3.setQuestion("He has been nominated for three Academy Awards and has won three Golden Globe Awards.");
		q3.setCorrectAnswer(new Answer("Tom Cruise"));
		Answer[] q3answers = {new Answer("Paul Newman"),  new Answer("Dustin Hoffman"), new Answer("Ben Stiller")};
		q3.setIncorrectAnswers(q3answers);
		q3.setId(3);
		q3.addHint("He was born in Syracuse, New York, the son of Mary Lee.");
		q3.addHint("He was included in The Beast's publication of their 50 Most Loathsome People of 2004.");
		q3.addHint("He is known for his Scientologist faith and for his support of the Church of Scientology.");
		questions.add(q3);
	}
	
	public int numberOfQuestions() {
		return questions.size();
	}

} // End TMPdb
