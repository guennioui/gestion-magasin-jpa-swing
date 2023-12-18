package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Article;
import ma.emsi.entities.Livraison;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.LivraisonNotFoundException;

import java.util.List;

public interface IDaoLivraison {
    void addNewLivraison(Livraison livraison, EntityManager entityManager);
    Livraison findLivraisonById(String id, EntityManager entityManager) throws LivraisonNotFoundException;
    void removeLivraison(Livraison livraison, EntityManager entityManager) throws LivraisonNotFoundException;
    void modifyLivraison(Livraison livraison, Livraison newLivraison, EntityManager entityManager);
    List<Livraison> listAllLivraison(EntityManager entityManager);
    void addArticleToLivraison(Article article, Livraison livraison,int quantity, EntityManager entityManager) throws ArticleNotExistException, LivraisonNotFoundException;

}
