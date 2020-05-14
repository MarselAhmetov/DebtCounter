package team404.project.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("langFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LangFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String string = httpRequest.getParameter("lang");
        if (string != null) {
            switch (string) {
                case "ru":
                    ((HttpServletResponse) servletResponse).addCookie(new Cookie("lang", "ru"));
                    break;
                case "en":
                    ((HttpServletResponse) servletResponse).addCookie(new Cookie("lang", "en"));
                    break;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}