package com.example.booksdb;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DBHelper db = new DBHelper(this);
		
		db.addBook(new Book("Android book", "Test"));
		db.addBook(new Book("Android book 2", "Test 2"));
		
		List<Book> list = db.getAllBooks();
		
		db.deleteBook(list.get(0));
		
		db.getAllBooks();
	}
}
