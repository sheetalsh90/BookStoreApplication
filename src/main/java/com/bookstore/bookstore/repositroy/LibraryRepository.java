package com.bookstore.bookstore.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

	Library findByLibraryId(Long libraryId);

}
