package bookify.model;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(sequenceName = "bookSeq", name = "bookSeq", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
	@NamedQuery(name = "Book.findByYear", query = "SELECT b FROM Book b WHERE b.year = :year"),
	@NamedQuery(name = "Book.findByPosition", query = "SELECT b FROM Book b WHERE b.position = :position"),
	@NamedQuery(name = "Book.findByEditor", query = "SELECT b FROM Book b WHERE b.editor = :editor"),
	@NamedQuery(name = "Book.findByCategory", query = "SELECT b FROM Book b WHERE b.category = :category"),
	@NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b JOIN b.authors a WHERE a.code = :author"),
	@NamedQuery(name = "Book.findBySerie", query = "SELECT b FROM Book b WHERE b.serie = :serie"),
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
})
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
	private long code;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	private String position;
	@Column(nullable = false)
	private String editor;


	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_code", referencedColumnName = "code")
	private Category category;

	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Author> authors;
	
	@ManyToOne()
	private Serie serie;

	public Book(){};

	public Book(String title, int year, String position, String editor, Category category, List<Author> authors, Serie serie) {
		this.title = title;
		this.year = year;
		this.position = position;
		this.editor = editor;
		this.category = category;
		this.authors = authors;
		this.serie = serie;
	}
	
	public long getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
}
