package parser.lib;

public class StyleStruct {
    private boolean hasItalic;
    private boolean hasBold;
    private boolean hasUnderline;

    public StyleStruct(){
        hasUnderline = false;
        hasItalic    = false;
        hasBold      = false;
    }

    public void italic() {
        hasItalic = true;
    }

    public void bold() {
        hasBold = true;
    }

    public void underline() {
        hasUnderline = true;
    }

    public String toString(){
        StringBuilder s = new StringBuilder("STYLES( ");
        if(hasItalic)       s.append("Italic ");
        if(hasBold)         s.append("Bold ");
        if(hasUnderline)    s.append("Underline ");
        if( !hasItalic && !hasBold && !hasUnderline )
            s.append("Normal");
        s.append(" )");
        return s.toString();
    }
}
