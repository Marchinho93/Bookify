package bookify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bookify.model.Administrator;
import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Serie;
import bookify.service.AdministratorService;
import bookify.service.AuthorService;
import bookify.service.BookService;
import bookify.service.CategoryService;
import bookify.service.SerieService;

@Controller
public class AdminController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private BookService bookService;
	

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SerieService serieService;


	@RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
	public @ResponseBody Administrator editAdmin(@RequestBody Administrator data){
		return administratorService.update(data);
	}

	@RequestMapping(value = "/findAllBooks", method = RequestMethod.GET)
	public @ResponseBody List<Book> findAllBooks(){
		return bookService.findAll();
	}

	@RequestMapping(value = "/findAllAuthors", method = RequestMethod.GET)
	public @ResponseBody List<Author> findAllAuthors(){
		return authorService.findAll();
	}

	@RequestMapping(value = "/findAllCategories", method = RequestMethod.GET)
	public @ResponseBody List<Category> findAllCategories(){
		return categoryService.findAll();
	}

	@RequestMapping(value = "/findAllSeries", method = RequestMethod.GET)
	public @ResponseBody List<Serie> findAllSeries(){
		return serieService.findAll();
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public @ResponseBody Book addBook(@RequestBody Book data){
		return bookService.create(data.getTitle(), data.getYear(), data.getPosition(), data.getEditor(), data.getCategory(), data.getAuthors(), data.getSerie());
		
	}
}
