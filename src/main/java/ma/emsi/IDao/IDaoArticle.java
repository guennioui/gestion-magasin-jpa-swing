package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import ma.emsi.entities.Article;
import ma.emsi.entities.Depot;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.DepotNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface IDaoArticle {
    void addNewArticle(EntityManager entityManager, Article article);
    Article findArticleById(EntityManager entityManager, String id) throws ArticleNotExistException;
    void removeArticle(EntityManager entityManager, Article article) throws ArticleNotExistException;
    void modifyArticle(EntityManager entityManager, Article article, Article newArticle);
    List<Article> findAllArticles(EntityManager entityManager);
    void addArticleToDepot(Article article, Depot depot, int quantity, LocalDate dateDepot, EntityManager entityManager) 
            throws DepotNotFoundException;            
    void addArticlesToDepot(List<Article> articles, Depot depot, EntityManager entityManager) throws
            DepotNotFoundException;
    List<Object[]> ArticlesDetails(EntityManager entityManager);
}
