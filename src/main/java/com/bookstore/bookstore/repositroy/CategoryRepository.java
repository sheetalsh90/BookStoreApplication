package com.bookstore.bookstore.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByCategoryId(Long id);

}
