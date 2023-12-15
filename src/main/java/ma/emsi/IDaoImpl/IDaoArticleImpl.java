package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.entities.Article;
import ma.emsi.exceptions.ArticleNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoArticleImpl implements IDaoArticle {
    @Override
    public void addNewArticle(EntityManager entityManager, Article article) {
        entityManager.getTransaction().begin();
        entityManager.persist(article);
        entityManager.getTransaction().commit();
    }

    @Override
    public Article findArticleById(EntityManager entityManager, String id) throws ArticleNotExistException {
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, id));
        if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+id+" est introuvable");
        }
        return optionalArticle.get();
    }

    @Override
    public void removeArticle(EntityManager entityManager, Article article) throws ArticleNotExistException {
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, article.getCode()));
        if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+article.getCode()+" est introuvable");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(article);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifyArticle(EntityManager entityManager, Article article, Article newArticle) {

    }

    @Override
    public List<Article> findAllArticles(EntityManager entityManager) {
        TypedQuery<Article> query = entityManager.createNamedQuery("Artcile.findAll", Article.class);
        return new ArrayList<Article>(query.getResultList());
    }
}
