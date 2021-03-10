package com.bookstore.bookstore.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Request;
import com.bookstore.bookstore.entity.Response;
import com.bookstore.bookstore.service.BookService;

@RestController
@RequestMapping("books")
@Transactional
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/{filter}/{id}")
	public List<Response> getBooks(@PathVariable(name = "filter") String filter, @PathVariable(name = "id") Long id) {
		return (bookService.findBookByFilter(filter, id));

	}

	@PostMapping("book")
	public Book saveBooks(@RequestBody Book book) {
		return bookService.saveBook(book);

	}

	@PutMapping("book")
	public ResponseEntity<Object> updateBooks(@RequestBody Request request) {
		return bookService.UpdateBook(request);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getBookByID(@PathVariable(name = "id") Long id) {
		Book book = (bookService.findBookByBookId(id));
		if (null == book) {
			return new ResponseEntity<Object>("Book Not Found", HttpStatus.NOT_FOUND);
		}
		Response response = new Response();
		response.setAuthorId(book.getAuthor().getAuthorId());
		response.setCategoryId(book.getCategory().getCategoryId());
		response.setBookId(book.getBookId());
		response.setLibraryId(book.getLibrary().getLibraryId());
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping("/library/{libraryId}")
	public ResponseEntity<Object> getBookByLibraryId(@PathVariable(name = "libraryId") Long libraryId) {
		return bookService.findBookByLibrary(libraryId);
	}

	/// library/{library_id}/book/{book_id}/assignbook POST
	@PostMapping("library/{library_id}/book/{book_id}")
	public ResponseEntity<Object> assignBook(@PathVariable(name = "library_id") Long library_id,
			@PathVariable(name = "book_id") Long book_id) {
		return bookService.assignBook(library_id, book_id);

	}
}
