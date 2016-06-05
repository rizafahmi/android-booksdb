package com.example.booksdb;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SimpleCursorAdapter adapter;
	private EditText searchEditText;
	DBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DBHelper(this);
		
//		db.addBook(new Book("Android book", "Test"));
//		db.addBook(new Book("Programming Swift", "Apple"));
		
		
		//db.deleteBook(list.get(0));
		
		db.getAllBooks();
		
		populateListView();
		
		searchEditText = (EditText) findViewById(R.id.searchEditText);
		
		searchEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
				if (s.toString().length() > 0)
					repopulateListView(s.toString());
				else
					populateListView();
			}
		});
		
	}
	

	protected void repopulateListView(String keyword) {
		// Initiate the DB
		DBHelper db = new DBHelper(this);
		
		// Get all data
		Cursor cursor = db.searchBooksCursor(keyword);
		
		
		// Initiate Cursor Adapter
		adapter = new SimpleCursorAdapter(
				this, R.layout.item_list,
				cursor,
				new String[] {DBHelper.KEY_TITLE, DBHelper.KEY_AUTHOR},
				new int[] {R.id.titleTextView, R.id.authorTextView},
				0
				);
		
		// Define ListView
		ListView mListView = (ListView) findViewById(R.id.listView);
		
		// Set Adapter
		adapter.notifyDataSetChanged();
		mListView.setAdapter(adapter);
		
		
		db.close();
		
	}


	private void populateListView() {
		// Initiate the DB
		DBHelper db = new DBHelper(this);
		
		// Get all data
		Cursor cursor = db.getAllBooksCursor();
		
		
		// Initiate Cursor Adapter
		adapter = new SimpleCursorAdapter(
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
