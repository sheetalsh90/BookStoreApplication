package com.bookstore.bookstore.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Category;
import com.bookstore.bookstore.entity.Library;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthor(Author author);

	List<Book> findByCategory(Category category);

	Book findByBookId(Long id);

	List<Book> findByLibrary(Library library);

}
