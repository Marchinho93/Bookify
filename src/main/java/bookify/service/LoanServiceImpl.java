package bookify.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookify.model.Book;
import bookify.model.Loan;
import bookify.model.User;
import bookify.repository.LoanDao;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDao loanDao;
	
	public Loan create(Loan loan){
		loanDao.create(loan);
		return loan;
	}
	
	public Loan create(Date startDate, Date endDate, Book book, User user){
		Loan loan = new Loan(startDate, endDate, book, user);
		loanDao.create(loan);
		return loan;
	}

	public Loan findByCode(long code){
		return loanDao.findByCode(code);
	}

	public List<Loan> findByBook(long code){
		return loanDao.findByBook(code);
	}

	public List<Loan> findByUser(long code){
		return loanDao.findByUser(code);
	}

	public List<Loan> laterThan(Date date){
		return loanDao.laterThan(date);
	}

	public List<Loan> beforeThan(Date date){
		return loanDao.beforeThan(date);
	}

	public List<Loan> findByDate(Date date){
		return loanDao.findByDate(date);
	}

	public List<Loan> findAll(){
		return loanDao.findAll();
	}

	public Loan update(Loan loan){
		loanDao.update(loan);
		return loan;
	}

	public Loan delete(Loan loan){
		loanDao.delete(loan);
		return loan;
	}
}
