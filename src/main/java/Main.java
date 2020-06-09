import parser.converter.HtmlToStruct;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        String filename = "./src/main/resources/index.html";
        File input = new File(filename);
        new HtmlToStruct(input);
    }
}
