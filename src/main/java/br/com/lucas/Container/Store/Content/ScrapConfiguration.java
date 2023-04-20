package br.com.lucas.Container.Store.Content;

import br.com.lucas.Container.Store.entity.Scrap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class ScrapConfiguration {

    private String formatingLink(String collectionName) {
        return "https://www.nft-stats.com/collection/" + collectionName;
    }


    public Scrap scrapingGenerate(String collectionName){

        String url = formatingLink(collectionName);

        try {

            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elements = doc.getElementsByClass("card-text font-content text-nowrap");
            Element fp = elements.get(3);
            String formarTedfp = fp.text().replace("Ξ","");
            double florPriceDouble = Double.parseDouble(formarTedfp);
            Element owners = elements.get(5);
            Element volume = elements.get(1);

            Scrap nft = Scrap.builder().floorPrice(florPriceDouble)
                    .link(url).
                    owners(owners.text()).
                    tradingVolume(volume.text()).collectionName(collectionName).build();


            System.out.println(owners.text());
            System.out.println(volume.text());


            return nft;

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Search not found");
    }
    //retornar uma nft(criando com builder uma entidade) que tera FP, vlolume e etc, se essa nft ja existir no banco de dados, so iremos atualizar
}
