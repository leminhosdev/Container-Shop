package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.Content.ScrapConfiguration;
import br.com.lucas.Container.Store.entity.Scrap;
import br.com.lucas.Container.Store.repository.Scrap_repository;
import br.com.lucas.Container.Store.service.ScrapServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ScrapController {

    private ScrapConfiguration scrapConfiguration;
    private ScrapServiceImpl scrapService;
    private InitialController initialController;
    private Scrap_repository scrapRepository;
   /* @PostMapping("/home/pesquisar/salvar")
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
    @GetMapping("/home/pesquisar/salvar")
    public ModelAndView top(){
        System.out.println("oi");
        ModelAndView modelAndView = new ModelAndView("home/home");
        return modelAndView;
    }
*/
}
