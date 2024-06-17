package ru.gb.library.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer lifetime;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public List<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, (Function<Claims, List<String>>)
                claims -> claims.get("roles", List.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T>claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
