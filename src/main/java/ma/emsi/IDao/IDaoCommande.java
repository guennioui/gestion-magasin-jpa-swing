package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.CommandeNotExistException;

import java.util.List;

public interface IDaoCommande {
    void addNewCommande(EntityManager entityManager, Commande commande);
    void deleteCommande(EntityManager entityManager, Commande commande) throws CommandeNotExistException;
    void modifyCommade(EntityManager entityManager, Commande commande, Commande newCommande);
    Commande findCommandeById(EntityManager entityManager, String id) throws CommandeNotExistException;
    List<Commande> listAllCommande(EntityManager entityManager);
}
