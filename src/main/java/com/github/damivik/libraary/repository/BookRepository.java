package com.github.damivik.libraary.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.damivik.libraary.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	public List<Book> findByTitleContainingIgnoreCase(String title);
}
