package com.bookstore.bookstore.entity;

import java.util.List;

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
@Table(name = "Library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long libraryId;
	private String libraryAddress;
	 @OneToMany(mappedBy = "library",cascade = CascadeType.ALL )
	private List<Book> books;

}
