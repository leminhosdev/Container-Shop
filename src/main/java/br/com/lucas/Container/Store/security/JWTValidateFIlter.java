package br.com.lucas.Container.Store.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JWTValidateFIlter extends BasicAuthenticationFilter {
        public static final String HEADER_ATRIBUTE = "Authorization";
        public static final String ATRIBUTE_PREFIX= "Bearer";
     public JWTValidateFIlter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

         String atribute = request.getHeader(HEADER_ATRIBUTE);
         if(atribute == null){
             chain.doFilter(request, response);
             return;
         }
            if (!atribute.startsWith(ATRIBUTE_PREFIX)){
                chain.doFilter(request, response);
                return;
            }
        String token = atribute.replace(ATRIBUTE_PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
         String user = JWT.require(Algorithm.HMAC512(JWTauthenticationFilter.TOKEN_PASSWORD))
                 .build()
                 .verify(token)
                 .getSubject();

         if(user == null){
             return null;
         }
         return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<>());
    }
}
