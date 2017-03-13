package bookify.repository;

import java.util.List;

import bookify.model.Author;
import bookify.model.Book;

public interface AuthorDao {

	void create(Author author);

	Author findByNameSurname(String name, String surname);

	Author findByBook(Book book);

	List<Author> findAll();

	void update(Author author);

	void delete(Author author);

}