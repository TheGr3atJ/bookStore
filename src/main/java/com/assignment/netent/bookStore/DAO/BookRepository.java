package com.assignment.netent.bookStore.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.netent.bookStore.model.Book;

@Repository
public  interface BookRepository extends JpaRepository<Book, String> {
	@Query("Select b from Book b where lower(author) like %:author% or author like %:author%")
	public List<Book> findByAuthor(@Param("author") String author);
	
	@Query("Select b from Book b where lower(title) like %:title% or title like %:title%")
	public List<Book> findByTitle(@Param("title") String title);
}
