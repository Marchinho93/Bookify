

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bookify.model.Administrator;
import bookify.model.Author;
import bookify.model.Book;
import bookify.model.Category;
import bookify.model.Loan;
import bookify.model.Serie;
import bookify.model.User;
import bookify.service.UserService;

public class Main {
	public static void main(String[] args) {
	
		Logger l = LogManager.getLogger(Main.class);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("models-unit");
		EntityManager em = emf.createEntityManager();
		
		
		
		Category c1 = new Category("Avventura", "Libri belli molto");
		Author a1 = new Author("marco", "br");
		List<Author> al1 = new ArrayList<>();
		al1.add(a1);
		Book b1 = new Book("Ciao", 2010, "F2", "lemure", c1, al1, null);
		List<Book> bl1 = new ArrayList<>();
		bl1.add(b1);
		Serie s1 = new Serie("cavalli", bl1);
		b1.setSerie(s1);
		
		Administrator adm1 = new Administrator("peppe", "ficco", "mario", "biondi", new Date(10,11,1993), "via lepre", "322324242", "roma" , "asdawd@yahoo.it");
		User u1 = new User("pino", "daniele", "pino", "daniele", new Date(2,12,1960), new Date(10,11,1993), "via lepre", "322324242", "roma" , "asdawd@yahoo.it");
		Loan l1 = new Loan(new Date(3,3,2017), null, b1, u1);
		em.getTransaction().begin();
		l.trace("in");
		em.persist(s1);
		em.persist(adm1);
		em.persist(l1);
		l.debug("?");
		em.getTransaction().commit();
		
	
		System.out.println(em.createNamedQuery("User.findById", User.class).setParameter("id", "pino").getSingleResult().getId());
		em.close();
		emf.close();
	}

}
