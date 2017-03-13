package bookify.service;

import java.util.List;

import bookify.model.Author;
import bookify.model.Book;

public interface AuthorService {
	
	Author create(Author author);

	Author create(String name, String surname);

	Author findByNameSurname(String name, String surname);

	Author findByBook(Book book);

	List<Author> findAll();

	Author update(Author author);

	Author delete(Author author);

}