package graphics.text;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Paragraph /*implements Component*/ {
    /*private float startX;
    private float startY;
    private float width;
    private float height;

    private Style     style;
    private Alignment alignment;

    private List<WordLine> wordLines;
    private float dX;
    private float dY;


    @Override
    public float getHeight() {
        return height - style.getSeparation();
    }

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

        for(WordLine word : wordLines){
            word.setDX(dX);
            word.setDY(dY);
            word.rebuild();
        }
        dX = 0;
        dY = 0;
    }


    @Override
    public void draw(PDPageContentStream contentStream) throws IOException {
        for(WordLine wordLine : wordLines)
            wordLine.draw(contentStream);

        //contentStream.setStrokingColor(Color.RED);
        //contentStream.addRect(startX,startY,width,height);
        //contentStream.stroke();
    }

    public static class ParagraphMultipleBuilder{
        private Paragraph paragraph = new Paragraph();
        private final List<Word> words = new LinkedList<>();


        public ParagraphMultipleBuilder addWord(Word word){
            float dx = paragraph.startX - word.getStartX();
            float dy = paragraph.startY - word.getStartY();

            word.setDX(dx);
            word.setDY(-dy);
            word.rebuild();

            this.words.add(word);

            return this;
        }

        public ParagraphMultipleBuilder addWords(List<Word> words){
            for(Word word : words)
                addWord(word);
            return this;
        }

        public Paragraph build() throws IOException {
            float auxStartX = paragraph.startX;
            float auxStartY = paragraph.startY;

            float auxWidth      = 0;
            List<Word> auxList  = new LinkedList<>();

            for(Word word : this.words){

                if(auxWidth + word.getWidth() > paragraph.width){
                    WordLine wordLine = WordLine.builder()
                            .addStartX(auxStartX)
                            .addStartY(auxStartY)
                            .addWords(auxList)
                            .build();
                    paragraph.wordLines.add(wordLine);
                    auxList = new LinkedList<>();
                    auxList.add(word);
                    auxWidth = word.getWidth();
                    auxStartY = auxStartY + wordLine.getHeight();
                }else {
                    auxWidth = auxWidth + word.getWidth();
                    auxList.add(word);
                }
            }
            if(!auxList.isEmpty()){
                WordLine wordLine = WordLine.builder()
                        .addStartX(auxStartX)
                        .addStartY(auxStartY)
                        .addWords(auxList)
                        .build();
                paragraph.wordLines.add(wordLine);
                auxStartY = auxStartY + wordLine.getHeight();
            }
            paragraph.height = auxStartY - paragraph.startY;

            return paragraph;
        }
    }*/
}
