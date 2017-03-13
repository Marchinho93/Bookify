package bookify.repository;

import java.util.List;

import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Serie;

public interface BookDao {
	
	void create(Book book);
	
	Book findByCode(long code);
	
	List<Book> findByTitle(String title);
	
	List<Book> findByYear(int year);
	
	List<Book> findByPosition(String position);
	
	List<Book> findByEditor(String editor);
	
	List<Book> findByCategory(Category category);
	
	List<Book> findByAuthor(Author author);

	List<Book> findBySerie(Serie serie);
	
	List<Book> findAll();
	
	void update(Book book);
	
	void delete(Book book);

}
