package br.com.lucas.Container.Store.http.controler.dto.filter;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Profile;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import br.com.lucas.Container.Store.util.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        String passwordEnconder = PasswordUtil.encoder(cliente.getPassword());
        cliente.setPassword(passwordEnconder);
        try {
            System.out.println("saved");
            clienteService.saving(cliente);
            return loginFE();
        }catch (Exception e){
            mv.addObject(e.getMessage());
            System.out.println("deu b.o salvando");
        }
        return loginFE();
    }

    @GetMapping("/login")
    public ModelAndView loginFE(){
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }
    @GetMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("home/home");
        return mv;
    }

}
