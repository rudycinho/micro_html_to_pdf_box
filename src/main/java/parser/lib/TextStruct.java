package parser.lib;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextStruct {
    private String content;
    private StyleStruct style;

    public TextStruct(String content, StyleStruct style) {
        this.content = content;
        this.style   = style;
    }

    public StyleStruct getStyle(){
        return style;
    }

    public List<String> getWords(){
        return Arrays.asList(content.trim().split(" "))
                .stream()
                .map(w -> w.trim()+" ")
                .collect(Collectors.toList());
    }

    public String toString(){
        return "TEXT{\n\t"+style.toString()+"\n\tCONTENT"+content+"\n}\n";
    }

}
