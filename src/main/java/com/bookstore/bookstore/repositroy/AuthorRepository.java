package com.bookstore.bookstore.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByAuthorId(Long id);

}
