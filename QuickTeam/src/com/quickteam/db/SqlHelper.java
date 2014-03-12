package com.quickteam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
	
	/*
	 * we have first create user/manager and tasks
	 */
	 
    static String taskTable="CREATE TABLE " + "task" + " ("
    		+ "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ "userId" + " INTEGER , "
    		+ "title" + " TEXT ,"
    		+ "subTaskOne" + " TEXT ,"
    		+ "subTaskTwo" + " TEXT ,"
    		+ "subTaskThree" + " TEXT ,"
    		+ "dateStart" + " Date ,"
    		+ "dateEnd" + " Date"+");"
    		+"";
    static String userTable="CREATE TABLE " + "user" + " ("
    		+ "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ "username" + " TEXT ,"
    		+ "password" + " TEXT ,"
    		+ "managerId" + " INTEGER ,"
    		+ "role" + " TEXT"+");"
    		+"";
    
	public SqlHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		 
		 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(userTable);
		db.execSQL(taskTable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		 
	}

}
