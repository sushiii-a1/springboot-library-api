package com.library.library_api.controller;

import com.library.library_api.model.BorrowRecord;
import com.library.library_api.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // POST http://localhost:8080/api/borrow?bookId=1&studentId=1
    @PostMapping
    public BorrowRecord borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long studentId) {
        return borrowService.borrowBook(bookId, studentId);
    }

    // PUT http://localhost:8080/api/borrow/return/1
    @PutMapping("/return/{borrowRecordId}")
    public BorrowRecord returnBook(@PathVariable Long borrowRecordId) {
        return borrowService.returnBook(borrowRecordId);
    }

    // GET http://localhost:8080/api/borrow/student/1
    @GetMapping("/student/{studentId}")
    public List<BorrowRecord> getStudentHistory(@PathVariable Long studentId) {
        return borrowService.getStudentBorrowHistory(studentId);
    }
}