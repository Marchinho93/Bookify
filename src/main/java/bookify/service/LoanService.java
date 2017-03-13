package bookify.service;

import java.util.Date;
import java.util.List;

import bookify.model.Book;
import bookify.model.Loan;
import bookify.model.User;

public interface LoanService {

	Loan create(Loan loan);

	Loan create(Date startDate, Date endDate, Book book, User user);

	Loan findByCode(long code);

	List<Loan> findByBook(long code);

	List<Loan> findByUser(long code);

	List<Loan> laterThan(Date date);

	List<Loan> beforeThan(Date date);

	List<Loan> findByDate(Date date);

	List<Loan> findAll();

	Loan update(Loan loan);

	Loan delete(Loan loan);

}