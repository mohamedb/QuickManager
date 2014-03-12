package com.quickteam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserOperation {

	private static final int version = 1;
	private static final String dbName = "quickManager";
	private static final String tableName = "user";
	
	private static final String id = "id";
	private static final int idNumCol = 0;
	
	private static final String username = "username";
	private static final int usernameIdNumCol = 1; 
	
	private static final String password = "password";
	private static final int passwordNumCol = 2;
	
	private static final String role = "role";
	private static final int roleNumCol = 3; 
	
	private static final String managerId = "managerId";
	private static final int managerIdNumCol = 4; 
    
	private SQLiteDatabase dataBase; 
	private SqlHelper sqlHelper;
	
	public UserOperation(Context context) {
		sqlHelper = new SqlHelper(context, dbName, null, version);
		open();
	}
	
	
	public long insertUser(User user){
		ContentValues values = new ContentValues();
		
		values.put(username, user.getUsername());
		values.put(password, user.getPassword());
		values.put(role, user.getRole());
		values.put(managerId, user.getManagerId());
		return dataBase.insert(tableName, null, values);
	}
	
	public int updateUser(int userId, User user){
		
		ContentValues values = new ContentValues();
		values.put(username, user.getUsername()); 
		values.put(password, user.getPassword());
		values.put(role,user.getRole());
		values.put(managerId, user.getManagerId());
		return dataBase.update(tableName, values, UserOperation.id + " = " +userId, null);
	}
	
	public User getUserById(int _userId){
		/*
		 * Récupère dans un Cursor les valeurs correspondant a une Task contenu dans la BDD 
		 * (ici on sélectionne la task selon l'id de l user)
		 */
		 Cursor cursor = dataBase.query(tableName, new String[] { id, username,password,
				 role,managerId }, id
                 + "=" + _userId, null, null, null, null);
		return cursorToUserObject(cursor);  
	}
	
	public User isExist(String username, String password){
		 Cursor cursor = dataBase.query(tableName, new String[] { UserOperation.id, UserOperation.username,UserOperation.password,
				 role,managerId },
				 null, null, null, null, null);
		 User user= this.cursorToUserObject(cursor);
		 if(user.getPassword().equals(password))
			 return user;
		return null;
	}
 
	/*
	 * Cette méthode permet de convertir un cursor en Task object
	 */
	private User cursorToUserObject(Cursor cursor){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (cursor.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		cursor.moveToFirst();
		//On créé un task
		User user = new User();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		user.setId(cursor.getInt(idNumCol));
		user.setUsername(cursor.getString(usernameIdNumCol));
		user.setPassword(cursor.getString(passwordNumCol));
		user.setRole(cursor.getString(roleNumCol));
		user.setRole(cursor.getString(managerIdNumCol));
		 
		//On ferme le cursor
		cursor.close();
		return user;
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
