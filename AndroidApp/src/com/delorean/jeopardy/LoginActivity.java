package com.delorean.jeopardy;


import com.delorean.jeopardy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends FragmentActivity {

	public Login login;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login = new Login();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void playAnonymously (View view) {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	public void signIn(View view) {

        Intent intent = new Intent(this, SignInActivity.class);
		startActivity(intent);
	}


	
	public void register(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}




}
