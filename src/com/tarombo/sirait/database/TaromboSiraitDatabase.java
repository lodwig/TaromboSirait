package com.tarombo.sirait.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class TaromboSiraitDatabase extends BaseClassDatabase {

	public TaromboSiraitDatabase(Context context, String name, CursorFactory factory, int dbVersion) {
		super(context, name, factory, dbVersion);
		createDatabase();
	}

	private void createDatabase() {
		try {
			String[] createTable = new String[]{ 
					"CREATE TABLE IF NOT EXISTS [SiraitFamily] 	([GenerationId] VARCHAR(40) PRIMARY KEY COLLATE NOCASE,[Name] VARCHAR(200) NULL COLLATE NOCASE, [NumberOfSons] VARCHAR(1) DEFAULT 0 COLLATE NOCASE, [Description] VARCHAR(200) DEFAULT NULL COLLATE NOCASE)",
					"CREATE TABLE IF NOT EXISTS [Generation] 	([Id] VARCHAR(40) PRIMARY KEY COLLATE NOCASE, [FatherId] VARCHAR(50) NULL, [ChildsId] VARCHAR(50) NULL, [Description] VARCHAR(200) DEFAULT NULL COLLATE NOCASE )"};
			OpenDatabase();
			
			for (String strQuery : createTable)
				_database.execSQL(strQuery);
			
		} catch (SQLException e) {
			Log.e("CreateDB", e.getMessage());
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
