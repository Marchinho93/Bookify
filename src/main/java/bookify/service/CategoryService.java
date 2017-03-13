package bookify.service;

import java.util.List;

import bookify.model.Book;
import bookify.model.Category;

public interface CategoryService {
	
	Category create(Category category);

	Category create(String name, String description);

	Category findByName(String name);

	Category findByBook(Book book);

	List<Category> findAll();

	Category update(Category category);

	Category delete(Category category);

}