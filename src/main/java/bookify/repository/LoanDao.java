package bookify.repository;

import java.util.Date;
import java.util.List;

import bookify.model.Loan;

public interface LoanDao {

	void create(Loan loan);

	Loan findByCode(long code);

	List<Loan> findByBook(long code);

	List<Loan> findByUser(long code);

	List<Loan> laterThan(Date date);

	List<Loan> beforeThan(Date date);

	List<Loan> findByDate(Date date);

	List<Loan> findAll();

	void update(Loan loan);

	void delete(Loan loan);

}