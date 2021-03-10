package com.bookstore.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.Library;
import com.bookstore.bookstore.entity.Request;
import com.bookstore.bookstore.entity.Response;
import com.bookstore.bookstore.repositroy.AuthorRepository;
import com.bookstore.bookstore.repositroy.BookRepository;
import com.bookstore.bookstore.repositroy.CategoryRepository;
import com.bookstore.bookstore.repositroy.LibraryRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	LibraryRepository libraryRepository;

	public List<Response> findBookByFilter(String filter, Long id) {
		List<Book> books = null;
		List<Response> responses = new ArrayList<Response>();

		if (filter.equals("authorId")) {
			books = bookRepository.findByAuthor(authorRepository.findByAuthorId(id));
		}

		if (filter.equals("categoryId")) {
			books = bookRepository.findByCategory(categoryRepository.findByCategoryId(id));
		}

		for (Book book : books) {
			Response response = new Response();
			response.setBookId(book.getBookId());
			response.setAuthorId(book.getAuthor().getAuthorId());
			response.setLibraryId(book.getLibrary().getLibraryId());
			response.setCategoryId(book.getCategory().getCategoryId());
			responses.add(response);
		}
		return responses;
	}

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public Book findBookByBookId(Long id) {
		return bookRepository.findByBookId(id);
	}

	public ResponseEntity<Object> UpdateBook(Request request) {
		Optional<Book> bookObject = bookRepository.findById(request.getBookId());
		if (!bookObject.isPresent()) {
			return new ResponseEntity<Object>("Book Not Found", HttpStatus.NOT_FOUND);
		}
		bookObject.get().setBookId(request.getBookId());
		bookObject.get().setAuthor(authorRepository.findByAuthorId(request.getAuthorId()));
		bookObject.get().setLibraries(libraryRepository.findByLibraryId(request.getLibraryId()));
		bookObject.get().setCategory(categoryRepository.findByCategoryId(request.getCategoryId()));
		bookObject.get().setDescription(request.getDescription());
		bookObject.get().setPublicationYear(request.getPublicationYear());
		if (bookObject.get().getAuthor() == null) {
			return new ResponseEntity<Object>("Author Not Found", HttpStatus.NOT_FOUND);
		}
		if (bookObject.get().getCategory() == null) {
			return new ResponseEntity<Object>("Category Not Found", HttpStatus.NOT_FOUND);
		}
		bookRepository.save(bookObject.get());
		Response response = new Response();
		response.setAuthorId(bookObject.get().getAuthor().getAuthorId());
		if (bookObject.get().getLibrary() != null) {
			response.setLibraryId(bookObject.get().getLibrary().getLibraryId());
		}

		response.setCategoryId(bookObject.get().getCategory().getCategoryId());
		response.setBookId(bookObject.get().getBookId());
		response.setDescription(bookObject.get().getDescription());
		response.setPublicationYear(bookObject.get().getPublicationYear());
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> findBookByLibrary(Long libraryId) {
		Library library = libraryRepository.findByLibraryId(libraryId);
		List<Response> resList = new ArrayList<Response>();
		if (library == null) {
			return new ResponseEntity<Object>("Library Not Found", HttpStatus.NOT_FOUND);
		}
		List<Book> list = bookRepository.findByLibrary(library);
		for (Book book : list) {
			Response response = new Response();
			response.setAuthorId(book.getAuthor().getAuthorId());
			response.setBookId(book.getBookId());
			response.setCategoryId(book.getCategory().getCategoryId());
			response.setDescription(book.getDescription());
			response.setPublicationYear(book.getPublicationYear());
			response.setLibraryId(book.getLibrary().getLibraryId());
			resList.add(response);
		}
		return new ResponseEntity<Object>(resList, HttpStatus.OK);
	}

	public ResponseEntity<Object> assignBook(Long library_id, Long book_id) {
		Library library = libraryRepository.findByLibraryId(library_id);
		Book book = bookRepository.findByBookId(book_id);
		if (library == null ) {
			return new ResponseEntity<Object>("Library Not Found", HttpStatus.NOT_FOUND);
		}
		if (book == null ) {
			return new ResponseEntity<Object>("Book Not Found", HttpStatus.NOT_FOUND);
		}
		if (book.getLibrary() != null && book.getLibrary().getLibraryId() == library_id) {
			return new ResponseEntity<Object>("Library Already Assigned", HttpStatus.FOUND);
		}
		book.setLibraries(library);
		Response response = new Response();
		response.setAuthorId(book.getAuthor().getAuthorId());
		response.setBookId(book_id);
		response.setCategoryId(book.getCategory().getCategoryId());
		response.setDescription(book.getDescription());
		response.setLibraryId(book.getLibrary().getLibraryId());
		response.setPublicationYear(book.getPublicationYear());
		bookRepository.save(book);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
