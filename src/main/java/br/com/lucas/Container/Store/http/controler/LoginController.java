package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private ClienteServiceImpl clienteService;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        ClientFilter user = new ClientFilter();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") ClientFilter clientDTO,
            BindingResult result,
            Model model) {
        Cliente existingUser = clienteService.findUserByEmail(clientDTO.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", clientDTO);
            return "/registration";
        }

        clienteService.SaveCliente(clientDTO);
        return "redirect:/registration?success";
    }
}
