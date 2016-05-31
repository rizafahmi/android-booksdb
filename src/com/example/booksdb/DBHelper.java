package com.example.booksdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "BookDB";
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create a table
		
		String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"title TEXT, " +
				"author TEXT )";
		db.execSQL(CREATE_BOOK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop table if existed
		db.execSQL("DROP TABLE IF EXISTS books");
		
		// Create new table
		this.onCreate(db);
	}

}
