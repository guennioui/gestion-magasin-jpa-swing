package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoSocieteDistribution;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoSocieteDistributionImpl implements IDaoSocieteDistribution {
    @Override
    public void addNewSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(societeDistribution);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeSocieteDistribution(SocieteDistribution societeDistribution, EntityManager entityManager) throws SocieteDistributionNotExistException {

        Optional<SocieteDistribution> optionalSocieteDistribution = Optional.ofNullable(
                entityManager.find(SocieteDistribution.class, societeDistribution.getId()));
        if(optionalSocieteDistribution.isEmpty()){
           throw new SocieteDistributionNotExistException("La Societer que vous voulez supprimer"+societeDistribution.getId()+"est introuvable");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(societeDistribution);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifySocieteDistribution(SocieteDistribution societeDistribution, SocieteDistribution newSocieteDistribution, EntityManager entityManager) {

    }

    @Override
    public SocieteDistribution findSocieteById(String id, EntityManager entityManager) throws SocieteDistributionNotExistException {
        Optional<SocieteDistribution> optionalSocieteDistribution = Optional.ofNullable(
                entityManager.find(SocieteDistribution.class, id));
        if(optionalSocieteDistribution.isEmpty()){
            throw new SocieteDistributionNotExistException("La Societer que vous vous rechercher "+id+" est introuvable");
        }
        return optionalSocieteDistribution.get();
    }

    @Override
    public List<SocieteDistribution> FindAllSocieteDistribution(EntityManager entityManager) {
        TypedQuery<SocieteDistribution> query = entityManager.createNamedQuery("SocieteDistribution.findAll", SocieteDistribution.class);
        return new ArrayList<>(query.getResultList());
    }
}
