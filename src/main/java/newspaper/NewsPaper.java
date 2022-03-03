package newspaper;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class NewsPaper {

    private final String name;
    private TreeSet<Article> articles= new TreeSet<>();

    public NewsPaper(String name) {
        this.name = name;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public String getName() {
        return name;
    }

    public SortedSet<Article> getArticles() {
        return articles;
    }

    public List<Article> findArticlesByAuthor(String author) {
        return articles.stream().filter(article -> article.getAuthor().equals(author)).toList();
    }

    public List<Article> findArticleByParagraphPart(String text) {
        List<Article> result = new ArrayList<>();
        for (Article actual : articles){
            if(isAnyParagraphsContainsText(actual.getParagraphs(),text)){
                result.add(actual);
            }
        }
        return result;
    }

    private boolean isAnyParagraphsContainsText(List<String> paragraphs,String text){
        for (String actual : paragraphs){
            if(isParagraphContainsText(actual,text)){
                return true;
            }
        }
        return false;
    }

    private boolean isParagraphContainsText(String paragraph, String text){
        return paragraph.contains(text);
    }
}
