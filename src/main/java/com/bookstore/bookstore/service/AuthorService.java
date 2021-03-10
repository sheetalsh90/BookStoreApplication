package com.bookstore.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.repositroy.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	AuthorRepository authorRepository;
}
