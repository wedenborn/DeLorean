package com.delorean.jeopardy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.delorean.jeopardy.R;

public class RoundActivity extends Activity {
	public TMPdb db;
	public Question question;
	private List<Integer> placements;
	private List<Integer> unPlayedQuestions;
	private List<Button> answerButtons;
	private int correctAnswerPosition;
	private int[] inCorrectAnswerPositions;
	int numberOfQuestions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

		super.onCreate(savedInstanceState);

		placements = new LinkedList<Integer>();
		placements.add(0);placements.add(1);placements.add(2);placements.add(3);
		answerButtons = new LinkedList<Button>();
		inCorrectAnswerPositions = new int[3];



		setContentView(R.layout.activity_round);

		Intent intent = getIntent();
		db = new TMPdb(getAssets());
//				(TMPdb) intent.getParcelableExtra(HomeActivity.DATABASE);
		numberOfQuestions = db.numberOfQuestions();
//				intent.getIntExtra(HomeActivity.NUMBER_OF_QUESTIONS,0);
		int questionID = intent.getIntExtra(HomeActivity.QUESTION_ID,-1);
		unPlayedQuestions  = intent.getIntegerArrayListExtra(HomeActivity.UNPLAYED_QUESTIONS);
		try {
			if(unPlayedQuestions.isEmpty()) {
				unPlayedQuestions = populateUnPlayedQuestions(unPlayedQuestions);
				Log.d(HomeActivity.LOG_TAG, String.valueOf("ISEMPTY_IN"));
			}
			Log.d(HomeActivity.LOG_TAG, String.valueOf("ISEMPTY_OUT"));
		} catch(NullPointerException e) {
			unPlayedQuestions = populateUnPlayedQuestions(unPlayedQuestions);
		}


		if(questionID == -1) { //random question
			questionID = randomQuestionID(unPlayedQuestions);
		} else { //Selected question manually
			questionID = questionID - 1; //Index starts at 0
			unPlayedQuestions.remove(questionID);
		}

		Log.d(HomeActivity.LOG_TAG, String.valueOf("Question index: " + questionID)); // syso check
		question = db.getQuestion(questionID);
		Log.d(HomeActivity.LOG_TAG, String.valueOf("Question ID: " + question.getId())); // syso check
		Log.d(HomeActivity.LOG_TAG, String.valueOf("-------"));

		TextView questionIDtext = (TextView) findViewById(R.id.question_id_text);
		TextView questionText = (TextView) findViewById(R.id.question_text);
		//Make it scrollable:
		questionText.setMovementMethod(new ScrollingMovementMethod());

		answerButtons.add( (Button) findViewById(R.id.answer_1) );
		answerButtons.add( (Button) findViewById(R.id.answer_2) );
		answerButtons.add( (Button) findViewById(R.id.answer_3) );
		answerButtons.add( (Button) findViewById(R.id.answer_4) );



		questionIDtext.setText("Question " + String.valueOf(question.getId()));
		questionText.setText(question.getQuestion());

		fillPosistions();
		answerButtons.get(correctAnswerPosition).setText(question.correctAnswer.answer);
		answerButtons.get(correctAnswerPosition).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {	correctAnswerButtonPushed(v);}});

		for(int i = 0; i < 3; i++) {
			final Button butt = answerButtons.get(inCorrectAnswerPositions[i]);
			butt.setText(question.incorrectAnswers[i].answer);
			butt.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {	
					butt.setBackgroundResource(R.drawable.red_gradient_outline);
					inCorrectAnswerButtonPushed(v);}});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/**
	 * To generate a random question ID, from 0 to numberOfQuestions
	 * @return The id
	 */
	private int randomQuestionID (int previousQuestionID) {
		Random rand = new Random();
		int size = db.numberOfQuestions();
		int nextID = previousQuestionID;
		if(previousQuestionID == -1) { //No previous question
			nextID = rand.nextInt(size);
		} else {
			while(nextID == previousQuestionID) {
				nextID = rand.nextInt(size);
			}
		}

		return nextID;
	}

	private int randomQuestionID (List<Integer> unPlayedQuestions) {
		Random rand = new Random();
		for(Integer integer : unPlayedQuestions) {
			Log.d(HomeActivity.LOG_TAG, String.valueOf("unPlayed qIndex: " + integer));
		}
		int size = unPlayedQuestions.size();
		int nextID = rand.nextInt(size);
		Log.d(HomeActivity.LOG_TAG, String.valueOf("RandomRemovedIndex: " + nextID + "of " + (size-1)));
		return unPlayedQuestions.remove(nextID);
	}
 
	private List<Integer> populateUnPlayedQuestions (List<Integer> unPlayedQuestions) {
		Log.d(HomeActivity.LOG_TAG, String.valueOf("Populating... Size = " + numberOfQuestions));
		unPlayedQuestions = new ArrayList<Integer>();
		for(int i=0; i<numberOfQuestions;i++) {
			unPlayedQuestions.add(i);
			Log.d(HomeActivity.LOG_TAG, String.valueOf("Populate: " +unPlayedQuestions.get(i)));
		}
		return unPlayedQuestions;
	}
 
	/**
	 * Generates random positions for the correct and incorrect answers
	 */
	public void fillPosistions() {
		correctAnswerPosition = randomAnswerPlacement();
		for(int i = 0; i < 3; i++) {
			inCorrectAnswerPositions[i] = randomAnswerPlacement();
		}

	}

	/**
	 * A method to decide witch button will hold which answer
	 * @return The placement, from 0 to 3
	 */
	private int randomAnswerPlacement () {
		Random rand = new Random();
		int placementIndex = rand.nextInt(placements.size());
		return placements.remove(placementIndex);
	}

	public void hintButtonPushed(View view) {
		TextView questionText = (TextView) findViewById(R.id.question_text);
		try {
			questionText.append(" " + question.popHint().getHint());
		} catch(NullPointerException e) {
			Log.d(HomeActivity.LOG_TAG, "No more hints");
		}
	}

	public void correctAnswerButtonPushed(View view) {
		answerButtons.get(correctAnswerPosition).setBackgroundResource(R.drawable.green_gradient_outline);
		final Intent intent = new Intent(this, RoundActivity.class);
		int id = -1;//randomQuestionID();
		intent.putExtra(HomeActivity.QUESTION_ID, id);
		//intent.putExtra(HomeActivity.PREVIOUS_QUESTION_ID, question.getId()-1);// -1 cause we want index
		intent.putExtra(HomeActivity.NUMBER_OF_QUESTIONS,numberOfQuestions);
		intent.putIntegerArrayListExtra(HomeActivity.UNPLAYED_QUESTIONS, (ArrayList<Integer>) unPlayedQuestions);
		Timer timer = new Timer();
		timer.schedule( new TimerTask() {
			public void run() {


				startActivity(intent);
			}
		}, 1*1000);
	}

	public void inCorrectAnswerButtonPushed(View view) {
		answerButtons.get(correctAnswerPosition).setBackgroundResource(R.drawable.green_gradient_outline);
		final Intent intent = new Intent(this, RoundActivity.class);
		int id = -1;//randomQuestionID();
		intent.putExtra(HomeActivity.QUESTION_ID, id);
		//		intent.putExtra(HomeActivity.PREVIOUS_QUESTION_ID, question.getId()-1); // -1 cause we want index
		intent.putExtra(HomeActivity.NUMBER_OF_QUESTIONS,numberOfQuestions);
		intent.putIntegerArrayListExtra(HomeActivity.UNPLAYED_QUESTIONS, (ArrayList<Integer>) unPlayedQuestions);
		Timer timer = new Timer();
		timer.schedule( new TimerTask() {
			public void run() {


				startActivity(intent);
			}
		}, 2*1000);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

}


