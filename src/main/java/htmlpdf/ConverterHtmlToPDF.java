package htmlpdf;

import graphics.basic.Alignment;
import graphics.basic.Style;
import graphics.setters.Setter;
import graphics.text.Paragraph;
import graphics.text.Word;
import graphics.text.WordLine;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import parser.converter.Document;
import parser.lib.ParagraphStruct;
import parser.lib.StyleStruct;
import parser.lib.TextStruct;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class ConverterHtmlToPDF {
    public static void convert(
            PDPageContentStream contentStream,
            Document d) throws IOException {
        //List<ParagraphStruct> paragraphs = d.getParagraphs();

        //drawWord(contentStream);
        //drawWords(contentStream);
        //drawWordLine(contentStream);
        //drawParagraph(contentStream, paragraphs.get(0));
        //drawParagraph(contentStream, paragraphs.get(1));
        drawDocument(contentStream, d);
    }

    private static void drawDocument(
            PDPageContentStream contentStream,
            Document document) throws IOException {

        List<Paragraph> paragraphs = Setter.createDocument(document, 500f,Alignment.LEFT,60f,-750f);

        for(Paragraph paragraph : paragraphs) {
            paragraph.draw(contentStream);
        }
        //contentStream.setStrokingColor(Color.GREEN);
        //contentStream.addRect(20f,700f,500f,-1000);
        //contentStream.stroke();
    }

    private static void drawParagraph(
            PDPageContentStream contentStream,
            ParagraphStruct paragraphStruct) throws IOException {

        Paragraph paragraph = Setter.createParagraphWithCoordinates(paragraphStruct,500f,Alignment.LEFT,20f,-700f);
        paragraph.draw(contentStream);

        //contentStream.setStrokingColor(Color.GREEN);
        //contentStream.addRect(20f,700f,500f,-1000);
        //contentStream.stroke();
    }

    private static void drawWordLine(PDPageContentStream contentStream) throws IOException {
        StyleStruct s = new StyleStruct();
        s.bold();
        s.italic();

        String t = "Hello world, how are you in this day?";

        TextStruct text = new TextStruct(t,s);

        WordLine w = Setter.createWordLineWithWords(Setter.listWordFromText(text));
        w.setDX(20f);
        w.setDY(-40f);
        w.rebuild();

        w.draw(contentStream);

    }

    private static void drawWords(PDPageContentStream contentStream) throws IOException {
        StyleStruct s = new StyleStruct();
        s.isHasBold();
        s.isHasItalic();

        String t = "Hello world, how are you in this day?";

        TextStruct text = new TextStruct(t,s);

        List<Word> words= Setter.listWordFromText(text);

        float posX = 20f;
        float posY = -40f;

        for ( Word word : words) {
            word.setDX(posX);
            word.setDY(posY);
            word.rebuild();
            try {
                word.draw(contentStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            posX = word.getWidth() + posX;
        }

    }

    private static void drawWord(PDPageContentStream contentStream) throws IOException {
        Style s = Style.builder()
                .textColor(Color.BLACK)
                .fontSize(10f)
                .underline(true)
                .textFont(PDType1Font.COURIER)
                .leading(1f)
                .build();

        Word w = Word.builder()
                .alignment(Alignment.LEFT)
                .startX(0f)
                .startY(0f)
                .style(s)
                .textContent("word")
                .dX(0f)
                .dY(0f)
                .height(0f)
                .build();

        w.draw(contentStream);
    }
}
