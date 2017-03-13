package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Author;
import bookify.model.Book;

@Repository
public class AuthorDaoImpl implements AuthorDao {
	
	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Author author){
		em.persist(author);
	}
	
	public Author findByNameSurname(String name, String surname){
		return em.createNamedQuery("Author.findByNameSurname", Author.class)
			.setParameter("name", name)
			.setParameter("surname", surname)
			.getSingleResult();
	}
	
	public Author findByBook(Book book){
		return em.createNamedQuery("Author.findByBook", Author.class).setParameter("title", book.getTitle()).getSingleResult();
	}
	
	public List<Author> findAll(){
		return em.createNamedQuery("Author.findAll", Author.class).getResultList();
	}
	
	@Transactional
	public void update(Author author){
		em.merge(author);
	}

	@Transactional
	public void delete(Author author){
		em.remove(author);
	}

}
