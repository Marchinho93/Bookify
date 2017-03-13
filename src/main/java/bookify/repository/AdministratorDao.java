package bookify.repository;

import java.util.List;

import bookify.model.Administrator;

public interface AdministratorDao {

	void create(Administrator administrator);

	Administrator findById(String id);

	Administrator findByCode(long code);

	List<Administrator> findAll();

	void update(Administrator administrator);

	void delete(Administrator administrator);

}