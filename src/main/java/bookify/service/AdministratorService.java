package bookify.service;

import java.util.Date;
import java.util.List;

import bookify.model.Administrator;

public interface AdministratorService {

	Administrator create(Administrator administrator);
	
	Administrator create(String id, String password, String name, String surname, Date birthday, String address,
			String phone, String city, String email);

	Administrator findById(String id);

	Administrator findByCode(long code);

	List<Administrator> findAll();

	Administrator update(Administrator administrator);

	Administrator delete(Administrator administrator);

}