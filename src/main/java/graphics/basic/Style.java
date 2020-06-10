package graphics.basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.Color;

@Getter
@Builder
@AllArgsConstructor
public class Style {
    private Color   textColor;
    private PDFont  textFont;
    private boolean underline;
    private float   fontSize;
    private float   leading;

    public float getSeparation() {
        return leading * fontSize / 3;
    }

}

