package bookify.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import bookify.model.LoggedUser;

@Component
public class BookifyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		String id = auth.getName();
		List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) auth.getAuthorities();
		
		List<String> roleList = new ArrayList<>();
		for(SimpleGrantedAuthority g:roles)
			roleList.add(g.getAuthority());
		
		LoggedUser user = new LoggedUser(id, roleList);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		writer.write(mapper.writeValueAsString(user));
		response.setStatus(HttpServletResponse.SC_OK);
		writer.flush();
		writer.close();
		
	}


}
