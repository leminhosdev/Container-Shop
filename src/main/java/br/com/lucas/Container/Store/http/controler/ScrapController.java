package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.Content.ScrapConfiguration;
import br.com.lucas.Container.Store.entity.Scrap;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController


public class ScrapController {

    private ScrapConfiguration scrapConfiguration;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Scrap Search(@RequestBody @Valid String collection){
       return scrapConfiguration.scrapingGenerate(collection);
    }

}
