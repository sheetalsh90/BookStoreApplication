package com.bookstore.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.repositroy.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	LibraryRepository libraryRepository;
}
