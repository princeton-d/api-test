package princeton.test.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import princeton.test.member.domain.role.MemberRole;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.header.name}")
    private String HEADER_NAME;
    private final String BEARER = "Bearer ";
    private final String AUTHORIZATION_KEY = "auth";
    private final Long EXPIRATION_TIME = 60 * 60 * 1000L;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;

    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String username, MemberRole role) {
        Date date = new Date();

        return BEARER + Jwts.builder()
                .setSubject(username)
                .claim(AUTHORIZATION_KEY, role)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + EXPIRATION_TIME))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

//    public String substringToken(String token) {
//
//    }

}
