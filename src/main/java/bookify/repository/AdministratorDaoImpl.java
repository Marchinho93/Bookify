package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Administrator;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
	
	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Administrator administrator){
		em.persist(administrator);
	}
	
	public Administrator findById(String id){
		return em.createNamedQuery("Administrator.findById", Administrator.class).setParameter("id", id).getSingleResult();
	}
	
	public Administrator findByCode(long code){
		return em.find(Administrator.class, code);
	}
	
	public List<Administrator> findAll(){
		return em.createNamedQuery("Administrator.findAll", Administrator.class).getResultList();
	}
	
	@Transactional
	public void update(Administrator administrator){
		em.merge(administrator);
	}
	
	@Transactional
	public void delete(Administrator administrator){
		em.remove(administrator);
	}

}
