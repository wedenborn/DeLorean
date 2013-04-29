package com.delorean.jeopardy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.jeapordytest.R;

public class SignInDialogFragment extends DialogFragment {

public EditText editTextUsername;
public EditText editTextPassword;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.dialog_signin, null);
		editTextUsername = (EditText) v.findViewById(R.id.signIn_username);
		editTextPassword = (EditText) v.findViewById(R.id.signIn_password);
		
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(v)
		
		// Add action buttons
		.setPositiveButton(R.string.signIn_button_signIn, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// Send the positive button event back to the host activity
				String username = editTextUsername.getText().toString();
				String password = editTextPassword.getText().toString();
				Log.d(HomeActivity.LOG_TAG, username);
				Log.d(HomeActivity.LOG_TAG, password);
				mListener.onSignInPositiveClick(username,password);
			}
		})
		.setNegativeButton(R.string.signIn_button_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				SignInDialogFragment.this.getDialog().cancel();
			}
		});      
		return builder.create();
	} // End onCreateDialog method


	/* The activity that creates an instance of this dialog fragment must
	 * implement this interface in order to receive event callbacks.
	 * Each method passes the DialogFragment in case the host needs to query it. */
	public interface SignInDialogListener {
		public void onSignInPositiveClick(String username, String password);
	}

	// Use this instance of the interface to deliver action events
	SignInDialogListener mListener;
	
	

	// Override the Fragment.onAttach() method to instantiate the SignInDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the SignInDialogListener so we can send events to the host
			mListener = (SignInDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement SignInDialogListener");
		}
	}


} //End SignInDialogFragment class
