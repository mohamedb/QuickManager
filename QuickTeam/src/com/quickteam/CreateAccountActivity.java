package com.quickteam;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.quickteam.db.User;
import com.quickteam.db.UserOperation;

public class CreateAccountActivity extends Activity {
	private TextView usernameNewAccountInput;
	private TextView passwordNewAccountInput;
	private RadioButton userRadio; 
	private RadioButton managerRadio; 
	private  Button saveNewAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		initUi();
        bind();
	}

	private void initUi() {
		usernameNewAccountInput = (TextView) findViewById(R.id.usernameNewAccountInput);
		usernameNewAccountInput.setTextColor(Color.BLUE);
		passwordNewAccountInput = (TextView) findViewById(R.id.passwordNewAccountInput);
		userRadio = (RadioButton) findViewById(R.id.userRadio);
		managerRadio = (RadioButton) findViewById(R.id.managerRadio);
		saveNewAccount = (Button) findViewById(R.id.saveNewAccount);
	}

	private void bind() {
	    /*
	     * here we handle diffirents events 
	     */
	}

	public void saveNewAccountAction(View v){
		/*
		 * Save account data to DB
		 * go back to UserHome or ManagerHome
		 */
		User user = new User();
		user.setUsername(usernameNewAccountInput.getText().toString());
		user.setPassword(passwordNewAccountInput.getText().toString());
		if(userRadio.isChecked())
		  user.setRole("User");
		else
			user.setRole("Manager");
		UserOperation userOperation = new UserOperation(this);
		userOperation.open();
		userOperation.insertUser(user);
		
		Intent entryIntent = new Intent(this, EntryActivity.class);
        startActivity(entryIntent);
	}

	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
		return true;
	}
	
	
}
