package graphics.text;

import graphics.basic.Alignment;
import graphics.basic.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class WordLine implements Component {
    private float startX;
    private float startY;
    private float width;
    private float height;

    private Alignment alignment;

    private List<Word> words;
    private float dX;
    private float dY;

    @Override
    public void setDX(float dX) {
        this.dX = dX;
    }

    @Override
    public void setDY(float dY) {
        this.dY = dY;
    }

    @Override
    public void rebuild() {
        startX = startX + dX;
        startY = startY - dY;

        for(Word word : words){
            word.setDX(dX);
            word.setDY(dY);
            word.rebuild();
        }
        dX = 0;
        dY = 0;
    }

    public void build(){
        float auxX = 0;
        float auxY = 0;

        for(Word word : words){
            word.setDX(auxX);
            auxX += word.getWidth();
            if(Math.abs(auxY) < Math.abs(word.getHeight())) {
                auxY = word.getHeight();
            }
            word.rebuild();
        }

        width = auxX;
        height= auxY;

        for(Word word : words){
            word.setHeight(height) ;
            word.rebuild();
        }
    }

    @Override
    public void draw(PDPageContentStream contentStream) throws IOException {
        for(Word word : words)
            word.draw(contentStream);

        //contentStream.setStrokingColor(Color.RED);
        //contentStream.addRect(startX,startY,width,height);
        //contentStream.stroke();
    }
}
