package bookify.repository;

import java.util.List;

import bookify.model.User;

public interface UserDao {

	void create(User user);

	User findById(String id);

	User findByCode(long code);

	User findByNameSurname(String name, String surname);

	List<User> findByCity(String city);

	User findByEmail(String email);

	List<User> findAll();

	void update(User user);

	void delete(User user);

}