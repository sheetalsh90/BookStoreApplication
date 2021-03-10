package com.bookstore.bookstore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long authorId;
	private String description;
	
	@OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
	private Set<Book> books;

}
