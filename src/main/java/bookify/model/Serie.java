package bookify.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name = "serieSeq", sequenceName = "serieSeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Serie.findByName", query = "SELECT s FROM Serie s WHERE s.name = :name"),
	@NamedQuery(name = "Serie.findByBook", query = "SELECT s FROM Serie s JOIN s.books b WHERE b.code = :code"),
	@NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
})
public class Serie {
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "serieSeq")
	private long code;
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy="serie",cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<Book> books;
	
	public Serie(){}
	
	public Serie(String name, List<Book> books) {
		this.name = name;
		this.books = books;
	}
	
	public long getCode(){
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	

}
