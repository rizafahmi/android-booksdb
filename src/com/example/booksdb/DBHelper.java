package com.example.booksdb;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "BookDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "books";
	
	public static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_AUTHOR = "author";
	
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
	
	public Cursor getAllBooksCursor() {
		String query = "SELECT rowid AS _id, * FROM " + TABLE_NAME;
		
		SQLiteDatabase db = this.getWritableDatabase();
		return db.rawQuery(query, null);
	}
	public Cursor searchBooksCursor(String text) {
		String query = "SELECT rowid AS _id, * FROM " + TABLE_NAME + 
				" WHERE " + KEY_TITLE + " LIKE '%" + text + "%' ";
		
		SQLiteDatabase db = this.getWritableDatabase();
		return db.rawQuery(query, null);
	}
	
	public List<Book> getAllBooks() {
		List<Book> books = new LinkedList<Book>();
		
		String query = "SELECT * FROM " + TABLE_NAME;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Book book = null;
		
		if (cursor.moveToFirst()) {
			do {
				book = new Book();
				book.setId(Integer.parseInt(cursor.getString(0)));
				book.setTitle(cursor.getString(1));
				book.setAuthor(cursor.getString(2));
				
				books.add(book);
			} while (cursor.moveToNext());
			
		}
	
		Log.d("getAllBooks()", books.toString());
		return books;
	}
	
	public int updateBook(Book book) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("title", book.getTitle());
		values.put("author", book.getAuthor());
		
		int i = db.update(TABLE_NAME, values, KEY_ID+" = ?", new String[] { String.valueOf(book.getId())});
		
		db.close();
		
		return i;
	}
	
	public void deleteBook(Book book) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(TABLE_NAME, KEY_ID +" = ?", new String[] { String.valueOf(book.getId()) });
		db.close();
		
		Log.d("deleteBook", book.toString());
	}

}
