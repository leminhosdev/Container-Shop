package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.repository.Cliente_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticaionService implements UserDetailsService {
   @Autowired
    private Cliente_repository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clienteRepository.findByLogin(email);
    }
}
