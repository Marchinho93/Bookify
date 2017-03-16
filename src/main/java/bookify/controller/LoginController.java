package bookify.controller;




import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookify.model.Administrator;
import bookify.service.AdministratorService;
import bookify.service.UserService;

@Controller
public class LoginController {
	
	Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdministratorService administratorService;
	

	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@RequestMapping(value = "/refreshCSRF", method = RequestMethod.GET)
	@ResponseBody
	public String refreshCSRF(){
		logger.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/initAdmin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Administrator data(@RequestParam("id") String id, HttpServletRequest request){
		CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
		logger.debug(token.getToken());
		return administratorService.findById(id);
	}

}
