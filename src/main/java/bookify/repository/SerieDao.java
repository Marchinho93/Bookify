package bookify.repository;

import java.util.List;

import bookify.model.Serie;

public interface SerieDao {

	void create(Serie serie);

	Serie findByName(String name);

	Serie findByBook(long code);

	List<Serie> findAll();

	void update(Serie serie);

	void delete(Serie serie);

}