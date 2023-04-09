package br.com.lucas.Container.Store.Authentication;

import br.com.lucas.Container.Store.config.JwtService;
import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Role;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Cliente_repository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Cliente.builder().
                name(request.getName()).
                cpf(request.getCpf()).
                email(request.getEmail()).
                password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var JwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(JwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var JwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(JwtToken).build();
    }
}
