package com.quickteam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quickteam.db.Task;
import com.quickteam.db.TaskOperation;

public class UserEditActivity extends Activity {

	private TextView taskTitleInput;
	private TextView subTaskOneInput;
	private TextView subTaskTwoInput;
	private TextView subTaskThreeInput;

	private ProgressBar oneProgressBar;
	private ProgressBar twoProgressBar;
	private ProgressBar threeProgressBar;

	private Button saveTask;
	private Intent homeIntent;
	private int[] ids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		initUi();
		bind();
		ids = this.getIntent().getExtras().getIntArray("ids");

		/*
		 * call fetch() on edit. sinon on passe a la creation!
		 */
		if (ids[0] <= 0) {
			Toast.makeText(getApplicationContext(),
					"User not found!  :): ID USER = " + ids[0] + "",
					Toast.LENGTH_LONG).show();
			return;
		}
		fetchData();
	}

	private void initUi() {
		taskTitleInput = (TextView) findViewById(R.id.taskTitleInput);
		subTaskOneInput = (TextView) findViewById(R.id.subTaskOneInput);
		subTaskTwoInput = (TextView) findViewById(R.id.subTaskTwoInput);
		subTaskThreeInput = (TextView) findViewById(R.id.subTaskThreeInput);
		oneProgressBar = (ProgressBar) findViewById(R.id.onePB);
		oneProgressBar.setMax(100);
		oneProgressBar.getProgressDrawable().setColorFilter(Color.RED,
				Mode.SRC_IN);
		twoProgressBar = (ProgressBar) findViewById(R.id.twoPB);
		twoProgressBar.setMax(100);
		twoProgressBar.getProgressDrawable().setColorFilter(Color.RED,
				Mode.SRC_IN);
		threeProgressBar = (ProgressBar) findViewById(R.id.threePB);
		threeProgressBar.setMax(100);
		threeProgressBar.getProgressDrawable().setColorFilter(Color.RED,
				Mode.SRC_IN);

		saveTask = (Button) findViewById(R.id.saveTask);
	}

	private void bind() {

	}

	/*
	 * This method is called on edit
	 */
	private void fetchData() {
		Task task;
		if (ids[0] >= 0) {
			TaskOperation taskOperation = new TaskOperation(this);
			taskOperation.open();
			task = taskOperation.getTaskByUserId(ids[0]);
			if (task == null) {
				return;
			}
			taskTitleInput.setText(task.getTitle());
			subTaskOneInput.setText(task.getSubTaskOne());
			subTaskTwoInput.setText(task.getSubTaskTwo());
			subTaskThreeInput.setText(task.getSubTaskThree());

			oneProgressBar.setProgress(task.getOneProgress()+15);
			twoProgressBar.setProgress(task.getTwoProgress()+80);
			threeProgressBar.setProgress(task.getThreeProgress()+66);

		}

	}

	public void saveTask(View v) {
		/*
		 * save date to DB go back to home view.
		 */
		TaskOperation taskOperation = new TaskOperation(this);
		taskOperation.open();
		Task task = new Task();

		if (ids[0] <= 0) {
			Toast.makeText(getApplicationContext(),
					"Impossible TO SAVE  :): ID USER = " + ids[0] + "",
					Toast.LENGTH_LONG).show();
			return;

		}

		task.setTitle(taskTitleInput.getText().toString());
		task.setSubTaskOne(subTaskOneInput.getText().toString());
		task.setSubTaskOne(subTaskTwoInput.getText().toString());
		task.setSubTaskOne(subTaskThreeInput.getText().toString());

		long tempId = (int) taskOperation.updateTask(ids[1], task);
		if (tempId <= 0) {
			Toast.makeText(getApplicationContext(),
					"Can't Save *__* TaskNewId =" + ids[1], Toast.LENGTH_LONG)
					.show();
			return;
		}
		ids[1] = (int) taskOperation.insertTask(task);
		Toast.makeText(getApplicationContext(), "Save task: done :)",
				Toast.LENGTH_LONG).show();
		homeIntent = new Intent(this, UserHomeActivity.class);/*
															 * go back to home
															 * user activity
															 */
		homeIntent.putExtra("ids", ids);
		startActivity(homeIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_edit, menu);
		return true;
	}

}
