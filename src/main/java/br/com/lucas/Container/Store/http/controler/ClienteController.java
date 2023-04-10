package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private Cliente_repository clienteRepository;

    private final PasswordEncoder encoder;
    @Autowired
    private ModelMapper modelMapper;

    public ClienteController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        cliente.setPassword(encoder.encode(cliente.getPassword()));
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
   /* @GetMapping("/validate")
    public ResponseEntity<Boolean> validatePassword(@RequestParam String email, String password ){

        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(email);
        if(optionalCliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Cliente cliente = optionalCliente.get();
        boolean valid = encoder.matches(password,cliente.getPassword());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    } */
}
