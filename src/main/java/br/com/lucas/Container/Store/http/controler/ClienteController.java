package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Profile;
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

    private final PasswordEncoder encoder;
    @Autowired
    private ModelMapper modelMapper;

    public ClienteController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("cliente/register");
        mv.addObject("user", new Cliente());
        mv.addObject("profiles", Profile.values());
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute @RequestBody @Valid Cliente cliente){
        ModelAndView mv = new ModelAndView("cliente/register");
        mv.addObject("user", cliente);
        try {
            cliente.setPassword(encoder.encode(cliente.getPassword()));
            System.out.println("saved");
            clienteService.saving(cliente);
            return home();
        }catch (Exception e){
            mv.addObject(e.getMessage());
            System.out.println("deu b.o salvando");
        }
        return home();
    }
    @GetMapping("/inicio")
    public ModelAndView home(){
        List<Cliente> clienteList = this.clienteService.findall();
        ModelAndView mv = new ModelAndView("ReadThis");
        mv.addObject("clienteList", clienteList);
        return mv;
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

}
