package com.delorean.jeopardy;

import java.util.Random;

import com.delorean.jeopardy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {
	
	public final static String QUESTION_ID = "com.delorean.HomeActivity.questionID";
	public final static String PREVIOUS_QUESTION_ID = "com.delorean.HomeActivity.previousQuestionID";
	public final static String NUMBER_OF_QUESTIONS = "com.delorean.HomeActivity.numberOfQuestionsInDatabase";
	public final static String UNPLAYED_QUESTIONS = "com.delorean.HomeActivity.unplayedQuesions";
	public final static String LOG_TAG = "wwoweee";
	public TMPdb db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new TMPdb();
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void toRandomQuestion (View view) {
		Intent intent = new Intent(this, RoundActivity.class);
    	int id = -1;//randomQuestionID();
    	int prevID = -1;
    	intent.putExtra(QUESTION_ID, id);
    	intent.putExtra(PREVIOUS_QUESTION_ID, prevID);
    	intent.putExtra(NUMBER_OF_QUESTIONS, db.numberOfQuestions());
    	startActivity(intent);
	}
	
	public void toSelectQuestion (View view) {
		Intent intent = new Intent(this, SelectQuestionActivity.class);
		intent.putExtra(NUMBER_OF_QUESTIONS, db.numberOfQuestions());
    	startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
	
}
