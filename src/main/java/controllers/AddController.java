package controllers;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.MockBookService;
import models.Book;

@Controller
@RequestMapping("/book")
public class AddController {
	@Autowired
	private MockBookService bookService;

	@GetMapping("/addForm")
	public String getForm(){
		return "/WEB-INF/addView.jsp";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String processPostBookForm(HttpServletRequest request){
		try {
			Map<String, String> fieldMap = request.getParameterMap() 
					.entrySet()
					.stream() 
					.collect(
							Collectors.toMap( 
									Map.Entry::getKey, 
									e -> e.getValue()[0])); 
			
			ObjectMapper mapper = new ObjectMapper(); 
			String bookJson = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(fieldMap);

			Book book = mapper.readValue(bookJson, Book.class); 
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
}
