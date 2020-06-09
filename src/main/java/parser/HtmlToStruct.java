package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class HtmlToStruct {

    public HtmlToStruct(File input) throws IOException {
        Document doc = Jsoup.parse(input, null);
        Node body = doc.body();
        processNode(body,new LinkedList<Node>());
    }


    public void processNode(Node node, LinkedList<Node> nodes){
        register(node,nodes);
        for (Node anotherNode : UtilsHtmlParser.expandClear(node)) {
            LinkedList list = new LinkedList<Node>();
            list.addAll(nodes);
            list.add(node.clone());
            processNode( anotherNode, list );
        }
    }

    private void register(Node node, LinkedList<Node> nodes) {
        print(node,nodes);
    }

    private void print(Node node, LinkedList<Node> nodes){
        if(node.nodeName().equalsIgnoreCase("#text")) {
            System.out.println("*" + node);
            nodes.forEach(n -> {
                System.out.println(n.nodeName());
            });
        }
        //else
        //    System.out.println(">" + node.nodeName());
    }
}
