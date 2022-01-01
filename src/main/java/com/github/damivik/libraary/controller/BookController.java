package com.github.damivik.libraary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.damivik.libraary.entity.User;
import com.github.damivik.libraary.service.BookService;
import com.github.damivik.libraary.service.BookshelfService;

@Controller
public class BookController {
	private BookService bookService;
	private BookshelfService bookshelfService;
	
	public BookController(BookService bookService, BookshelfService bookshelfService) {
		this.bookService = bookService;
		this.bookshelfService = bookshelfService;
	}
	
	@GetMapping("/books/search")
	public String search(Authentication authentication, String title, Model model) {
		model.addAttribute("books", bookService.search(title));
		User user = (User) authentication.getPrincipal();
		model.addAttribute("bookshelves", bookshelfService.retrieveUserBookshelves(user));
		return "books_search";
	}
	
}