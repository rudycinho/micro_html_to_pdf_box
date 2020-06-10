package parser.converter;

import lombok.Getter;
import parser.lib.ParagraphStruct;

import java.util.List;

@Getter
public class Document{
    List<ParagraphStruct> paragraphs;

    public Document(List<ParagraphStruct> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
