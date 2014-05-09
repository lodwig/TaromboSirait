package com.tarombo.sirait.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for database SIRAIT's family
 * @author Hengki Lodwig Sirait
 *
 */
public abstract class BaseClassDatabase extends SQLiteOpenHelper {

	protected SQLiteDatabase _database;
	public BaseClassDatabase(Context context, String name, CursorFactory factory, int dbVersion) {
		super(context, name, factory, dbVersion);
		
	}
	
	public SQLiteDatabase getDatabase(){ return _database;}
	
	/**
	 * Function to check is the database is open or not
	 * @return
	 */
	public boolean IsDatabaseOpen(){
		if(_database != null)
			return _database.isOpen();
		
		return false;
	}
	public void beginTransaction(){
		_database.beginTransaction();
	}
	public void commitTransaction(){
		_database.endTransaction();
	}
	public boolean inTransaction(){
		return _database.inTransaction();
	}
	
	/**
	 * Function to execute the query on database without returning object
	 * @param sql
	 */
	public void ExecQuery(String sql){
		try
		{
			if(!_database.isOpen())
				_database = getWritableDatabase();
			 _database.execSQL(sql);
		}
		catch(SQLException e)
		{
			Log.e("BaseClassDatabase:", e.getMessage());
		}
	}
	
	/**
	 * Function to query for table in database
	 * @param query
	 * @param values
	 * @return Cursor
	 */
	public Cursor Query(String query,String[] values)
	{
		Cursor cursor = null;
		try
		{
			if(!_database.isOpen())
				_database = getWritableDatabase();
			
			cursor = _database.rawQuery(query, values);
		}
		catch(SQLException e)
		{
			Log.e("BaseClassDatabase:", e.getMessage());
		}
		
		return cursor;
	}
	
	/**
	 * Function to opening database
	 */
	public void OpenDatabase(){
		try {
			
			if (_database == null || !_database.isOpen())
				_database = getWritableDatabase();
			
		} catch (SQLException e) {

		}
	}
	/**
	 * Function to closing database
	 */
	public void CloseDatabase()
	{
		if(_database != null)
			_database.close();
	}

}
