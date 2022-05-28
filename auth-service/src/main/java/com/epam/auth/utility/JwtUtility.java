package com.epam.auth.utility;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtility {
	@Value("${secret}")
	private String secret;

	public Boolean validateToken(String token, String userName) {
		String tokenUserName = getUserName(token);
		return (tokenUserName.equals(userName) && !isTokenExpired(token));
	}

	public Boolean isTokenExpired(String token) {
		Date tokenExpDate = getExpiredDate(token);
		return tokenExpDate.before(new Date(System.currentTimeMillis()));
	}

	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}

	public Date getExpiredDate(String token) {
		return getClaims(token).getExpiration();
	}

	public Claims getClaims(String token) {

		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("EPAM").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
}
