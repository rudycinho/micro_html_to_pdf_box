package graphics.text;

import graphics.basic.Alignment;
import graphics.basic.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Paragraph implements Component {
    private float startX;
    private float startY;
    private float width;
    private float height;

    private Alignment alignment;

    private List<WordLine> wordLines;
    private float dX;
    private float dY;


    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setDX(float dX) {
        this.dX = dX;
    }

    @Override
    public void setDY(float dY) {
        this.dY = dY;
    }

    public void build() {
        float auxY = startY;

        for(WordLine line : wordLines){
            line.setDY(auxY);
            auxY -= line.getWidth();
            line.setDY(auxY);
            line.rebuild();
        }
    }

    @Override
    public void rebuild() {
        startX = startX + dX;
        startY = startY - dY;

        for(WordLine line : wordLines){
            line.setDX(dX);
            line.setDY(dY);
            line.rebuild();
        }
        dX = 0;dY = 0;
    }


    @Override
    public void draw(PDPageContentStream contentStream) throws IOException {
        for(WordLine wordLine : wordLines)
            wordLine.draw(contentStream);

        //contentStream.setStrokingColor(Color.BLUE);
        //contentStream.addRect(startX,startY,width,height);
        //contentStream.stroke();
    }
}
