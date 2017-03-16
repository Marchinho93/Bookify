package bookify.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"bookify.security"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	BookifyUserDetailsService bookifyUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 auth.userDetailsService(bookifyUserDetailsService);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		 .loginPage("/")
		 .loginProcessingUrl("/login")
		 .usernameParameter("id")
		 .passwordParameter("password")
		 .permitAll()
		 .successHandler(new BookifyLoginSuccessHandler())
		 .failureHandler(new BookifyLoginFailureHandler())
		.and()
		.httpBasic()
		.and()
		.logout()
		 .logoutUrl("/logout")
		 .logoutSuccessUrl("/")
		 .invalidateHttpSession(true)
		.and()
		.authorizeRequests()
		 .antMatchers("/css/**", "/bookify/**", "/view/**")
		 .permitAll()
		 .anyRequest()
		 .authenticated()
		 .and()
		.csrf()
		 .csrfTokenRepository(csrfTokenRepository())
		 .ignoringAntMatchers("/refreshCSRF")
		.and()
		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		 
	}
	
	@Bean
	public CsrfTokenRepository csrfTokenRepository(){
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}



}
