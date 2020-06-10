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
    public static Paragraph createParagraphWithCoordinates(
            ParagraphStruct p,
            float width,
            Alignment alignment,
            float startX,
            float startY){
        Paragraph paragraph = createParagraph(p,width,alignment);
        paragraph.setDX(startX);
        paragraph.setDY(startY);
        paragraph.rebuild();
        return paragraph;
    }

    public static Paragraph createParagraph(
            ParagraphStruct p,float width,Alignment alignment
            ){
        List<Word>     wordsOfParagraph = listWordFromParagraph(p);
        List<Word>     wordsOfText      = new LinkedList<>();
        List<WordLine> linesOfParagraph = new LinkedList<>();

        float auxStartX = 0f;
        float auxStartY = 0f;
        float auxWidth  = 0;

        for(Word word : wordsOfParagraph){

            if(auxWidth + word.getWidth() > width){

                WordLine line = createWordLineWithWordsWithCoordinates(wordsOfText,auxStartX,auxStartY);

                linesOfParagraph.add(line);

                wordsOfText = new LinkedList<>();
                wordsOfText.add(word);

                auxWidth = word.getWidth();
                auxStartY = auxStartY - line.getHeight();
            }else {
                auxWidth = auxWidth + word.getWidth();
                wordsOfText.add(word);
            }
        }
        if(!wordsOfText.isEmpty()){
            WordLine line = createWordLineWithWordsWithCoordinates(wordsOfText,auxStartX,auxStartY);
            linesOfParagraph.add(line);
            auxStartY = auxStartY + line.getHeight();
        }
        Paragraph paragraph = Paragraph.builder()
                .alignment(alignment)
                .startX(auxStartX)
                .startY(auxStartY)
                .height(auxStartY-auxStartY )
                .wordLines(linesOfParagraph)
                .width(width)
                .build();

        //paragraph.build();

        return paragraph;
    }

    public static WordLine createWordLineWithWordsWithCoordinates(
            List<Word> words,
            float startX, float startY){

        WordLine wordLine =  createWordLineWithWords(words);
        wordLine.setDX(startX);
        wordLine.setDY(startY);
        wordLine.rebuild();
        return wordLine;
    }

    public static WordLine createWordLineWithWords(List<Word> words){
        WordLine wordLine =  WordLine.builder()
                .startX(0f)
                .startY(0f)
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
