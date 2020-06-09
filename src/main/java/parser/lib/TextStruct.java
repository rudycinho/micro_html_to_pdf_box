package parser.lib;

public class TextStruct {
    private String content;
    private StyleStruct style;

    public TextStruct(String content, StyleStruct style) {
        this.content = content;
        this.style   = style;
    }

    public String toString(){
        return "TEXT{\n\t"+style.toString()+"\n\tCONTENT"+content+"\n}\n";
    }

}
