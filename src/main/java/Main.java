import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import parser.HtmlToStruct;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String [] args) throws IOException {
        String filename = "/home/rudy/Code/micro_html_to_pdf_box/src/main/resources/index.html";
        File input = new File(filename);
        HtmlToStruct htmlToStruct = new HtmlToStruct(input);
    }

    /*
    private HtmlTagPdfWriter tagWriter;


    public void processNode(Node node){

        //Boolean hasBackground = HtmlTagBackgroundTO.hasBackground(node);
        String tag = node.nodeName();

        if(startNewLine(tag)){
            tagWriter.changeLineY(node);
        }

        if(node.nodeName().equalsIgnoreCase("#text")){
            stream = tagWriter.writeTag(node.parent(), node.toString());
        }
        else if(node.nodeName().equalsIgnoreCase("img")){
            ySpace5();
            stream = tagWriter.writeImage(node);
            ySpace5();
        }
        else if(node.nodeName().equalsIgnoreCase("table")){
            stream = tagWriter.writeTable(node);
        }
        else if(node.nodeName().equalsIgnoreCase("ul")){
            stream = tagWriter.writeUL(node);
        }
        else if(node.nodeName().equalsIgnoreCase("ol")){
            stream = tagWriter.writeOL(node);
        }
        else{
            if(hasBackground){
                ySpace5();
                stream = tagWriter.writeTag(node, node.toString());
                ySpace5();
            }else {
                for (Node chNode : node.childNodes()) {
                    processNode(chNode);
                }
            }
        }
    }*/
}
