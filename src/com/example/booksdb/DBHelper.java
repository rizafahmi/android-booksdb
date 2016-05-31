package com.example.booksdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "BookDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "books";
	
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_AUTHOR = "author";
	
	private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_AUTHOR};
	
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
	
	public void addBook(Book book) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, book.getTitle());
		values.put(KEY_AUTHOR, book.getAuthor());
		
		db.insert(TABLE_NAME, null, values);
		
		db.close();
		
		
	}
	
	public Book getBook(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, " id= ?", new String[] { String.valueOf(id) }, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
			
			
		}
		
		Book book = new Book();
		book.setId(Integer.parseInt(cursor.getString(0)));
		book.setTitle(cursor.getString(1));
		book.setAuthor(cursor.getString(2));
		
		return book;
	}

}
