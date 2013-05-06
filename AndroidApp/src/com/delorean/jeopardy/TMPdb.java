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
		
		Question q4 = new Question();
		q4.setQuestion("This person's religious zeal grew until he felt he had found his true vocation.");
		q4.setCorrectAnswer(new Answer("Vincent Van Gogh"));
		Answer[] q4answers = {new Answer("Jesus"),  new Answer("Shief Al Kalif"), new Answer("Abraham")};
		q4.setIncorrectAnswers(q4answers);
		q4.setId(4);
		q4.addHint("He began to draw as a child.");
		q4.addHint("This person spent his early adulthood working for a firm of art dealers.");
		q4.addHint("The extent to which his mental health affected his painting has been a subject of speculation since his death..");
		questions.add(q4);
		
		Question q5 = new Question();
		q5.setQuestion("Eroica is a 1949 Austrian film depicting life and works of this person.");
		q5.setCorrectAnswer(new Answer("Ludwig van Beethoven"));
		Answer[] q5answers = {new Answer("Adolf Hitler"),  new Answer("Leonardo Da Vinci"), new Answer("Jesus")};
		q5.setIncorrectAnswers(q5answers);
		q5.setId(5);
		q5.addHint("He had some hair.");
		q5.addHint("A crucial figure in the transition between the Classical and Romantic eras in Western art music.");
		q5.addHint("A German composer and pianist.");
		questions.add(q5);
		
		Question q6 = new Question();
		q6.setQuestion("For one year this person and his men remained stranded on Jamaica.");
		q6.setCorrectAnswer(new Answer("Christopher Columbus"));
		Answer[] q6answers = {new Answer("Paul Newman"),  new Answer("50 cent"), new Answer("Jesus")};
		q6.setIncorrectAnswers(q6answers);
		q6.setId(6);
		q6.addHint("He is from Italy.");
		q6.addHint("He was an explorer.");
		q6.addHint("He died 54 years old.");
		questions.add(q6);
		
		Question q7 = new Question();
		q7.setQuestion("The star is located at the pedestrian entrance to the this personland Resort on Harbor Boulevard.");
		q7.setCorrectAnswer(new Answer("Walt Disney"));
		Answer[] q7answers = {new Answer("Jesus"),  new Answer("Dustin Hoffman"), new Answer("J.K. Rowling")};
		q7.setIncorrectAnswers(q7answers);
		q7.setId(7);
		q7.addHint("In 1995, this person Pictures distributed Pixar's Toy Story, the first computer animated feature film.");
		q7.addHint("The this person studio expanded and Walt re-hired Harman, Rudolph Ising, Carman Maxwell, and Friz Freleng from Kansas City.");
		q7.addHint("This person has one of the world's first theme parks, which opened on July 17, 1955, and was immediately successful.");
		questions.add(q7);
		
		Question q8 = new Question();
		q8.setQuestion("Most of this person's greatest post-1599 plays were written for the Globe, including Hamlet, Othello and King Lear.");
		q8.setCorrectAnswer(new Answer("William Shakespeare"));
		Answer[] q8answers = {new Answer("Paul Newman"),  new Answer("Dustin Hoffman"), new Answer("Ben Stiller")};
		q8.setIncorrectAnswers(q8answers);
		q8.setId(8);
		q8.addHint("The actors in this person's company included the famous Richard Burbage, William Kempe, Henry Condell and John Heminges.");
		q8.addHint("It is not clear for which companies this person wrote his early plays.");
		q8.addHint("At the age of 18, this person married the 26-year-old Anne Hathaway.");
		questions.add(q8);
		
		Question q9 = new Question();
		q9.setQuestion("Botstein notes that music assumed a pivotal and permanent role in this person's life from that period on.");
		q9.setCorrectAnswer(new Answer("Albert Einstein"));
		Answer[] q9answers = {new Answer("Paul Newman"),  new Answer("Isac Newton"), new Answer("Leonard Hofstadter")};
		q9.setIncorrectAnswers(q9answers);
		q9.setId(9);
		q9.addHint("In 1916, this person was appointed president of the German Physical Society.");
		q9.addHint("His mother's name was Pauline.");
		q9.addHint("He is known for relativity and stuff.");
		questions.add(q9);
		
		Question q10 = new Question();
		q10.setQuestion("With almost no support in the South, this person swept the North and was elected president in 1860.");
		q10.setCorrectAnswer(new Answer("Abraham Lincoln"));
		Answer[] q10answers = {new Answer("George Bush"),  new Answer("John F. Kennedy"), new Answer("Lyndon B. Johnson")};
		q10.setIncorrectAnswers(q10answers);
		q10.setId(10);
		q10.addHint("This person also presided over the expansion of the federal government's economic influence in several other areas.");
		q10.addHint("Responding to criticism of Grant after Shiloh, this person had said, \"I can't spare this man\"");
		q10.addHint("Troops headed south towards Washington to protect the capital in response to this person's call.");
		questions.add(q10);
	}
	
	public int numberOfQuestions() {
		return questions.size();
	}

} // End TMPdb
