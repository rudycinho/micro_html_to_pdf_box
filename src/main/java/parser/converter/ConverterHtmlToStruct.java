package parser.converter;

import parser.lib.ParagraphStruct;
import parser.lib.StyleStruct;
import parser.lib.TextStruct;
import org.jsoup.nodes.Node;

import java.util.LinkedList;
import java.util.List;

public class ConverterHtmlToStruct {

    private ParagraphStruct currentParagraph;
    private final List<ParagraphStruct> paragraphs;

    public ConverterHtmlToStruct(){
        paragraphs = new LinkedList<>();
    }

    public void createParagraph() {
        currentParagraph = new ParagraphStruct();
        paragraphs.add(currentParagraph);
    }

    public void addStyle(Node node) {
        if(UtilsHtmlParser.isText(node)) {
            StyleStruct style = new StyleStruct();
            Node anotherNode = node;
            while (node.hasParent()) {
                if (UtilsHtmlParser.isBold(node)) style.bold();
                if (UtilsHtmlParser.isItalic(node)) style.italic();
                if (UtilsHtmlParser.hasUnderline(node)) style.underline();
                node = node.parentNode();
            }
            currentParagraph.append(new TextStruct(anotherNode.toString(), style));
        }
    }


    public Document getDocument() {
        return new Document(paragraphs);
    }
}
