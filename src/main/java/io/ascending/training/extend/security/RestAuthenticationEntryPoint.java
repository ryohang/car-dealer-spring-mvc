package io.ascending.training.extend.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created By.
 * User: hanqinghang
 */

@Component( "restAuthenticationEntryPoint" )
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
	    response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
    }

}









