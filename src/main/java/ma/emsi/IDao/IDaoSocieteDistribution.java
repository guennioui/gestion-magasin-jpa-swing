
package ma.emsi.IDao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.FournisseurNotExistException;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

import java.util.List;

public interface IDaoSocieteDistribution {
    void addNewSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager);
    void removeSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager) throws SocieteDistributionNotExistException;
    void modifySocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager)throws SocieteDistributionNotExistException;
    SocieteDistribution findSocieteById(String id, EntityManager entityManager) throws SocieteDistributionNotExistException;
    List<SocieteDistribution> FindAllSocieteDistribution(EntityManager entityManager);
    void addFournisseurToSocieteDistribution(SocieteDistribution societeDistribution, Fournisseur fournisseur, EntityManager entityManager) throws SocieteDistributionNotExistException, FournisseurNotExistException;
    void addFournisseurToSocieteDistributions(List<SocieteDistribution> societeDistributions, Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException;
}
