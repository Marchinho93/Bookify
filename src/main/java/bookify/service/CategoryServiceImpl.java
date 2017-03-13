package bookify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Book;
import bookify.model.Category;
import bookify.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public Category create(Category category){
		categoryDao.create(category);
		return category;
	}

	public Category create(String name, String description){
		Category category = new Category(name, description);
		categoryDao.create(category);
		return category;
	}
	
	public Category findByName(String name){
		return categoryDao.findByName(name);
	}
	
	public Category findByBook(Book book){
		return categoryDao.findByBook(book);
	}
	
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
	
	public Category update(Category category){
		categoryDao.update(category);
		return category;
	}
	
	public Category delete(Category category){
		categoryDao.delete(category);
		return category;
	}

}
