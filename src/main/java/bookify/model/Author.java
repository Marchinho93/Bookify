package bookify.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "authorSeq", sequenceName = "authorSeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Author.findByNameSurname", query = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname"),
	@NamedQuery(name = "Author.findByBook", query = "SELECT a FROM Book b JOIN b.authors a WHERE b.title = :title"),
	@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
})
@Embeddable
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorSeq")
	private long code;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	
	public Author(){};
	
	public Author(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
