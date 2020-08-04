package com.example.demo.components;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {


    private JwtTkenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTkenProvider jwtTokenProvid) {
        this.jwtTokenProvider = jwtTokenProvid;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        //nga kerkesa http qe behet marrim tokenin
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //tek objekti auth ose vendosim tokenin ne rast se useri eshte loguar me token ose vendoosim objektin user ne rast se po logohet me kredenciale
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        System.out.println(req);
        filterChain.doFilter(req, res);
    }
}