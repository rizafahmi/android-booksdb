package com.example.booksdb;

public class Book {
	
	private int id;
	private String title, author;
	
	public Book() {
		
	}

	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + "]";
	}
	
	
	
	
	

}