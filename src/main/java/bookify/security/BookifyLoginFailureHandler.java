package bookify.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookifyLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authExc)
			throws IOException, ServletException {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			writer.write(mapper.writeValueAsString(authExc.getMessage()));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			writer.flush();
			writer.close();
	}

}
