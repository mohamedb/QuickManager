package com.quickteam;

 

import com.quickteam.db.Router;
import com.quickteam.db.Task;
import com.quickteam.db.TaskOperation;
import com.quickteam.db.User;
import com.quickteam.db.UserOperation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EntryActivity extends Activity {
	private TextView username;
	private TextView password;
	private Button logIn;
	private Button createAccount;
	private Intent intent;
	private Intent creatAccountIntent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);       
        initUi();
        bind();
    }


    protected void bind() {
		 username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				 /* Need some implementation here*/
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				 /*
				  * check email format!
				  */
				if(Patterns.EMAIL_ADDRESS.matcher(username.getText()).matches()){
					logIn.setEnabled(true);
					return;
				}
				logIn.setEnabled(false);
			}
		});
		 
	}

  

   public void logInAction(View v){
	  // Router router = new Router();
	 //  router.doLogIn(username.getText().toString(), password.getText().toString(), this);  
	   creatAccountIntent= new Intent(this, ManagerHomeActivity.class);/* create LoginActivity */
	   startActivity(creatAccountIntent);
   }
   public void creatAccountAction(View v){
	   Toast.makeText(getApplicationContext()," Create un account :)", Toast.LENGTH_LONG).show();
	   creatAccountIntent= new Intent(this, CreateAccountActivity.class);/* create LoginActivity */
	   startActivity(creatAccountIntent);
   }
    protected void initUi() {
    	    username = (TextView) findViewById(R.id.username);
    	    password = (TextView) findViewById(R.id.password);
	    	logIn = (Button) findViewById(R.id.logIn);
	    	createAccount = (Button) findViewById(R.id.createAccount);
	    	
	    	username.setText("med@med.com"); password.setText("med");
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entry, menu);
        return true;
    }
    
}
