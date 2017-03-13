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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "userSeq", sequenceName = "userSeq", initialValue = 1, allocationSize = 1)
@Table(name = "\"user\"")
@NamedQueries({
	@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.findByNameSurname", query = "SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname"),
	@NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	private long code;
	@Column(nullable = false)
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
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date subscription;
	@Column
	private String address;
	@Column
	private String phone;
	@Column
	private String city;
	@Column(nullable = false)
	private String email;
	
	public User(){}
	
	public User(String id, String password, String name, String surname, Date birthday, Date subscription,
			String address, String phone, String city, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.subscription = subscription;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.email = email;
	}

	public long getCode() {
		return code;
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
	
	public Date getSubscription() {
		return subscription;
	}

	public void setSubscription(Date subscription) {
		this.subscription = subscription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
