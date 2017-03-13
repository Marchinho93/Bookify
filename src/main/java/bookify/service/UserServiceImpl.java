package bookify.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.User;
import bookify.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao	userDao;

	public User create(User user){
		userDao.create(user);
		return user;
	}
	
	public User create(String id, String password, String name, String surname, Date birthday, Date subscription,
			String address, String phone, String city, String email){
		User user = new User(id, password, name, surname, birthday, subscription, address, phone, city, email);
		userDao.create(user);
		return user;
	}
	
	public User findById(String id){
		return userDao.findById(id);
	}
	
	public User findByCode(long code){
		return userDao.findByCode(code);
	}
	
	public User findByNameSurname(String name, String surname){
		return userDao.findByNameSurname(name, surname);
	}
	
	public List<User> findByCity(String city){
		return userDao.findByCity(city);
	}
	
	public User findByEmail(String email){
		return userDao.findByEmail(email);
	}
	
	public List<User> findAll(){
		return userDao.findAll();
	}
	
	public User update(User user){
		userDao.update(user);
		return user;
	}
	
	public User delete(User user){
		userDao.delete(user);
		return user;
	}
	
	
}
