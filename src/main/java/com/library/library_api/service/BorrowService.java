package com.library.library_api.service;

import com.library.library_api.model.Book;
import com.library.library_api.model.BorrowRecord;
import com.library.library_api.model.Student;
import com.library.library_api.repository.BookRepository;
import com.library.library_api.repository.BorrowRecordRepository;
import com.library.library_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Borrow a book
    public BorrowRecord borrowBook(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        // Check if book exists and is available
        if (book == null || student == null || !book.isAvailable()) {
            return null;
        }

        // Mark book as unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        // Create borrow record
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setStudent(student);
        record.setBorrowDate(LocalDate.now());

        return borrowRecordRepository.save(record);
    }

    // Return a book
    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId).orElse(null);

        if (record == null || record.getReturnDate() != null) {
            return null; // Record not found or already returned
        }

        // Mark book as available again
        Book book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        // Set return date
        record.setReturnDate(LocalDate.now());
        return borrowRecordRepository.save(record);
    }

    // Get all borrow records for a student
    public List<BorrowRecord> getStudentBorrowHistory(Long studentId) {
        return borrowRecordRepository.findByStudentId(studentId);
    }
}
