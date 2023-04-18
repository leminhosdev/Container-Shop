package br.com.lucas.Container.Store.Content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ScrapConfiguration {
    private String path= "C:\\Users\\lemos\\IdeaProjects\\PROJETOS 2.0\\Container-Store\\src\\main\\java\\br\\com\\lucas\\Container\\Store\\Content\\WebScraping.txt";
    private String link  = "https://opensea.io/collection/analytics";
    private OutputStreamWriter writer;

    public ScrapConfiguration(){
        configFile();
       executar();
    }

    private void configFile() {
        try{
            File archirve = new File(path);

            if(!archirve.exists()){
                archirve.createNewFile();

            }
            writer = new OutputStreamWriter(new FileOutputStream(archirve), "UTF-8");
            writer.write("Floor Price\tVolume\tSales");
            writer.flush();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void executar(){
        String url = "https://opensea.io/collection/azuki/analytics";
        Scrap.search(url, writer);
    }

    private void formateLink(String collection){
        this.link = "https://opensea.io/collection/"+collection+"/analytics";
    }
}
