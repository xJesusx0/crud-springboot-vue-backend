package jesus.pruebatecnica.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
    private static final String SECRET_KEY = "QL0jPl64e3/Tl0y/rQwarFrBlZjUXDTGT+QltIxlDeg=";
    private static final long TOKEN_EXPIRATION_SECONDS = 2_592_000L;

    public static String createToken(String nombre) {
        long expirationTime = TOKEN_EXPIRATION_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", nombre);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            if (token == null) {
                return null;
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String nombre = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(nombre, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }

    }
}
