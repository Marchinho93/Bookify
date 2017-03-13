package bookify.repository;

import java.util.List;

import bookify.model.Book;
import bookify.model.Category;

public interface CategoryDao {
	
	void create(Category category);
	
	Category findByName(String name);
	
	Category findByBook(Book book);
	
	List<Category> findAll();
	
	void update(Category category);
	
	void delete(Category category);

}
