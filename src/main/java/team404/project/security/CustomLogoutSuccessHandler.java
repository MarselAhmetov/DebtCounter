package team404.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import team404.project.repository.interfaces.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("SESSION")) {
                sessionRepository.deleteBySessionId(cookie.getValue());
                cookie.setMaxAge(1);
                response.addCookie(cookie);
            }
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}
