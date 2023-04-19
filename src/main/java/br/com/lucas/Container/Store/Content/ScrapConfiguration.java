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
    

    public void metodo() {
        String html = "https://www.nft-stats.com/collection/thecaptainz";
        try {
            Document doc = Jsoup.connect(html).userAgent("Mozilla").get();
            Elements elements = doc.getElementsByClass("card-text font-content text-nowrap");
            Element divExemplo = elements.get(3);

            System.out.println(divExemplo);


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
