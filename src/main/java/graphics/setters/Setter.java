package graphics.setters;

import graphics.basic.Alignment;
import graphics.basic.Style;
import graphics.text.Paragraph;
import graphics.text.Word;
import graphics.text.WordLine;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import parser.lib.ParagraphStruct;
import parser.lib.StyleStruct;
import parser.lib.TextStruct;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Setter {
    public static Paragraph createParagraph(ParagraphStruct p){
        return null;
    }

    public static WordLine createWordLineWithWords(List<Word> words){
        WordLine wordLine =  WordLine.builder()
                .alignment(Alignment.LEFT)
                .words(words)
                .build();
        wordLine.build();
        return wordLine;
    }

    public static List<Word> listWordFromParagraph(ParagraphStruct paragraph){
        List<Word> words = new LinkedList<>();
        paragraph.getTexts().forEach(t -> {
            words.addAll(listWordFromText(t));
        });
        return words;
    }

    public static List<Word> listWordFromText(TextStruct text){
        Style style = styleFromStyleStruct(text.getStyle());
        return text.getWords()
                .stream()
                .map(w -> Word.builder()
                        .alignment(Alignment.LEFT)
                        .startX(0f)
                        .startY(0f)
                        .style(style)
                        .textContent(w)
                        .dX(0f)
                        .dY(0f)
                        .height(0f)
                        .build())
                .collect(Collectors.toList());
    }

    public static Style styleFromStyleStruct(StyleStruct style){
        return Style.builder()
                .textColor(Color.BLACK)
                .fontSize(10f)
                .underline(style.isHasUnderline())
                .textFont(getFont(style))
                .leading(1f)
                .build();
    }

    private static PDFont getFont(StyleStruct style) {
        PDType1Font font;
        if(style.isHasBold() && style.isHasItalic())    font = PDType1Font.HELVETICA_BOLD_OBLIQUE;
        else if(style.isHasBold())                      font = PDType1Font.HELVETICA_BOLD;
        else if(style.isHasBold())                      font = PDType1Font.HELVETICA_BOLD;
        else                                            font = PDType1Font.HELVETICA;
        return font;
    }
}
