package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Article;
import ma.emsi.entities.Categorie;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.CategorieNotExistException;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.CommandeNotExistException;

import java.util.List;

public interface IDaoCategorie {
    void addNewCategorie(EntityManager entityManager, Categorie categorie);
    void deleteCategorie(EntityManager entityManager, Categorie categorie) throws CategorieNotExistException;
    void modifyCategorie(EntityManager entityManager, Categorie categorie) throws CategorieNotExistException;
    Categorie findCategorieById(EntityManager entityManager, String id) throws CategorieNotExistException;
    Categorie findCategoriByName(String name, EntityManager entityManager)throws CategorieNotExistException;
    List<Categorie> listAllCategories(EntityManager entityManager);
    void addArticleToCategorie(Categorie categorie, Article article, EntityManager entityManager) throws CategorieNotExistException;
    void addArticlesToCategorie(Categorie categorie, List<Article> articles, EntityManager entityManager) throws CategorieNotExistException;
    
}
