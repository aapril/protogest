package com.pfe.ldb.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.pfe.ldb.auth.security.exceptions.ExpiredOrInvalidJwtException;

public class JwtFilter extends GenericFilterBean {

	private JwtProvider jwtProvider;
	
	
	public JwtFilter(final JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	
	@Override
	public void doFilter(final ServletRequest request, 
						 final ServletResponse response, 
						 final FilterChain filterChain)
			throws IOException, ServletException {

		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		try {
			if(token == null || jwtProvider.validateToken(token)) {
				return;
			}

			Authentication auth = jwtProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		} catch (final ExpiredOrInvalidJwtException e) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(e.getHttpStatus().value(), e.getMessage());
			return;
		}

		filterChain.doFilter(request, response);
	}

}
