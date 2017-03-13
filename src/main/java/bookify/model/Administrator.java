package bookify.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "administratorSeq", sequenceName = "administratorSeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Administrator.findById", query="SELECT a FROM Administrator a WHERE a.id = :id"),
	@NamedQuery(name = "Administrator.findAll", query="SELECT a FROM Administrator a")
})
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "administratorSeq")
	private long code;
	@Column(nullable = false, unique = true)
	private String id;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date birthday;
	
	public Administrator(){}
	
	public Administrator(String id, String password, String name, String surname, Date birthday) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
	
}
