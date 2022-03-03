package newspaper;

import java.util.List;
import java.util.Objects;

public abstract class Article implements Comparable<Article>{

    private String author;
    private Header header;
    private List<String> paragraphs;

    public Article(String author, Header header, List<String> paragraphs) {
        this.author = author;
        this.header = header;
        this.paragraphs = paragraphs;
    }

    abstract int getImportance();

    public Header getHeader() {
        return header;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    @Override
    public int compareTo(Article other) {
        return other.getImportance() - getImportance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(header.getContent(), article.header.getContent()) && Objects.equals(paragraphs, article.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header.getContent(), paragraphs);
    }
}
