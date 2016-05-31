package com.example.booksdb;

public class Book {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}
