package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import models.Book;

@Component
public class MockBookService {
	
	private List<Book> list;
	private Long nextId;
	
	public MockBookService() {
		if(1>0)
		list = new ArrayList<>();
		list.add(new Book(1L, "9788324631766", "Thiniking in Java", "Bruce Eckel", "Helion", "programming"));
		list.add(new Book(2L, "9788324627738", "Rusz glowa Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
		list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming"));
		this.nextId=4L;
	}
	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}
	
	public Book getById(Long id){
		Optional<Book> result = list.stream()
			     .filter(item -> item.getId() == id)
			     .findFirst();
		
		return result.get();
	}
	
	public void add(Book book){
		book.setId(this.nextId++);
		this.list.add(book);
	}
	public void deleteById(Long id) {
		Book book = this.getById(id);
		
		this.list.remove(book);
	}
	public void update(Long id, Book book) {
		book.setId(id);
		Book toUpdate = this.getById(id);
		this.list.remove(toUpdate);
		this.list.add(book);
	}
}
