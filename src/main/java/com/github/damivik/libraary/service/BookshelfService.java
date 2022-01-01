package com.github.damivik.libraary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.damivik.libraary.entity.Book;
import com.github.damivik.libraary.entity.Bookshelf;
import com.github.damivik.libraary.entity.User;
import com.github.damivik.libraary.exception.BookshelfNotFoundException;
import com.github.damivik.libraary.repository.BookshelfRepository;

@Service
public class BookshelfService {
	private BookshelfRepository bookshelfRepository;

	public BookshelfService(BookshelfRepository bookshelfRepository) {
		this.bookshelfRepository = bookshelfRepository;
	}
	
	public Bookshelf create(User user, String name) { 
		return bookshelfRepository.save(new Bookshelf(user, name));
	}
	
	public Bookshelf retrieveBookshelf(long bookshelfId) throws BookshelfNotFoundException {
		Optional<Bookshelf> optionalBookshelf = bookshelfRepository.findById(bookshelfId);
		
		if (optionalBookshelf.isEmpty()) {
			throw new BookshelfNotFoundException();
		}
		
		return optionalBookshelf.get();
	}

	public void deleteBookshelf(Bookshelf bookshelf) {
		bookshelfRepository.delete(bookshelf);
	}
	
	public Bookshelf renameBookshelf(Bookshelf bookshelf, String name) {
		bookshelf.setName(name);
		
		return bookshelfRepository.save(bookshelf);
	}
	
	public List<Bookshelf> retrieveUserBookshelves(User user) {
		return bookshelfRepository.findByUser(user);
	}
	
	public void clearBookshelf(Bookshelf bookshelf) {
		bookshelf.getBooks().clear();
		bookshelfRepository.save(bookshelf);
	}

	public void addBook(Bookshelf bookshelf, Book book) {
		bookshelf.addBook(book);

		bookshelfRepository.save(bookshelf);
	}
	
	public void removeBook(Bookshelf bookshelf, Book book) {
		bookshelf.removeBook(book);

		bookshelfRepository.save(bookshelf);
	}
}