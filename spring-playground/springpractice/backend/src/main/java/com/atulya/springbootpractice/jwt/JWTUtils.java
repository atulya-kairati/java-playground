package com.atulya.springbootpractice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.Map;


@Service
public class JWTUtils {

    private static final String SECRET = "super_private_and_long_secret_key_IDEALLY_THIS_SHOULD_BE_PLACE_IN_A_CONFIG_FILE_OR_AS_ENV_VARIABLE";

    public String issueToken(String subject) {
        return issueJWT(subject, Map.of());
    }

    public String issueToken(String subject, String... scopes) {
        return issueJWT(subject, Map.of("Scopes", scopes));
    }

    public String issueJWT(
            String subject,
            Map<String, Object> claims
    ) {

        String token = Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuer("atulya.kairati")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(Period.ofDays(10)))) // expires after 10 days
                .signWith(getSigningKey())
                .compact();

        return token;
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public boolean isTokenValid(String jwt, String username) {
        String subject = getSubject(jwt);
        return subject.equals(username) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        Date today = Date.from(Instant.now());
        return getClaims(jwt).getExpiration().before(today);
    }
}
