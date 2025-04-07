package com.blogpessoal.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blogpessoal.service.TokenService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil implements TokenService {
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    @Value("${jwt.secret}")
    private String secret;
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String generateToken(Authentication authentication) {
        return buildToken(new HashMap<>(), authentication.getName());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails.getUsername());
    }

    private String buildToken(Map<String, Object> claims, String subject) {
        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + expiration);
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(hoje)
            .setExpiration(expiracao)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

	@Override
	public String getSubject(String tokenJWT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gerarToken(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}