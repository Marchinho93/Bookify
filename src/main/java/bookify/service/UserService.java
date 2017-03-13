package bookify.service;

import java.util.Date;
import java.util.List;

import bookify.model.User;

public interface UserService {

	User create(User user);

	User create(String id, String password, String name, String surname, Date birthday, Date subscription,
			String address, String phone, String city, String email);

	User findById(String id);

	User findByCode(long code);

	User findByNameSurname(String name, String surname);

	List<User> findByCity(String city);

	User findByEmail(String email);

	List<User> findAll();

	User update(User user);

	User delete(User user);

}