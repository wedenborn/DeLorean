package com.delorean.jeopardy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.delorean.jeopardy.R;

public class SignInActivity extends Activity {

	public Login login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		Intent intent = getIntent();

		login = new Login(getAssets());
//		login.createDB(this);
	}

	public void signInClick(View v) {
		EditText usernameEditText = (EditText) findViewById(R.id.signIn_username);
		EditText passwordEditText = (EditText) findViewById(R.id.signIn_password);
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		login.setUserName(username);
		login.setPassword(password);

		if(Math.min(username.length(), password.length())>=3 && login.negotiateCredentials()) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}

	public void cancelClick(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

}
