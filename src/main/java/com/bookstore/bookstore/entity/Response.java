package com.bookstore.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private Long bookId;
	private Long categoryId;
	private Long libraryId;
	private Long authorId;
	private String description;
	private String publicationYear;

	public Long getBookId() {
		return bookId;
	}

}
