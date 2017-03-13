package bookify.controller;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bookify.service.UserService;

@Controller
@Scope(value="session")
public class LoginController {
	
	Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	UserService userservice;

	
	
	@RequestMapping("/data")
	public @ResponseBody String data(){
		return "ok";
	}

}
