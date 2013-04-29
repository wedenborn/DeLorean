package com.delorean.jeopardy;

import com.delorean.jeopardy.registerDialogFragment.registerDialogListener;
import com.example.jeapordytest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends FragmentActivity
implements SignInDialogFragment.SignInDialogListener, registerDialogFragment.registerDialogListener {

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
		DialogFragment signInDialog = new SignInDialogFragment();
        signInDialog.show(getSupportFragmentManager(), "SignInDialogFragment");
	}

	// The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
	@Override
	public void onSignInPositiveClick(String inUsername, String inPassword) {
		Log.d(HomeActivity.LOG_TAG, "user: " + inUsername);
		Log.d(HomeActivity.LOG_TAG, "pass: " + inPassword);
		
		login.setUserName(inUsername);
		login.setPassword(inPassword);
		
		if(Math.min(inUsername.length(), inPassword.length())>=3 && login.negotiateCredentials()) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}
	
	public void register(View view) {
		DialogFragment registerDialog = new registerDialogFragment();
        registerDialog.show(getSupportFragmentManager(), "RegisterDialogFragment");
	}

	@Override
	public void onRegisterPositiveClick(String newUsername, String newPassword, String confirmPassword) {
		Log.d(HomeActivity.LOG_TAG, "user: " + newUsername);
		Log.d(HomeActivity.LOG_TAG, "pass: " + newPassword);
		
		login.setUserName(newUsername);
		login.setPassword(newPassword);
		
		if(newPassword.equals(confirmPassword) && Math.min(newUsername.length(), newPassword.length())>=3 && login.registerAccount(newUsername, newPassword)) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
		
	}


}
