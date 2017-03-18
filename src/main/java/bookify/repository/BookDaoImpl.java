package bookify.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Serie;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Book book) {
		em.persist(book);		
	}
	
	public Book findByCode(long code){
		return em.find(Book.class, code);
	}
	
	public List<Book> findByTitle(String title){
		return em.createNamedQuery("Book.findByTitle", Book.class).setParameter("title", title).getResultList();
	}
	
	public List<Book> findByYear(int year){
		return em.createNamedQuery("Book.findByYear", Book.class).setParameter("year", year).getResultList();
	}
	
	public List<Book> findByPosition(String position){
		return em.createNamedQuery("Book.findByPosition", Book.class).setParameter("position", position).getResultList();
	}
	
	public List<Book> findByEditor(String editor){
		return em.createNamedQuery("Book.findByEditor", Book.class).setParameter("editor", editor).getResultList();
	}
	
	public List<Book> findByCategory(Category category){
		return em.createNamedQuery("Book.findByCategory", Book.class).setParameter("category", category.getCode()).getResultList();
	}
	
	public List<Book> findByAuthor(Author author){
		return em.createNamedQuery("Book.findByAuthor", Book.class).setParameter("author", author.getCode()).getResultList();
	}
	
	public List<Book> findBySerie(Serie serie){
		return em.createNamedQuery("Book.findBySerie", Book.class).setParameter("serie", serie.getCode()).getResultList();
	}
	
	@Transactional
	public List<Book> findAll(){
		return em.createNamedQuery("Book.findAll", Book.class).getResultList();
	}
	
	@Transactional
	public void update(Book book){
		em.merge(book);
	}
	
	@Transactional
	public void delete(Book book){
		em.remove(book);
	}

}
