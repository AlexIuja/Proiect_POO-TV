import Classes.Packet.Actions.Action;
import Classes.Packet.Pages.*;
import Classes.Packet.Site;
import Classes.fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        Site site = new Site(inputData.getUsers(), inputData.getMovies(), inputData.getActions());


        //test pentru a vedea daca paginilie sunt create corect, la fel si dependentele
//        for(int i = 0; i < site.getAvailablePages().size(); i++) {
//            System.out.println(site.getAvailablePages().get(i).getPageName() + " : ");
//            if(site.getAvailablePages().get(i).getAllowedPagesToChange() != null)
//                for (SitePage page : site.getAvailablePages().get(i).getAllowedPagesToChange())
//                    System.out.println(page.getPageName());
//            else System.out.println("-");
//        }

        //test pentru a vedea pagina curenta atunci cand se creeaza site-ul
//        System.out.println(site.getCurrentPage().getPageName());

        //test pentru actiuni
//        for(int i = 0; i < site.getActionsIn().size(); i++)
//            System.out.println(site.getActionsIn().get(i).toString());



        ArrayNode output = objectMapper.createArrayNode();
        objectWriter.writeValue(new File(args[1]), output);


    }
}
