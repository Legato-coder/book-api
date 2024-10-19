package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookService bookService;

	// Add multiple books
	@PostMapping("/book")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) {
		List<Book> savedBooks = bookService.addBooks(books);
		return ResponseEntity.ok(savedBooks);
	}

	// Get a book by ID
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional<Book> book = bookService.getBookById(id);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Get all books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	// Update a book
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
		Book book = bookService.updateBook(id, updatedBook);
		return ResponseEntity.ok(book);
	}

	// Delete a book by ID
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
}
