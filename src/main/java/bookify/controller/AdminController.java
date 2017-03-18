package bookify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bookify.model.Administrator;
import bookify.model.Book;
import bookify.service.AdministratorService;
import bookify.service.BookService;

@Controller
public class AdminController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private BookService bookService;

	
	@RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
	public @ResponseBody Administrator editAdmin(@RequestBody Administrator data){
		return administratorService.update(data);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<Book> findAll(){
		return bookService.findAll();
	}
}
