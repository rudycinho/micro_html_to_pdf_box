import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {

    public static void main(String [] args) throws IOException {
        //String html = "<div><p>THE <a href=\'one\'>one <strong>two</strong> three</a>IS A <strong>MANGAKA</strong>...</p></div>";
        //Document doc = Jsoup.parse(html);

        String input = "/home/rudy/Code/Parser/src/main/resources/index.html";

        File in = new File(input);
        Document doc = Jsoup.parse(in, null);

/*        Elements elements = doc.body().select("*");
        for(Element element : elements)
            System.out.println(
                    String.format("NOMBRE NODO: %s\nETIQUETA: %s\nTEXT: %s\n",element.nodeName(),element.tagName(),element.ownText() ));
  */
        //Element element = doc.body();


        Queue<Element> elements = new LinkedList<>();
        elements.addAll(doc.body().children());

        Element element;
        element = elements.remove();

        List<Node> children = element.childNodes();
        System.out.println("__________________________");
        System.out.println(children.get(0).nodeName());
        System.out.println(children.get(1).nodeName());
        System.out.println("__________________________");
        System.out.println(children.get(0).toString());
        System.out.println(children.get(1).childNodes());
        System.out.println(children.get(2));
        System.out.println(children.get(3).childNodes());
        System.out.println(children.get(4));

        System.out.println("////////////////////////");
        readElement(element);
        System.out.println("////////////////////////");
        element = elements.remove();
        readElement(element);
        System.out.println("////////////////////////");

    }

    public static void readElement(Element element){
        Iterator<TextNode> itText = element.textNodes().iterator();
        Iterator<Element> itNode = element.children().iterator();

        String tag = element.tagName();

        System.out.println(tag + "--->" + itText.next().text());
        while (itText.hasNext()){
            readElement(itNode.next());
            System.out.println(tag + "--->" + itText.next().text());
        }
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
