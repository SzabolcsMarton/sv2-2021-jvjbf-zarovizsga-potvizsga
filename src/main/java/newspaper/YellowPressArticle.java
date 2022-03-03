package newspaper;

import java.util.List;

public class YellowPressArticle extends Article {

    public static final int IMPORTANCE = 1;
    public static final int MAX_TITLE_LEVEL = 5;

    public YellowPressArticle(String author, Header header, List<String> paragraphs) {
        super(author, header, paragraphs);
        ensurTitleSizeIsValid(header);
    }

    private void ensurTitleSizeIsValid(Header header){
        if (header.getLevel() > 5){
            throw new IllegalArgumentException("Header size cannot be greater than 5!");
        }
    }

    @Override
    int getImportance() {
        return IMPORTANCE;
    }
}
