package dev.thewlabs.schoolar.common.iam.authentication.services;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static dev.thewlabs.schoolar.infra.environments.SecurityEnvironment.*;

@Service
public class JwtService {
    private JwtService() {
    }

    public @Nullable String extractToken(@NotNull HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.substring(7);
    }

    public String generateToken(Account account) {
        return generateToken(account, new HashMap<>());
    }

    public String generateToken(@NotNull Account account, Map<String, Object> extraClaims) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusHours(JWT_EXPIRATION_IN_HOURS);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setId(account.getId().toString())
                .setSubject(account.getEmail())
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public boolean isTokenValid(String token, @NotNull Account account) {
        final String subject = extractSubject(token);

        return subject.equals(account.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }
}
