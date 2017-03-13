package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Serie;

@Repository
public class SerieDaoImpl implements SerieDao {

	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Serie serie){
		em.persist(serie);
	}
	
	public Serie findByName(String name){
		return em.createNamedQuery("Serie.findByName", Serie.class).setParameter("name", name).getSingleResult();
	}
	
	public Serie findByBook(long code){
		return em.createNamedQuery("Serie.findByBook", Serie.class).setParameter("code", code).getSingleResult();
	}
	
	public List<Serie> findAll(){
		return em.createNamedQuery("Serie.findAll", Serie.class).getResultList();
	}
	
	@Transactional
	public void update(Serie serie){
		em.merge(serie);
	}
	
	@Transactional
	public void delete(Serie serie){
		em.remove(serie);
	}
}
