package bookify.controller;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookify.model.Administrator;
import bookify.service.AdministratorService;
import bookify.service.UserService;

@Controller
@Scope(value="session")
public class LoginController {
	
	Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdministratorService administratorService;

	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/initAdmin", method = RequestMethod.POST)
	public @ResponseBody Administrator data(@RequestParam("id") String id){
		return administratorService.findById(id);
	}

}
