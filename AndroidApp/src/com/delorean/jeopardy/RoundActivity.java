package com.delorean.jeopardy;


import com.example.jeapordytest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class RoundActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_round);
		
		Intent intent = getIntent();
		int questionID = intent.getIntExtra(HomeActivity.QUESTION_ID,-1);
		
		TextView questionText = (TextView) findViewById(R.id.question_id_text);
		
		Log.d(HomeActivity.LOG_TAG, String.valueOf(questionID));
		questionText.setText("Question " + String.valueOf(questionID));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
