package bookify.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Administrator;
import bookify.repository.AdministratorDao;

@Service
public class AdministratorServiceImpl implements AdministratorService{
	
	@Autowired
	AdministratorDao administratorDao;
	
	public Administrator create(Administrator administrator){
		administratorDao.create(administrator);
		return administrator;
	}
	
	public Administrator create(String id, String password, String name, String surname, Date birthday, String address,
			String phone, String city, String email){
		Administrator administrator = new Administrator(id, password, name, surname, birthday, address, phone, city, email);
		administratorDao.create(administrator);
		return administrator;
	}
	
	public Administrator findById(String id){
		return administratorDao.findById(id);
	}
	
	public Administrator findByCode(long code){
		return administratorDao.findByCode(code);
	}
	
	public List<Administrator> findAll(){
		return administratorDao.findAll();
	}
	
	public Administrator update(Administrator administrator){
		administratorDao.update(administrator);
		return administrator;
	}
	
	public Administrator delete(Administrator administrator){
		administratorDao.delete(administrator);
		return administrator;
	}
}
