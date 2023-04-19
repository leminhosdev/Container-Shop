package br.com.lucas.Container.Store.Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class ScrapConfiguration {



    public String formatingLink(String url) {
        return "https://www.nft-stats.com/collection/" + url;
    }
    public String scrapingGenerate(String url){
        Document docc = null;
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elements = doc.getElementsByClass("card-text font-content text-nowrap");
            Element divExemplo = elements.get(3);

            //System.out.println(divExemplo.text());
            return divExemplo.text();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Search not found");
    }
    //retornar uma nft(criando com builder uma entidade) que tera FP, vlolume e etc, se essa nft ja existir no banco de dados, so iremos atualizar
}
