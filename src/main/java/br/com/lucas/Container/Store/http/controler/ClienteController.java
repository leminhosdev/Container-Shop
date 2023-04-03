package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.saving(cliente);
    }
    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listCliente(){
        return clienteService.listingClientes();
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
