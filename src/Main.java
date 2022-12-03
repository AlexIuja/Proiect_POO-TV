import Classes.fileio.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();
        objectWriter.writeValue(new File(args[1]), output);


    }
}
