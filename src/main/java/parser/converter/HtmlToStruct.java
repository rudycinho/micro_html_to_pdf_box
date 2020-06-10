package parser.converter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.File;
import java.io.IOException;

public class HtmlToStruct {

    private final ConverterHtmlToStruct converterHtmlToStruct;

    public HtmlToStruct(File input) throws IOException {
        converterHtmlToStruct = new ConverterHtmlToStruct();
        Document doc = Jsoup.parse(input, null);
        Node body = doc.body();
        processNode(body);

    }

    public void processNode(Node node){
        register(node);
        for (Node anotherNode : UtilsHtmlParser.expandClear(node)) {
            processNode( anotherNode );
        }
    }

    private void register(Node node) {
        if(node.nodeName().equalsIgnoreCase("p")){
            converterHtmlToStruct.createParagraph();
        }else if(node.nodeName().equalsIgnoreCase("#text")){
            converterHtmlToStruct.addStyle(node);
        }
    }

    public parser.converter.Document getDocument(){
        return converterHtmlToStruct.getDocument();
    }

}
