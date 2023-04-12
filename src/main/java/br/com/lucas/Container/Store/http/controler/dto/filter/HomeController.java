package br.com.lucas.Container.Store.http.controler.dto.filter;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Profile;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private Cliente_repository clienteRepository;

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
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("clienteList", clienteList);
        return mv;
    }




}
