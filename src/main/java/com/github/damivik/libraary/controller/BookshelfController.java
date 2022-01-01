package com.github.damivik.libraary.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.github.damivik.libraary.entity.Book;
import com.github.damivik.libraary.entity.Bookshelf;
import com.github.damivik.libraary.entity.User;
import com.github.damivik.libraary.exception.BookNotFoundException;
import com.github.damivik.libraary.exception.BookshelfNotFoundException;
import com.github.damivik.libraary.service.BookService;
import com.github.damivik.libraary.service.BookshelfService;

@Controller
public class BookshelfController {

	private BookshelfService bookshelfService;
	private BookService bookService;

	public BookshelfController(BookshelfService bookshelfService) {
		this.bookshelfService = bookshelfService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/")
	public String index(Authentication authentication, Model model) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("bookshelves", bookshelfService.retrieveUserBookshelves(user));

		return "index";
	}

	@PostMapping("/addShelf")
	public String addShelf(Authentication authentication, @RequestParam @Valid @NotBlank @Size(max = 255) String name) {
		User user = (User) authentication.getPrincipal();
		bookshelfService.create(user, name);

		return "redirect:/";
	}

	@GetMapping("/bookshelves/{bookshelfId}")
	public String displayBookshelf(Authentication authentication, @PathVariable long bookshelfId, Model model) {
		Bookshelf bookshelf;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(bookshelfId);
		} catch (BookshelfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		model.addAttribute("bookshelf", bookshelf);
		
		return "bookshelf";
	}

	@PostMapping("/bookshelves/{bookshelfId}/rename")
	public String rename(@PathVariable long bookshelfId, @RequestParam @Valid @NotBlank @Size(max = 255) String name) {
		Bookshelf bookshelf;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(bookshelfId);
		} catch (BookshelfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		bookshelfService.renameBookshelf(bookshelf, name);

		return "redirect:/bookshelves/" + bookshelfId;
	}

	@PostMapping("/bookshelves/{bookshelfId}/delete")
	public String delete(@PathVariable long bookshelfId) {
		Bookshelf bookshelf;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(bookshelfId);
		} catch (BookshelfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		bookshelfService.deleteBookshelf(bookshelf);

		return "redirect:/";
	}

	@PostMapping("/bookshelves/{bookshelfId}/clear")
	public String clear(@PathVariable Long bookshelfId) {
		Bookshelf bookshelf;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(bookshelfId);
		} catch (BookshelfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		bookshelfService.clearBookshelf(bookshelf);

		return "redirect:/bookshelves/" + bookshelfId;
	}

	@PostMapping("/addBook")
	public String addBook(@RequestBody Map<String, Integer> data) {
		Bookshelf bookshelf;
		Book book;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(data.get("bookshelfId"));
			book = bookService.retrieve(data.get("bookId"));
		} catch (BookshelfNotFoundException | BookNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		bookshelfService.addBook(bookshelf, book);

		return "redirect:/bookshelves/" + data.get("bookshelfId");
	}

	@PostMapping("/bookshelves/{bookshelfId}/removeBook")
	public String removeBook(@PathVariable long bookshelfId, @RequestParam long bookId) {
		Bookshelf bookshelf;
		Book book;
		try {
			bookshelf = bookshelfService.retrieveBookshelf(bookshelfId);
			book = bookService.retrieve(bookId);
		} catch (BookshelfNotFoundException | BookNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		bookshelfService.removeBook(bookshelf, book);

		return "redirect:/bookshelves/" + bookshelfId;
	}

}