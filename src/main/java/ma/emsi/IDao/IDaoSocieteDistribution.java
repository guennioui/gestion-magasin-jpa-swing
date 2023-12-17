package ma.emsi.IDao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import ma.emsi.entities.Client;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

import java.util.List;

public interface IDaoSocieteDistribution {
    void addNewSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager);
    void removeSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager) throws SocieteDistributionNotExistException;
    void modifySocieteDistribution(SocieteDistribution societeDistribution, SocieteDistribution newSocieteDistribution, EntityManager entityManager);
    SocieteDistribution findSocieteById(String id, EntityManager entityManager) throws SocieteDistributionNotExistException;
    List<SocieteDistribution> FindAllSocieteDistribution(EntityManager entityManager);
}
