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

public class registerDialogFragment extends DialogFragment {

public EditText editTextUsername;
public EditText editTextPassword;
public EditText editTextConfirm;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View v = inflater.inflate(R.layout.dialog_register, null);
		editTextUsername = (EditText) v.findViewById(R.id.register_username);
		editTextPassword = (EditText) v.findViewById(R.id.register_password);
		editTextConfirm = (EditText) v.findViewById(R.id.register_confirm_password);
		
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(v)
		
		// Add action buttons
		.setPositiveButton(R.string.register_button_create, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// Send the positive button event back to the host activity
				String username = editTextUsername.getText().toString();
				String password = editTextPassword.getText().toString();
				String confirm = editTextConfirm.getText().toString();
				Log.d(HomeActivity.LOG_TAG, username);
				Log.d(HomeActivity.LOG_TAG, password);
				Log.d(HomeActivity.LOG_TAG, confirm);
				mListener.onRegisterPositiveClick(username,password,confirm);
			}
		})
		.setNegativeButton(R.string.register_button_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				registerDialogFragment.this.getDialog().cancel();
			}
		});      
		return builder.create();
	} // End onCreateDialog method


	/* The activity that creates an instance of this dialog fragment must
	 * implement this interface in order to receive event callbacks.
	 * Each method passes the DialogFragment in case the host needs to query it. */
	public interface registerDialogListener {
		public void onRegisterPositiveClick(String username, String password, String confirmPassword);
	}

	// Use this instance of the interface to deliver action events
	registerDialogListener mListener;
	
	

	// Override the Fragment.onAttach() method to instantiate the RegisterDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the RegisterDialogListener so we can send events to the host
			mListener = (registerDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement registerDialogListener");
		}
	}


} //End RegisterDialogFragment class
