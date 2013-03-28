package com.delorean.jeopardy;

import java.util.Random;

import com.example.jeapordytest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {
	
	public final static String QUESTION_ID = "com.example.jeopardytest.questionID";
	public final static String LOG_TAG = "www";
	public final static int NUMBER_OF_QUESTIONS = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
    	int id = randomQuestionID();
    	Log.d(LOG_TAG, String.valueOf(id));
    	intent.putExtra(QUESTION_ID, id);
    	startActivity(intent);
	}
	//
	private int randomQuestionID () {
		Random rand = new Random();
		return rand.nextInt(NUMBER_OF_QUESTIONS);
	}

}
