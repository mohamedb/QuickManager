package com.quickteam.db;

import com.quickteam.EntryActivity;
import com.quickteam.UserEditActivity;
import com.quickteam.UserHomeActivity;

import android.content.Intent;
import android.widget.Toast;

public class Router {
    
	
	private Intent currentIntent;
	UserOperation userOperation;
	TaskOperation taskOperation;
	
	
	public  Router() {
		 
	} 
		
	public Router(UserOperation userOperation, TaskOperation taskOperation){
		 this.userOperation=userOperation; this.taskOperation=taskOperation;
	}
	
	public void doLogIn(String username,String password, EntryActivity entryActivity) {
		int[] ids= new int[]{-1,-1};
		taskOperation = new TaskOperation(entryActivity);
		taskOperation.open();
		userOperation = new UserOperation(entryActivity);
		userOperation.open();
		 
		User user=userOperation.isExist(username,password);
		    if(user!=null){
		    	/*
		    	 * init id user
		    	 */
		      ids[0]=user.getId();
			  Toast.makeText(entryActivity.getApplicationContext(),"Welcome User :)", Toast.LENGTH_LONG).show();
			  Task t= new Task(); t.setTitle("Titre de l action"); t.setSubTaskOne("fonction::A");
			  t.setSubTaskTwo("Doc:2");t.setSubTaskThree("ActionThree");t.setUserId(ids[0]); taskOperation.insertTask(t);
			  Task taskFromDb =taskOperation.getTaskByUserId(ids[0]);
			  /*
			   * on test si la task recupérée est bien valide: exists= l objet n est pas vide  !
			   */
			  if(taskFromDb==null ){
				  currentIntent = new Intent(entryActivity, UserEditActivity.class);/* create LoginActivity */
				  currentIntent.putExtra("ids",ids);
				  entryActivity.startActivity(currentIntent);
				  Toast.makeText( entryActivity.getApplicationContext(),"Ready to Start,\n Create a new task ", Toast.LENGTH_LONG).show(); 
				  return;
			  }
			  /*
			   * sinon affiche view pour creer une activité!
			   */
			  currentIntent = new Intent(entryActivity, UserHomeActivity.class);/* create LoginActivity */
			  currentIntent.putExtra("userId",user.getId());
			  
			   
			  ids[1]=taskFromDb.getId();
			  
			  currentIntent.putExtra("ids", ids);
			  if(ids[1]>=1 && ids[0]>=1)
			    entryActivity.startActivity(currentIntent);
			  return;
	        }
	         Toast.makeText(entryActivity.getApplicationContext(),"Bad credentials *__*' ", Toast.LENGTH_LONG).show();
		
	}
	
	public void doEditTask(int[] ids, UserHomeActivity userHomeActivity){
		if(ids[1]<=0){
			 Toast.makeText(userHomeActivity.getApplicationContext(),"Something wrong, cant Edit task:ID task est = ! "+ids[1], Toast.LENGTH_LONG).show();
			 return; 
		 }
		 Toast.makeText(userHomeActivity.getApplicationContext(),"Edit is Clicked :)", Toast.LENGTH_LONG).show();
		 currentIntent = new Intent(userHomeActivity, UserEditActivity.class);/* edit activity */
		 currentIntent.putExtra("ids", ids);
		 userHomeActivity.startActivity(currentIntent);
		
	}
	public Intent getCurrentIntent(){
		return currentIntent;
	}
	
}
