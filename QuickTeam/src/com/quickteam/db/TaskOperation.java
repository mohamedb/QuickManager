package com.quickteam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskOperation {
	
	private static final int version = 1;
	private static final String dbName = "quickManager";
	private static final String tableName = "task";
	
	private static final String id = "id";
	private static final int idNumCol = 0;
	
	private static final String userId = "userId";
	private static final int userIdNumCol = 1;
	
	private static final String title = "title";
	private static final int titleNumCol = 2;
	
	private static final String subTaskOne = "subTaskOne";
	private static final int subTaskOneNumCol = 3;
	
	private static final String subTaskTwo = "subTaskTwo";
	private static final int subTaskTwoNumCol = 4; 
	
	private static final String subTaskThree = "subTaskThree";
	private static final int subTaskThreeNumCol = 5;
	
	private static final String dateStart = "dateStart";
	private static final int dateStartNumCol = 6;
	
	private static final String dateEnd = "dateEnd";
	private static final int dateEndNumCol = 7;
    
	private SQLiteDatabase dataBase; 
	private SqlHelper sqlHelper;
	
	public TaskOperation(Context context) {
		sqlHelper = new SqlHelper(context, dbName, null, version);
	}
	
	
	public long insertTask(Task task){
		if(task.getUserId()<=0)
			return 0;
		ContentValues values = new ContentValues();
		
		values.put(userId, task.getUserId());
		values.put(title, task.getTitle());
		values.put(subTaskOne,task.getSubTaskOne());
		values.put(subTaskTwo,task.getSubTaskTwo());
		values.put(subTaskThree,task.getSubTaskThree());
		values.put(dateStart,task.getDateStart());
		values.put(dateEnd,task.getDateEnd());
		
		 
		return dataBase.insert(tableName, null, values);
	}
	
	public int updateTask(int taskId, Task task){
		
		ContentValues values = new ContentValues();
		values.put(userId, task.getUserId());
		values.put(title, task.getTitle());
		values.put(subTaskOne,task.getSubTaskOne());
		values.put(subTaskTwo,task.getSubTaskTwo());
		values.put(subTaskThree,task.getSubTaskThree());
		values.put(dateStart,task.getDateStart());
		values.put(dateEnd,task.getDateEnd()); 
		return dataBase.update(tableName, values, TaskOperation.id + " = " +taskId, null);
	}
	
	public Task getTaskByUserId(int _userId){
		/*
		 * Récupère dans un Cursor les valeurs correspondant a une Task contenu dans la BDD 
		 * (ici on sélectionne la task selon l'id de l user)
		 */
		 Cursor cursor = dataBase.query(tableName, new String[] { id, userId,title,
				 subTaskOne, subTaskTwo, subTaskThree, dateStart,dateEnd }, userId
                 + "=" + _userId, null, null, null, null);
		return cursorToTaskObject(cursor); 
	}
	
 
	/*
	 * Cette méthode permet de convertir un cursor en Task object
	 */
	private Task cursorToTaskObject(Cursor cursor){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (cursor.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		cursor.moveToFirst();
		//On créé un task
		Task task = new Task();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		task.setId(cursor.getInt(idNumCol));
		task.setUserId(cursor.getInt(userIdNumCol));
		task.setTitle(cursor.getString(titleNumCol));
		task.setSubTaskOne(cursor.getString(subTaskOneNumCol));
		task.setSubTaskTwo(cursor.getString(subTaskTwoNumCol)); 
		task.setSubTaskThree(cursor.getString(subTaskThreeNumCol)); 
		task.setDateStart(cursor.getString(dateStartNumCol));
		task.setDateEnd(cursor.getString(dateEndNumCol)); 
		//On ferme le cursor
		cursor.close();
		return task;
	}
 
	
	public void open(){
 		dataBase = sqlHelper.getWritableDatabase();
	}
 
	public void close(){
 		dataBase.close();
	}
 
	public SQLiteDatabase getDataBase(){
		return dataBase;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
