package bookify.service;

import java.util.List;

import bookify.model.Book;
import bookify.model.Serie;

public interface SerieService {

	Serie create(Serie serie);

	Serie create(String name, List<Book> books);

	Serie findByName(String name);

	Serie findByBook(long code);

	List<Serie> findAll();

	Serie update(Serie serie);

	Serie delete(Serie serie);

}