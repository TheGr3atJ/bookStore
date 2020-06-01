package com.assignment.netent.bookStore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Book {
	
	@Id
	@Size(min = 13,max = 13,message = "Please provide valid ISBN")
	private String isbn;
	
	@NotEmpty(message = "ISBN is mandatory")
	private String title;
	
	@NotEmpty(message = "ISBN is mandatory")
	private String author;
	
	@Min(value = 0, message = "Price cannot be negative")
	private int price;
	
	private int qauntity;
	
	public Book() {
	}
	
	public Book(@Size(min = 13, max = 13, message = "Please provide valid ISBN") String isbn,
			@NotEmpty(message = "ISBN is mandatory") String title,
			@NotEmpty(message = "ISBN is mandatory") String author,
			@Min(value = 0, message = "Price cannot be negative") int price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.qauntity = 1;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQauntity() {
		return qauntity;
	}

	public void setQauntity(int qauntity) {
		this.qauntity = qauntity;
	}
	
}
