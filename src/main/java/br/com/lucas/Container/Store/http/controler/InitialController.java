package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.Content.ScrapConfiguration;
import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Profile;
import br.com.lucas.Container.Store.entity.Scrap;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import br.com.lucas.Container.Store.service.ScrapServiceImpl;
import br.com.lucas.Container.Store.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class InitialController {
    @Autowired

    private ClienteServiceImpl clienteService;
    @Autowired
    private Cliente_repository clienteRepository;

    private final ScrapConfiguration scrapConfiguration;

    private ScrapServiceImpl scrapService;
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
    @GetMapping("/inicio")
    public ModelAndView initialPage(){
        ModelAndView mv = new ModelAndView("inicio/inicio");
        return mv;
    }
    @GetMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("home/home");
        mv.addObject("termoPesquisa","");
        return mv;
    }
    @GetMapping("/home/pesquisar")
    public ModelAndView pesquisar(@RequestParam("termo") String termo, HttpSession httpSession) {
        Scrap nft = scrapConfiguration.scrapingGenerate(termo);
        List<Scrap> resultados = new ArrayList<>();
        resultados.add(nft);
        ModelAndView modelAndView = new ModelAndView("home/home");
        modelAndView.addObject("termoPesquisa", termo);
        modelAndView.addObject("resultados", resultados);
        httpSession.setAttribute("resultados", resultados);
        return modelAndView;
    }

    @PostMapping("/home/pesquisar")
    public ModelAndView Search(HttpSession session){
        List<Scrap> resultados = (List<Scrap>) session.getAttribute("resultados");
        resultados.forEach(collection -> scrapService.saving(collection));
        List<String> pesquisasSalvas = (List<String>) session.getAttribute("pesquisasSalvas");
        if (pesquisasSalvas == null) {
            pesquisasSalvas = new ArrayList<>();
        }
        pesquisasSalvas.add(resultados.get(0).getLink());
        session.setAttribute("pesquisasSalvas", pesquisasSalvas);
        ModelAndView mv = new ModelAndView("home/home");
        mv.addObject("resultados", resultados);
        mv.addObject("mensagem", "Pesquisa salva com sucesso!");
        return mv;
    }



}
