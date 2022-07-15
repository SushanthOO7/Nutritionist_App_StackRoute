package com.project.jwtfilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
public class AuthFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");
        if(req.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
        }
        else {
        	String authHeader = req.getHeader("Authorization");
        	if (authHeader == null || !authHeader.startsWith("Bearer")) {
        		throw new ServletException("Missing or Invalid Authorization " + " Header");
        	}
			try {
				String token = authHeader.substring(7);
				Claims claim = Jwts.parser()
						.setSigningKey("usersecretkey")
						.parseClaimsJws(token).getBody();
				req.setAttribute("claims", claim.getId());
				chain.doFilter(req, resp);
			}
			catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token. Not Authorized");
			}}
        }
}