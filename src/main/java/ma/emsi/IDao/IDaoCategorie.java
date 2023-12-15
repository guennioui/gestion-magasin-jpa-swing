package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Categorie;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.CategorieNotExistException;
import ma.emsi.exceptions.CommandeNotExistException;

import java.util.List;

public interface IDaoCategorie {
    void addNewCategorie(EntityManager entityManager, Categorie categorie);
    void deleteCategorie(EntityManager entityManager, Categorie categorie) throws CategorieNotExistException;
    void modifyCategorie(EntityManager entityManager, Categorie categorie, Categorie newCategorie);
    Categorie findCategorieById(EntityManager entityManager, String id) throws CategorieNotExistException;
    List<Categorie> listAllCategories(EntityManager entityManager);
}
