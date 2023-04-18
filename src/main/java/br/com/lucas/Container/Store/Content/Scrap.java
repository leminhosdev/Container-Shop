package br.com.lucas.Container.Store.Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.OutputStreamWriter;

public class Scrap {
    public static void search(String link, OutputStreamWriter writer){

        try {
          Document doc = Jsoup.connect(link).get();
        }catch (Exception e){
            System.out.println("Site indisponível");
        }
        for(Element element : doc.getElementsByClass("sc-29427738-0 sc-d58c749b-1 ILliQ jsHA-dC")){
            System.out.println(link);
        }
    }
}
