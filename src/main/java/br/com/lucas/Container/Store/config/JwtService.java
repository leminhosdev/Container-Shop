package br.com.lucas.Container.Store.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String extractEmail(String token) {
    return null;
    }

    private Claims ExtractAllClaims(String Token){
        return Jwts.
                parserBuilder().
                setSigningKey(getSinginKey()).
                build().
                parseClaimsJws().getBody();
    }
}
