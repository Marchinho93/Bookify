package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Book;
import bookify.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Category category){
		em.persist(category);
	}
	
	public Category findByName(String name){
		return em.createNamedQuery("Category.findByName", Category.class).setParameter("name", name).getSingleResult();
	}
	
	public Category findByBook(Book book){
		return em.createNamedQuery("Category.findByBook", Category.class).setParameter("title", book.getTitle()).getSingleResult();
	}
	
	public List<Category> findAll(){
		return em.createNamedQuery("Category.findAll", Category.class).getResultList();
	}
	
	@Transactional
	public void update(Category category){
		em.merge(category);
	}
	
	@Transactional
	public void delete(Category category){
		em.remove(category);
	}


}
