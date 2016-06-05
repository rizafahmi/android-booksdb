package com.example.booksdb;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DBHelper db = new DBHelper(this);
		
//		db.addBook(new Book("Android book", "Test"));
//		db.addBook(new Book("Android book 2", "Test 2"));
		
		
		//db.deleteBook(list.get(0));
		
		db.getAllBooks();
		
		populateListView();
	}
	
	private void populateListView() {
		// Initiate the DB
		DBHelper db = new DBHelper(this);
		
		// Get all data
		Cursor cursor = db.getAllBooksCursor();
		
		
		// Initiate Cursor Adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, R.layout.item_list,
				cursor,
				new String[] {DBHelper.KEY_TITLE, DBHelper.KEY_AUTHOR},
				new int[] {R.id.titleTextView, R.id.authorTextView},
				0
				);
		
		// Define ListView
		ListView mListView = (ListView) findViewById(R.id.listView);
		
		// Set Adapter
		mListView.setAdapter(adapter);
		
		db.close();
		
	}
}
