package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class CustomClienteDetailsService implements UserDetailsService {
    @Autowired
    private Cliente_repository clienteRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            return new org.springframework.security.core.userdetails.User(cliente.getEmail()
                    , cliente.getPassword(),
                    cliente.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

}
