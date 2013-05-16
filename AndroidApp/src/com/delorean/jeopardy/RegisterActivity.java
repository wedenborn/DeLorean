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

public class RegisterActivity extends Activity {
	
	public Login login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Intent intent = getIntent();
		
		login = new Login(getAssets());
//		login.createDB(this);
	}

	public void registerClick(View v) {
		EditText usernameEditText = (EditText) findViewById(R.id.register_username);
		EditText passwordEditText = (EditText) findViewById(R.id.register_password);
		EditText confirmPasswordEditText = (EditText) findViewById(R.id.register_confirm_password);
		
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		String confirmPassword = confirmPasswordEditText.getText().toString();
		
		login.setUserName(username);
		login.setPassword(password);
		
		if(password.equals(confirmPassword) && Math.min(username.length(), password.length())>=3 && login.registerAccount(username, password)) {
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
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}


}
