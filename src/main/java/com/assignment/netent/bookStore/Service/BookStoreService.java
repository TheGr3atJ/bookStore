package com.assignment.netent.bookStore.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.netent.bookStore.DAO.BookRepository;
import com.assignment.netent.bookStore.model.Book;
import com.assignment.netent.bookStore.model.Coverage;

@Service
public class BookStoreService {

	@Autowired
	BookRepository bookRepository;
	

	public boolean addBook(Book book) {
		try {
			Optional<Book> b = bookRepository.findById(book.getIsbn());
			book.setQauntity((b.isPresent()?b.get().getQauntity():0)+Math.max(1,book.getQauntity()));
			bookRepository.save(book);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookByIsbn(String isbn) {
		return bookRepository.findById(isbn);
	}

	public List<Book> getBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}
	
	public List<Book> getBooksByTitle(String title) {
		return bookRepository.findByAuthor(title);
	}

	public List<String> getMediaCoverage(String isbn) {
		
		Optional<Book> b = bookRepository.findById(isbn);
		if(b.isPresent()) {
			Coverage[] coverage = getAllPosts();
		return Arrays.stream(coverage).
				filter(c->c.isPresent(b.get().getAuthor()) || c.isPresent(b.get().getTitle()))
				.map(c->c.getTitle())
				.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	@Cacheable("posts")
	public Coverage[] getAllPosts() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Coverage[].class).getBody();
	}
	
	@Transactional
	public Optional<Book> buy(String isbn) {
		Optional<Book> book = bookRepository.findById(isbn);
		if(book.isPresent()) {
			Book b = book.get();
			int quant = b.getQauntity()-1;
			b.setQauntity(quant);
			bookRepository.save(b);
			if(quant==0)
				addBook(b);
		}
		return book;
	}
	
	
}
