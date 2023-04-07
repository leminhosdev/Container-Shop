package br.com.lucas.Container.Store.security;

import br.com.lucas.Container.Store.Data.DetailsCliente;
import br.com.lucas.Container.Store.entity.Cliente;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTauthenticationFilter extends UsernamePasswordAuthenticationFilter {
        private final AuthenticationManager authenticationManager;
        public static final String TOKEN_PASSWORD = "d0475c66-e54e-49a3-8de5-2074acb6f5a9";
        public static final int TOKEN_EXPIRATE = 600_000;

    public JWTauthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Cliente cliente = new ObjectMapper().readValue(request.getInputStream(), Cliente.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                cliente.getEmail(),
                cliente.getPassword(),
                new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException("Authentication Fail", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetailsCliente detailsCliente = (DetailsCliente) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(detailsCliente.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATE))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
