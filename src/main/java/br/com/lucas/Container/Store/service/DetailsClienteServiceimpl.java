package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.Data.DetailsCliente;
import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailsClienteServiceimpl implements UserDetailsService {
   private final Cliente_repository repository;

    public DetailsClienteServiceimpl(Cliente_repository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = repository.findByEmail(email);
        if(cliente.isEmpty()){
            throw new UsernameNotFoundException("Email "+email+" not found");
        }
        return new DetailsCliente(cliente);
    }
}
