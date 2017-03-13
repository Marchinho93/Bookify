package bookify.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import bookify.model.Administrator;
import bookify.service.AdministratorService;
import bookify.service.UserService;

@Component
public class BookifyUserDetailsService implements UserDetailsService {
	
	Logger logger = LogManager.getLogger(BookifyUserDetailsService.class);
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		bookify.model.User u = null;
		Administrator a = null;
		
		try{
			u = userService.findById(id);
		}catch(NoResultException e){
			try{
				a = administratorService.findById(id);
			}catch(NoResultException e2){
				throw e2;
			}
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		User user;
		
		if(a!=null){
			GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
			authorities.add(authority);
			user = new User(a.getId(), a.getPassword(), true, true, true, true, authorities);
		}
		else if(u!=null){
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			authorities.add(authority);
			user = new User(u.getId(), u.getPassword(), true, true, true, true, authorities);
		}
		else{
			user = null;
			throw new UsernameNotFoundException(id);
		}
		
		return user;
		
	}

	

}
