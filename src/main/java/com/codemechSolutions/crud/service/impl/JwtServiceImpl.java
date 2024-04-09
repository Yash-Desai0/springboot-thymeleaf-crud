package com.codemechSolutions.crud.service.impl;

import com.codemechSolutions.crud.exception.ActorMoviePortalException;
import com.codemechSolutions.crud.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.Map;

import static com.codemechSolutions.crud.constant.JwtConstant.SECRET_KEY;
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${app.jwt-secret}")
    private String secretKey;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ) {
        Date expireDate = new Date(new Date().getTime() + jwtExpirationDate);
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws ActorMoviePortalException {
        try{
            final String userName = extractUsername(token);
            return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            throw new ActorMoviePortalException("Token expired");
        }
        catch(MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e)
        {
            throw new ActorMoviePortalException("invalid token");
        }

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){      // to extract particular we need claim resolver
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())          // to access we need signing key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);   // we have 256bit encrypted key
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
