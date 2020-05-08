package team404.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import team404.project.model.Session;
import team404.project.model.User;
import team404.project.model.details.UserDetailsImpl;
import team404.project.repository.interfaces.SessionRepository;
import team404.project.repository.interfaces.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component("customFilter")
public class CustomFilter extends GenericFilterBean {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    @Qualifier("userRepositoryJPAImpl")
    UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            Cookie[] cookies = httpRequest.getCookies();
            Cookie sessionCookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSION")) {
                    sessionCookie = cookie;
                }
            }
            if (sessionCookie != null) {
                Session session = sessionRepository.getBySessionId(sessionCookie.getValue());
                if (session != null) {
                    User user = userRepository.getByUsername(session.getUsername());
                    UserDetailsImpl userDetails = new UserDetailsImpl(user);
                    UsernamePasswordAuthenticationToken token
                            = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
