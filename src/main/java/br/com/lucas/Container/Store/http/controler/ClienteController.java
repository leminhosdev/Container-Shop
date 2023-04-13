package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Profile;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;

import br.com.lucas.Container.Store.repository.Cliente_repository;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import br.com.lucas.Container.Store.util.PasswordUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private Cliente_repository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
       String HashMapPassword = PasswordUtil.encoder(cliente.getPassword());
       cliente.setPassword(HashMapPassword);
        return clienteService.saving(cliente);
    }
    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<Cliente> listCliente(ClientFilter clientFilter, Pageable pageable){
        return clienteService.listingClientes(clientFilter, pageable);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findById(@PathVariable("id") Long id){
        return clienteService.searchById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCliente(@PathVariable("id") Long id){
        clienteService.searchById(id).map(cliente -> {
            clienteService.deletedById(cliente.getId());
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found, delete process failed"));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        clienteService.searchById(id).map(clienteBase -> {
            modelMapper.map(cliente, clienteBase);
            clienteService.saving(clienteBase);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente not found, update failed"));
    }

}
