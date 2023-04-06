package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Service
public class TokenService {
    public String gerarToken(Cliente cliente){
        return JWT.create().
                withIssuer("Cliente").
                withSubject(cliente.getEmail()).
                withClaim("id",cliente.getId()).
                withExpiresAt(Date.from(LocalDateTime.now().
                             plusMinutes(10).
                        toInstant(ZoneOffset.of("-03:00")))).
                sign(Algorithm.HMAC256("segurançaAdvanced"));




    }
}
