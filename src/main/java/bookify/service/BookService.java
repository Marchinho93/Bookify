package bookify.service;

import java.util.List;

import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Serie;

public interface BookService {
	
	public Book create(Book book);
	
	public Book create(String title, int year, String position, String editor, Category category, List<Author> authors, Serie serie);
	
	public Book findByCode(long code);
	
	public List<Book> findByTitle(String title);
	
	public List<Book> findByYear(int year);
	
	public List<Book> findByPosition(String position);
	
	public List<Book> findByEditor(String editor);
	
	public List<Book> findByCategory(Category category);
	
	public List<Book> findByAuthor(Author author);
	
	public List<Book> findBySerie(Serie serie);
	
	public List<Book> findAll();
	
	public Book update(Book book);
	
	public Book delete(Book book);

}
