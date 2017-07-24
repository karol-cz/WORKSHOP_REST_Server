package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	
	
	public Optional<Book> getById(Long id){
		
		return list.stream()
			.filter(item -> Objects.equals(item.getId(), id))
			.findFirst();
		
	}
	
	public void add(Book book){
		book.setId(nextId++);
		list.add(book);
	}
	
	public void deleteById(Long id) {
		getById(id)
		 	.ifPresent(list::remove); 
//			.ifPresent(book -> list.remove(book));
	}
	
	public void update(Long id, Book book) {
		deleteById(id);
		book.setId(id);
		list.add(book);
	}
}
