package com.assignment.netent.bookStore.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.netent.bookStore.Service.BookStoreService;
import com.assignment.netent.bookStore.model.Book;

@RestController
@RequestMapping("/api/book")
public class BookStoreController{

	@Autowired
	BookStoreService bookStoreService;

	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
		boolean added = bookStoreService.addBook(book);
		if(added)
			return ResponseEntity.ok().body("Book '" + book.getTitle() + "' added successfully to the store");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book not added. Internal Error occured.");
	}

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		return bookStoreService.getAllBooks();
	}

	@GetMapping("/getBookByIsbn/{isbn}")
	public ResponseEntity<Object> getBookByIsbn(@PathVariable("isbn") String isbn){
		Optional<Book> book = bookStoreService.getBookByIsbn(isbn);
		if(!book.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
		return ResponseEntity.ok().body(book);
	}

	@GetMapping("/getBookByAuthor/{author}")
	public ResponseEntity<Object> getBookByAuthor(@PathVariable("author") String author){
		List<Book> book= bookStoreService.getBooksByAuthor(author);
		if(book.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
		return ResponseEntity.ok().body(book);
	}

	@GetMapping("/getBookByTitle/{title}")
	public ResponseEntity<Object> getBookByTitle(@PathVariable("title") String title){
		List<Book> book= bookStoreService.getBooksByTitle(title);
		if(book.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
		return ResponseEntity.ok().body(book);
	}

	@GetMapping("/getMediaCoverage/{isbn}")
	public ResponseEntity<Object> getMediaCoverage(@PathVariable("isbn") String isbn){
		List<String> coverage= bookStoreService.getMediaCoverage(isbn);
		if(coverage.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
		return ResponseEntity.ok().body(coverage);
	}

	@GetMapping("/buy/{isbn}")
	public ResponseEntity<String> but(@PathVariable("isbn") String isbn){
		Optional<Book> book = bookStoreService.buy(isbn);
		if(book.isPresent())
			return ResponseEntity.ok().body("Book '" + book.get().getTitle() + "' bought successfully from the store");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book not found.");
	}
}