package com.bcp.springboot.reactive.tipocambio.app.security;
import com.bcp.springboot.reactive.tipocambio.app.model.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/*
 * JwtSigner
 * Clase para la generación del token
 * */
@Component
public class JWTUtil {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Value("${springbootwebfluxjjwt.jjwt.secret}")
    private String secret;

    @Value("${springbootwebfluxjjwt.jjwt.expiration}")
    private String expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
    	logger.info("JWTUtil init");
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        logger.info("JWTUtil this.key:"+this.key);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
    	logger.info("JWTUtil generateToken user:"+user.toString());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        String tokenGenerado=doGenerateToken(claims, user.getUsername());
        logger.info("JWTUtil generateToken tokenGenerado:"+tokenGenerado);
        return tokenGenerado;
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        Long expirationTimeLong = Long.parseLong(expirationTime); //in second
        logger.info("JWTUtil doGenerateToken");
        logger.info("JWTUtil doGenerateToken expirationTime:"+expirationTime);
        logger.info("JWTUtil doGenerateToken expirationTimeLong"+String.valueOf(expirationTimeLong));
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
