package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import beans.MockBookService;
import models.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private MockBookService bookService;

	@GetMapping("")
	public List<Book> getList(){
		List<Book> list = bookService.getList();
		
//		List<String> titles = list.stream()
//				.map(Book::getTitle)
//				.collect(Collectors.toList());
				
		return list;
	}
	
	@GetMapping("/addForm")
	public String getForm(){
		return "addView";
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void processPostBookForm(@RequestBody Book book){
		bookService.add(book);
	}
	
	@GetMapping("/{id}")
	public Book getList(@PathVariable Long id){
		return bookService.getById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void putBook(@PathVariable Long id, @RequestBody Book book){
		bookService.update(id, book);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteBook(@PathVariable Long id){
		this.bookService.deleteById(id);
		return "{status: deleted}";
	}
}

