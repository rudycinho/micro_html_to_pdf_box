package htmlpdf;

import graphics.basic.Alignment;
import graphics.basic.Style;
import graphics.text.Word;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import parser.converter.Document;
import parser.lib.ParagraphStruct;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ConverterHtmlToPDF {
    public static void convert(
            PDPageContentStream contentStream,
            Document d) throws IOException {
        List<ParagraphStruct> paragraphs = d.getParagraphs();

        drawWord(contentStream);
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
