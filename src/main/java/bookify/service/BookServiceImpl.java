package bookify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Serie;
import bookify.repository.BookDao;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookDao bookDao;
	
	public Book create(Book book){
		bookDao.create(book);
		return book;
	}
	
	public Book create(String title, int year, String position, String editor, Category category, List<Author> authors, Serie serie){
		Book book  = new Book(title, year, position, editor, category, authors, serie);
		bookDao.create(book);
		return book;
	}
	
	public Book findByCode(long code){
		return bookDao.findByCode(code);
	}
	
	public List<Book> findByTitle(String title){
		return bookDao.findByTitle(title);
	}
	
	public List<Book> findByYear(int year){
		return bookDao.findByYear(year);
	}
	
	public List<Book> findByPosition(String position){
		return bookDao.findByPosition(position);
	}
	
	public List<Book> findByEditor(String editor){
		return bookDao.findByEditor(editor);
	}
	
	public List<Book> findByCategory(Category category){
		return bookDao.findByCategory(category);
	}
	
	public List<Book> findByAuthor(Author author){
		return bookDao.findByAuthor(author);
	}
	
	public List<Book> findBySerie(Serie serie){
		return bookDao.findBySerie(serie);
	}
	
	public List<Book> findAll(){
		return bookDao.findAll();
	}
	
	public Book update(Book book){
		bookDao.update(book);
		return book;
	}
	
	public Book delete(Book book){
		bookDao.delete(book);
		return book;
	}

}
