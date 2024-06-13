package com.example.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.model.Books;
import com.example.login.model.Input;
import com.example.login.repository.BookRepo;

@Service
public class BooksService {

    @Autowired
    private BookRepo bookRepo;

    public List<Books> getAllbooks() {
        return bookRepo.findAll();
    }

    public Books getBookById(long bookid) {
        return bookRepo.findById(bookid);
    }

    public void delById(long bookid) {
        Books b = bookRepo.findById(bookid);
        bookRepo.delete(b);
    }

    public Books save(Input books) {
        Books b = new Books();
        b.setBookName(books.getBookName());
        b.setAuthorName(books.getAuthorName());
        b.setLaunchDate(books.getLaunchDate().getTime()); 
        b.setAboutBook(books.getAboutBook());
        return bookRepo.save(b);
    }

    public Books update(Input books, long id) {
        Books b = bookRepo.findById(id);
        b.setAboutBook(books.getAboutBook());
        b.setBookName(books.getBookName());
        b.setAuthorName(books.getAuthorName());
        b.setLaunchDate(books.getLaunchDate().getTime()); 
        return bookRepo.save(b);
    }
}
