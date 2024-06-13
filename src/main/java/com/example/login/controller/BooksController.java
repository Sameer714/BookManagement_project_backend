package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.Books;
import com.example.login.model.Input;
import com.example.login.service.BooksService;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class BooksController {
	
	@Autowired
	BooksService booksService;

	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Books> getAllbooks() {
		return booksService.getAllbooks();
	}

	@GetMapping("/getbyid/{bookid}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Books getBooks(@PathVariable long bookid) {
		return booksService.getBookById(bookid);
	}

	@DeleteMapping("/deletebyId/{bookid}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteBook(@PathVariable long bookid) {
		booksService.delById(bookid);
	}

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Books savebook(@RequestBody Input books) {
		return booksService.save(books);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Books update(@RequestBody Input books, @PathVariable long id) {
		return booksService.update(books, id);
	}
}