package com.vai.vmcapi.security;


import com.vai.vmcapi.domain.exception.BusinessException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtProvider {

    private String jwtSecret;
    private int jwtExpirationInMs;
    private SecretKey secretKey;
    private JwtParser jwtParser;

    public JwtProvider(@Value("${app.security.jwtSecret}") String jwtSecret,
                       @Value("${app.security.jwtExpirationInMs}") int jwtExpirationInMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInMs = jwtExpirationInMs;


        /// init secretKey
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parser().verifyWith(secretKey).build();

    }

    public String generateToken(UserContext userPrincipal) {
        return generateToken(userPrincipal.getId());
    }

    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .issuedAt(new Date())
                .id(UUID.randomUUID().toString())
                .expiration(expiryDate)
                .subject(String.valueOf(userId))
                .signWith(secretKey)
                .compact();
    }


    public String getUserIdFromToken(String token) {
        return (getClaimsFromToken(token)).getSubject();
    }

    public DefaultClaims getClaimsFromToken(String token) {

        return ((DefaultClaims) jwtParser.parse(token).getPayload());
    }

    public boolean validateToken(String authToken) {
        try {
            jwtParser.parse(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
//            return false;
            throw new BusinessException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
//            return false;
            throw new BusinessException("Invalid JWT token");

        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");

            throw new BusinessException("Expired JWT token");

        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");

            throw new BusinessException("Unsupported JWT token");

        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");

            throw new BusinessException("JWT claims string is empty.");

        }
    }
}
