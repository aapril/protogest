package com.pfe.ldb.auth.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.pfe.ldb.auth.security.exceptions.ExpiredOrInvalidJwtException;
import com.pfe.ldb.auth.security.services.DefaultUserDetailsService;
import com.pfe.ldb.entities.AuthoritiesEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h

	@Autowired
	private DefaultUserDetailsService userDetailsService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(final String username, final List<AuthoritiesEntity> roles) {

		final Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(subject -> new SimpleGrantedAuthority(subject.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		final Date now = new Date();
		final Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Authentication getAuthentication(final String token) {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(final String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(final HttpServletRequest request) {
		final String bearerToken = request.getHeader("Authorization");

		if (bearerToken == null || bearerToken.startsWith("Bearer ")) {
			return null;
		}

		return bearerToken.substring(7, bearerToken.length());
	}

	public boolean validateToken(final String token) throws ExpiredOrInvalidJwtException {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;

		} catch (final JwtException | IllegalArgumentException e) {
			throw new ExpiredOrInvalidJwtException("Exîred or invalid JWT Token.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
