package bookify.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "loanSeq", sequenceName = "loanSeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Loan.findByBook", query = "SELECT l FROM Loan l WHERE l.book.code = :book_code"),
	@NamedQuery(name = "Loan.findByUser", query = "SELECT l FROM Loan l WHERE l.user.code = :user_code"),
	@NamedQuery(name = "Loan.laterThan", query = "SELECT l FROM Loan l WHERE l.startDate > :startDate"),
	@NamedQuery(name = "Loan.beforeThan", query = "SELECT l FROM Loan l WHERE l.startDate < :startDate"),
	@NamedQuery(name = "Loan.findByDate", query = "SELECT l FROM Loan l WHERE l.startDate = :startDate"),
	@NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l")
})
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanSeq")
	private long code;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column()
	private Date endDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "book_code", referencedColumnName = "code")
	private Book book;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_code", referencedColumnName = "code")
	private User user;
	
	public Loan(){}
	
	public Loan(Date startDate, Date endDate, Book book, User user) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.book = book;
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
