package bookify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "categorySeq", sequenceName = "categorySeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
	@NamedQuery(name = "Category.findByBook", query = "SELECT c FROM Book b JOIN b.category c WHERE b.title = :title"),
	@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
})
public class Category {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeq")
	@Id
	private long code;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String description;
	
	public Category(){};
	
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	
	
	

}
