package team404.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import team404.project.model.Session;
import team404.project.repository.interfaces.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie sessionCookie = new Cookie("SESSION", UUID.randomUUID().toString());
        sessionCookie.setMaxAge(-1);
        response.addCookie(sessionCookie);
        Session session = Session.builder()
                .username(authentication.getName())
                .sessionId(sessionCookie.getValue())
                .localDateTime(LocalDateTime.now())
                .build();
        sessionRepository.save(session);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
