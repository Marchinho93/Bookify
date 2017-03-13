package bookify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Author;
import bookify.model.Book;
import bookify.repository.AuthorDao;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	public Author create(Author author) {
		authorDao.create(author);
		return author;
	}
	
	public Author create(String name, String surname){
		Author author = new Author(name, surname);
		authorDao.create(author);
		return author;
	}
	
	public Author findByNameSurname(String name, String surname){
		return authorDao.findByNameSurname(name, surname);
	}
	
	public Author findByBook(Book book){
		return authorDao.findByBook(book);
	}
	
	public List<Author> findAll(){
		return authorDao.findAll();
	}
	
	public Author update(Author author){
		authorDao.update(author);
		return author;
	}
	
	public Author delete(Author author){
		authorDao.delete(author);
		return author;
	}

}
