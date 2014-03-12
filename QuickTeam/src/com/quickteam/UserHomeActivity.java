package com.quickteam;

import com.quickteam.db.Router;
import com.quickteam.db.Task;
import com.quickteam.db.TaskOperation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
 
public class UserHomeActivity extends Activity {
	private TextView userTaskTitleTxt;
	private TextView subTaskOneLbl;
	private ProgressBar oneProgressBar;
	private TextView subTaskTwoLbl;
	private ProgressBar twoProgressBar;
	private TextView subTaskThreeLbl;
	private ProgressBar threeProgressBar;
	
	private Button addNewTask;
	private Button editTask;
	private Intent editIntent;
	private int[] ids= new int[]{-1,-1};
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		
		//get Ids from previews views: ids[0]= userId; ids[1]=taskId 
		
		ids=this.getIntent().getExtras().getIntArray("ids");
		
		initUi(); 
		bind();
		fetchData();
	} 
	 public void editTask(View v){
			/*
			 * on edit click button:
			 * start new intent: put extra data
			 */
			 
			 Router router = new Router();
			 router.doEditTask(ids, this);
		 }
	
	 private void fetchData() {
		 TaskOperation taskOperation = new TaskOperation(this);
		 taskOperation.open();
		 /*
		  * on recuper les parametres depuis l intent, puis en cherche dans la base de donnée en fonction
		  * du resultat
		  */
		   Task taskFromDb;
		
			
			if(ids[1]<=0){
				//Toast.makeText(this.getApplicationContext(),"Something wrong, cant fetch data *__* ! ", Toast.LENGTH_LONG).show();
				//return;
			}
			 taskFromDb = taskOperation.getTaskByUserId(ids[0]);
			 if(taskFromDb==null){
				 Toast.makeText(this.getApplicationContext(),"Something wrong, No task found  *__* ! ", Toast.LENGTH_LONG).show();
				 return;
			 }
			 ids[1]=taskFromDb.getId();
			 taskFromDb.setTitle("Week N* 12 ");
			 taskFromDb.setOneProgress(20);taskFromDb.setTwoProgress(70);taskFromDb.setThreeProgress(55);
			 taskFromDb.setSubTaskOne("Task2");taskFromDb.setSubTaskTwo("second:!");taskFromDb.setSubTaskThree("Troisieme :#");
			 taskOperation.updateTask(ids[1], taskFromDb);
			 userTaskTitleTxt.setText(taskFromDb.getTitle());
			 subTaskOneLbl.setText(taskFromDb.getSubTaskOne());
			 oneProgressBar.setProgress(taskFromDb.getOneProgress());
			 subTaskTwoLbl.setText(taskFromDb.getSubTaskThree());
			 twoProgressBar.setProgress(taskFromDb.getTwoProgress());
			 subTaskThreeLbl.setText(taskFromDb.getSubTaskTwo());
			 threeProgressBar.setProgress(taskFromDb.getThreeProgress());
			 
	}
	protected void initUi() {
		 userTaskTitleTxt = (TextView) findViewById(R.id.userTaskTitleTxt);
		 userTaskTitleTxt.setTypeface(Typeface.MONOSPACE);
		 subTaskOneLbl = (TextView) findViewById(R.id.subTaskOneLbl);
		 oneProgressBar= (ProgressBar) findViewById(R.id.oneProgressBar);
			oneProgressBar.setMax(100);
			oneProgressBar.getProgressDrawable().setColorFilter(Color.RED, Mode.SRC_IN);
			
		 subTaskTwoLbl = (TextView) findViewById(R.id.subTaskTwoLbl);
		 twoProgressBar= (ProgressBar) findViewById(R.id.twoProgressBar);
			 twoProgressBar.setMax(100);
			 twoProgressBar.getProgressDrawable().setColorFilter(Color.RED, Mode.SRC_IN);
		 subTaskThreeLbl = (TextView) findViewById(R.id.subTaskThreeLbl);
		 
		 threeProgressBar= (ProgressBar) findViewById(R.id.threeProgressBar);
			 threeProgressBar.setMax(100);
			 threeProgressBar.getProgressDrawable().setColorFilter(Color.RED, Mode.SRC_IN);
		 
		 addNewTask = (Button) findViewById(R.id.addNewTask);
		 editTask = (Button) findViewById(R.id.editTask);
	}

	private void bind() { 
		 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}

}
