package com.github.damivik.libraary.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Author> authors;
	
	@Column(length = 2000)
	private String description;
	
	private int pages;
	
	private LocalDate published;
	
	private String publisher; 
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Category> categories;
	
	private String coverImage;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private String isbn;
	
	@ManyToMany(mappedBy = "books")
	public List<Bookshelf> bookshelves;
	
	public Book() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public String getAuthorsAsString() {
		String authorsString = "";
		if (authors.size() > 0) {
			authorsString = authors.get(0).getName();
		}
		for (int i = 1; i < authors.size(); i++) {
			authorsString = authorsString + ", " + authors.get(i).getName();
		}
		return authorsString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public LocalDate getPublished() {
		return published;
	}

	public void setPublished(LocalDate published) {
		this.published = published;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		categories.add(category);
		category.getBooks().add(this);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
		category.getBooks().remove(this);
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Bookshelf> getBookshelves() {
		return bookshelves;
	}

	public void setBookshelves(List<Bookshelf> bookshelves) {
		this.bookshelves = bookshelves;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
}