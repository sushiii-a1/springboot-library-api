package com.library.library_api.service;

import com.library.library_api.model.Book;
import com.library.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service  // Tells Spring this is a service class
public class BookService {

    @Autowired  // Spring automatically injects the repository
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get one book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update existing book
    public Book updateBook(Long id, Book updatedBook) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setGenre(updatedBook.getGenre());
            existing.setAvailable(updatedBook.isAvailable());
            return bookRepository.save(existing);
        }
        return null;
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Search books
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    public List<Book> searchByGenre(String genre) {
        return bookRepository.findByGenreContaining(genre);
    }
}