package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Article;
import ma.emsi.exceptions.ArticleNotExistException;

import java.util.List;

public interface IDaoArticle {
    void addNewArticle(EntityManager entityManager, Article article);
    Article findArticleById(EntityManager entityManager, String id) throws ArticleNotExistException;
    void removeArticle(EntityManager entityManager, Article article) throws ArticleNotExistException;
    void modifyArticle(EntityManager entityManager, Article article, Article newArticle);
    List<Article> findAllArticles(EntityManager entityManager);
}
