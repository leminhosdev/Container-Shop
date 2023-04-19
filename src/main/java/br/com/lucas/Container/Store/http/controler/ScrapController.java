package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.Content.ScrapConfiguration;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController


public class ScrapController {

    private ScrapConfiguration scrapConfiguration;
    @PostMapping
    public String Search( @RequestBody String url){
       String formatedUrl = scrapConfiguration.formatingLink(url);
       return scrapConfiguration.scrapingGenerate(formatedUrl);

    }

}
