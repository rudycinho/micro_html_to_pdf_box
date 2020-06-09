package parser.converter;

import org.jsoup.nodes.Node;

import java.util.List;
import java.util.stream.Collectors;

public class UtilsHtmlParser {
    public static List<Node> expand(Node node) {
        return node.childNodes();
    }

    public static List<Node> expandClear(Node node) {
        return expand(node).stream().filter(n ->
            !isEmpty(n)
        ).collect(Collectors.toList());
    }

    public static boolean isEmpty(Node node){
        String tag = node.nodeName();
        String val = node.toString().trim();
        return tag.equalsIgnoreCase("#text") && val.equalsIgnoreCase("");
    }

    public static boolean hasUnderline(Node node) {
        return node.nodeName().equalsIgnoreCase("u");
    }

    public static boolean isBold(Node node) {
        return node.nodeName().equalsIgnoreCase("strong");
    }

    public static boolean isItalic(Node node) {
        return node.nodeName().equalsIgnoreCase("i");
    }

    public static boolean isText(Node node) {
        return node.nodeName().equalsIgnoreCase("#text");
    }
}
