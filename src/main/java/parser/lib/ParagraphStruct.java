package parser.lib;

import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Getter
public class ParagraphStruct {
    private List<TextStruct> texts;

    public ParagraphStruct(){
        texts = new LinkedList<>();
    }

    public void append(TextStruct text) {
        texts.add(text);
    }

    public String toString(){
        StringBuilder s= new StringBuilder("\nPARAGRAPH {\n");
        if(!texts.isEmpty()){
            Iterator<TextStruct> it = texts.iterator();
            s.append(it.next());
            while (it.hasNext()){
                s.append(",");
                s.append(it.next());
            }
        }
        s.append("}\n");
        return s.toString();
    }
}
