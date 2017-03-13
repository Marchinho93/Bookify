package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(User user){
		em.persist(user);
	}
	
	public User findById(String id){
		return em.createNamedQuery("User.findById", User.class).setParameter("id", id).getSingleResult();
	}
	
	public User findByCode(long code){
		return em.find(User.class, code);
	}
	
	public User findByNameSurname(String name, String surname){
		return em.createNamedQuery("User.findByNameSurname", User.class)
				.setParameter("name", name)
				.setParameter("surname", surname)
				.getSingleResult();
	}
	
	public List<User> findByCity(String city){
		return em.createNamedQuery("User.findByCity", User.class).setParameter("city", city).getResultList();
	}
	
	public User findByEmail(String email){
		return em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getSingleResult();
	}
	
	public List<User> findAll(){
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}
	
	@Transactional
	public void update(User user){
		em.merge(user);
	}
	
	@Transactional
	public void delete(User user){
		em.remove(user);
	}
}
