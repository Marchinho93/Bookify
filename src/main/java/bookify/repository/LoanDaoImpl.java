package bookify.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookify.model.Loan;

@Repository
public class LoanDaoImpl implements LoanDao {

	@Autowired
	@PersistenceContext(unitName = "models-unit")
	EntityManager em;
	
	@Transactional
	public void create(Loan loan){
		em.persist(loan);
	}
	
	public Loan findByCode(long code){
		return em.find(Loan.class, code);
	}
	
	public List<Loan> findByBook(long code){
		return em.createNamedQuery("Loan.findByBook", Loan.class).setParameter("book_code", code).getResultList();
	}
	
	public List<Loan> findByUser(long code){
		return em.createNamedQuery("Loan.findByUser", Loan.class).setParameter("user_code", code).getResultList();
	}
	
	public List<Loan> laterThan(Date date){
		return em.createNamedQuery("Loan.laterThan", Loan.class).setParameter("startDate", date, TemporalType.DATE).getResultList();
	}
	
	public List<Loan> beforeThan(Date date){
		return em.createNamedQuery("Loan.beforeThan", Loan.class).setParameter("startDate", date, TemporalType.DATE).getResultList();
	}
	
	public List<Loan> findByDate(Date date){
		return em.createNamedQuery("Loan.findByDate", Loan.class).setParameter("startDate", date, TemporalType.DATE).getResultList();
	}
	
	public List<Loan> findAll(){
		return em.createNamedQuery("Loan.findAll", Loan.class).getResultList();
	}
	
	@Transactional
	public void update(Loan loan){
		em.merge(loan);
	}
	
	@Transactional
	public void delete(Loan loan){
		em.remove(loan);
	}
}
