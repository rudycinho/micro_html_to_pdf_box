import htmlpdf.ConverterHtmlToPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import parser.converter.Document;
import parser.converter.HtmlToStruct;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        String filename = "./src/main/resources/index.html";
        File input = new File(filename);
        Document d = new HtmlToStruct(input).getDocument();

        PDDocument doc = new PDDocument();

        PDPage page = new PDPage(new PDRectangle(PDRectangle.LETTER.getWidth(),PDRectangle.LETTER.getHeight()));

        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        ConverterHtmlToPDF.convert(contentStream,d);

        contentStream.close();

        doc.save(new File("./src/main/resources/doc.pdf"));
        doc.close();
    }
}
