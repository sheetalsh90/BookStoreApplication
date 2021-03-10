package com.bookstore.bookstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId")
	private Long bookId;
	private String description;
	private String publicationYear;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "authorId")
	  private Author author;
	 
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "libraryId")
	private Library library;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibraries(Library library) {
		this.library = library;
	}
	

}
