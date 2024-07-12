package store.ggun.ai.security.component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import store.ggun.ai.security.exception.JwtAuthenticationException;
import store.ggun.ai.user.domain.UserModel;


@Component
public class JwtToken {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private long refreshExpiration;

    // Instant 글로벌 런칭서비스시 필요 (각 나라의 시간이 다르기때문에 서버의 시간도 다르다)
//    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);
    String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    @SuppressWarnings("unchecked")
    List<String> extractRoles(String jwt) {
        return extractClaim(jwt, claims -> (List<String>) claims.get("roles"));
    }


    public boolean isTokenValid(String jwt) {
        return !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractClaim(jwt, Claims::getExpiration).before(new Date());
    }

    public String generateToken(Map<String, Object> extraClaims, UserModel userDetails, Boolean isRefreshToken) {
        long currentTimeMillis = System.currentTimeMillis();

            return Jwts.builder()
                    .claims(extraClaims)
                    .subject(userDetails.getEmail())
                    .claim("roles", List.of("user"))
                    .issuedAt(new Date(currentTimeMillis))
                    .expiration(Date.from(Instant.now().plusSeconds(isRefreshToken ? refreshExpiration : accessExpiration)))
                    .signWith(getSigningKey(), Jwts.SIG.HS256)
                    .compact();

    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtAuthenticationException(e.getMessage());
        }
    }

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }


}
