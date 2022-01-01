package com.github.damivik.libraary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.damivik.libraary.entity.Book;
import com.github.damivik.libraary.exception.BookNotFoundException;
import com.github.damivik.libraary.repository.BookRepository;

@Service
public class BookService {
	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> search(String title) {
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}
	
	public Book retrieve(long id) throws BookNotFoundException {
		Optional<Book> optionalBook = bookRepository.findById((long) id);
		
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		return optionalBook.get();
	}
}