package graphics.text;

import graphics.basic.Alignment;
import graphics.basic.Component;
import graphics.basic.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;
import java.io.IOException;

@Getter
@Builder
@AllArgsConstructor
public class Word implements Component {
    private float startX;
    private float startY;
    private float height;

    private final String    textContent;
    private final Style     style;
    private final Alignment alignment;

    private float dX;
    private float dY;

    public float getWidth() {
        try {
            return style.getTextFont().getStringWidth(textContent) / 1000 * style.getFontSize();
        } catch (IOException e) {
            e.printStackTrace();
            return 0f;
        }
    }

    @Override
    public void setDX(float dX) {
        this.dX = dX;
    }

    @Override
    public void setDY(float dY) {
        this.dY = dY;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getHeight() {
        return getStyle().getFontSize() * -1 * style.getLeading();
    }

    @Override
    public void rebuild() {
        startX = startX + dX;
        startY = startY - dY;
        dX = 0;
        dY = 0;
    }

    @Override
    public void draw(PDPageContentStream contentStream) throws IOException {
        contentStream.setNonStrokingColor(style.getTextColor());
        contentStream.setFont(style.getTextFont(), style.getFontSize());

        contentStream.beginText();
        contentStream.newLineAtOffset(startX+dX, startY + getHeight() - getHeight()/4);

        contentStream.showText(textContent);
        contentStream.endText();

        if(style.isUnderline()) {
            contentStream.setStrokingColor(style.getTextColor());
            contentStream.addRect(startX, startY + height+1f, getWidth(), 0);
            contentStream.stroke();
        }

        /*
        contentStream.setStrokingColor(new Color(255,255,125));
        contentStream.addRect(startX,startY,getWidth(),height);
        contentStream.stroke();

        contentStream.setStrokingColor(new Color(255,255,125));
        contentStream.addRect(startX,startY,1,-1);
        contentStream.stroke();*/
    }

}
