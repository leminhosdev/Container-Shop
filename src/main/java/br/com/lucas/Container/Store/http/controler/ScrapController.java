package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.Content.ScrapConfiguration;
import br.com.lucas.Container.Store.entity.Scrap;
import br.com.lucas.Container.Store.service.ScrapServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScrapController {

    private ScrapConfiguration scrapConfiguration;
    private ScrapServiceImpl scrapService;
    private InitialController initialController;
   /* @PostMapping("/savenft")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView Search( @RequestParam @Valid String collection){
        ModelAndView mv = new ModelAndView("home/home");
        Scrap nft = scrapConfiguration.scrapingGenerate(collection);
        scrapService.saving(nft);
        return mv;
    } */
    @GetMapping("/savenft")
    public String nftsave(@RequestParam("collection") String collection, Model model){
        Scrap nft = scrapConfiguration.scrapingGenerate(collection);
        List<Scrap> resultados = new ArrayList<>();
        resultados.add(nft);
        return "nftcollection/nftcollection";
    }

}
