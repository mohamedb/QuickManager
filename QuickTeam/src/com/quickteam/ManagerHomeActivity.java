package com.quickteam;

import com.quickteam.db.ImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ManagerHomeActivity extends Activity {
	GridView listGrid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_home);
		
		listGrid = (GridView) findViewById(R.id.listGrid);
		listGrid.setAdapter(new ImageAdapter(this));

		listGrid.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(getApplicationContext(), "Number: " + position+" is clicked", Toast.LENGTH_SHORT).show();
	        }	
	    });

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager_home, menu);
		return true;
	}

}
