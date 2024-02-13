package uz.pdp.clickuzusers.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.clickuzusers.model.User;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.secret.key}")
    private String key;

    @Value("${jwt.token.secret.expiry}")
    private String expiry;

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getPhoneNumber())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiry)))
                .signWith(key())
                .compact();
    }
    public boolean isValid(String token) {
        Claims claims = parseAllClaims(token);
        Date date = extractExpiryDate(claims);
        return date.after(new Date());
    }
    private Date extractExpiryDate(Claims claims) {
        return claims.getExpiration();
    }

    public Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public SecretKey key() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
