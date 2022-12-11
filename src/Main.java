import Classes.Packet.Actions.Action;
import Classes.Packet.Actions.ActionVisitor;
import Classes.Packet.Actions.ActionVisitorImpl;
import Classes.Packet.Pages.*;
import Classes.Packet.Site;
import Classes.fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        Site site = new Site(inputData.getUsers(), inputData.getMovies(), inputData.getActions());
        ArrayNode output = objectMapper.createArrayNode();
        site.exec(site.getActionsIn(), objectWriter, objectMapper, output, args);
    }
}


