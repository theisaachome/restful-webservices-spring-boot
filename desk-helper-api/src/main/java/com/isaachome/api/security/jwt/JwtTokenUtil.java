package com.isaachome.api.security.jwt;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 8327369203738133832L;


	static final String CLAIM_KEY_USERNAME="sub";
	static final String CLAIM_KEY_CREATED="created";
	static final String CLAIM_KEY_EXPIRED="exp";
	
	@Value("${jwt.secret}")
	private String secret;
	
	
	@Value("${jwt.expiration}")
    private Long expiration;
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username=null;
		}
		return username;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJwt(token)
					.getBody();
		} catch (Exception e) {
			claims=null;
		}
		return claims;
	}
}
