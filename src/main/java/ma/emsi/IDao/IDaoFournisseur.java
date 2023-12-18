package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.Livraison;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.FournisseurNotExistException;
import ma.emsi.exceptions.LivraisonNotFoundException;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

import java.util.List;

public interface IDaoFournisseur {
    void addNewFournisseur(Fournisseur fournisseur, EntityManager entityManager);
    Fournisseur findFournisseurById(Fournisseur fournisseur, EntityManager entityManager)throws FournisseurNotExistException;
    void modifyFournisseur(Fournisseur fournisseur, Fournisseur newFournisseur, EntityManager entityManager);
    void removeFournisseur(Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException;
    List<Fournisseur> findAllFournisseurs(EntityManager entityManager);
    void addLivraisonToFournisseur(Livraison livraison, Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException, LivraisonNotFoundException;
    void addLivraisonsToFournisseur(List<Livraison> livraisons, Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException;
}
