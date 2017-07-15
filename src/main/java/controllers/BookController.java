package controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.MockBookService;
import models.Book;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private MockBookService bookService;

	@GetMapping("/all")
	public List<Book> getList(){
		List<Book> list = this.bookService.getList();
		return list;
	}
	
	@GetMapping("/{id}")
	public Book getList(@PathVariable Long id){
		return this.bookService.getById(id);
	}
	
	@PostMapping("")
	public String postBook(HttpServletRequest request){
		try {
			String bookJson = request.getReader().lines() 
					.collect(Collectors.joining());
			Book book = new ObjectMapper().readValue(bookJson, Book.class); 
			this.bookService.add(book);
			return "{status: ok}";
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{status: error}";
	}
	
	@PutMapping("/{id}")
	public String putBook(@PathVariable Long id, HttpServletRequest request){ 
		try {
			String bookJson = request.getReader().lines().collect(Collectors.joining());
			Book book = new ObjectMapper().readValue(bookJson, Book.class);
			this.bookService.update(id, book);
			return "{status: ok}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{status: error}";
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Long id){
		this.bookService.deleteById(id);
		return "{status: deleted}";
	}
}
