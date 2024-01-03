package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import ma.emsi.entities.Article;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.CommandeNotExistException;

import java.util.List;

public interface IDaoCommande {
    void addNewCommande(EntityManager entityManager, Commande commande);
    void deleteCommande(EntityManager entityManager, Commande commande) throws CommandeNotExistException;
    void modifyCommade(EntityManager entityManager, Commande commande, Commande newCommande);
    Commande findCommandeById(EntityManager entityManager, String id) throws CommandeNotExistException;
    List<Commande> listAllCommande(EntityManager entityManager);
    void addCommandeToClient(Client client, Commande commande, EntityManager entityManager) throws ClientNotExistException;
    void addCommandesToClient(Client client, List<Commande> commandes, EntityManager entityManager) throws ClientNotExistException;
    void addArticleToCommande(Commande commande, Article article, int quantity,EntityManager entityManager) throws CommandeNotExistException, ArticleNotExistException;
    void addArticlesToCommande(Commande commande, List<Article> articles, int[] quantity, EntityManager entityManager);
    void updateMontantCommande(Commande commande, EntityManager entityManager) throws CommandeNotExistException;
}
