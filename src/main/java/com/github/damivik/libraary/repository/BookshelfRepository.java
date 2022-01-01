package com.github.damivik.libraary.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.damivik.libraary.entity.Book;
import com.github.damivik.libraary.entity.Bookshelf;
import com.github.damivik.libraary.entity.User;

public interface BookshelfRepository extends CrudRepository<Bookshelf, Long> {
public List<Bookshelf> findByUser(User user);
	
	public String findByNameIn(List<Book> books);
}
