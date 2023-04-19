package br.com.lucas.Container.Store.Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ScrapConfiguration {



    public String formatingLink(String url) {
        return "https://www.nft-stats.com/collection/" + url;
    }
    public void scrapingGenerate(String url){
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elements = doc.getElementsByClass("card-text font-content text-nowrap");
            Element divExemplo = elements.get(3);

            System.out.println(divExemplo.text());


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
