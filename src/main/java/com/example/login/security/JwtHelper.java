package com.example.login.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.login.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
	
	@Autowired
	public User user;

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	private String secret = "afafasfafafasfasfasfafacasBBABAABBAUIUSUSUAISABdwavfsfarvfafafasfafafasfasfasfafacasABABABABABBABAABBAUIUSUSUAISABdwavfsfarvfafafasfafafasfasfasfafacasABABABABABBABAABBAUIUSUSUAISABdwavfsfarvf";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	@SuppressWarnings("deprecation")
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("name", user.getName());
		claims.put("email", user.getEmail());
		claims.put("role", user.getRole());
		return doGenerateToken(claims, user.getUsername());
	}
	
	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, User user) {
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
}