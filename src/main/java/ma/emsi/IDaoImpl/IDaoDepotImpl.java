package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoDepot;
import ma.emsi.entities.Depot;
import ma.emsi.exceptions.DepotNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoDepotImpl implements IDaoDepot {
    @Override
    public void addNewDepot(Depot depot, EntityManager entityManager) {
        entityManager.getTransaction().begin();        
        depot.setNumeroDepot(depot.generateId());
        entityManager.persist(depot);        
        entityManager.getTransaction().commit();
    }

    @Override
    public Depot findDepotById(String id, EntityManager entityManager) throws DepotNotFoundException {
        Optional<Depot> optionalDepot = Optional.ofNullable(entityManager.find(Depot.class, id));
        if (optionalDepot.isEmpty()){
            throw new DepotNotFoundException("Le depot que vous recherchez "+id+" est introuvable");
        }
        return optionalDepot.get();
    }

    @Override
    public void removeDepot(Depot depot, EntityManager entityManager) throws DepotNotFoundException {
        Optional<Depot> optionalDepot = Optional.ofNullable(entityManager.find(Depot.class, depot.getNumeroDepot()));
        if (optionalDepot.isEmpty()){
            throw new DepotNotFoundException("Le depot que vous recherchez "+depot.getNumeroDepot()+" est introuvable");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(depot);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Depot> findAllDepot(EntityManager entityManager) {
        TypedQuery<Depot> query = entityManager.createNamedQuery("Depot.findAll", Depot.class);
        return new ArrayList<Depot>(query.getResultList());
    }

    @Override
    public void modifyDepot(Depot depot, EntityManager entityManager) throws DepotNotFoundException {
         Optional<Depot> optionalDepot = Optional.ofNullable(entityManager.find(Depot.class, depot.getNumeroDepot()));
        if (optionalDepot.isEmpty()){
            throw new DepotNotFoundException("Le depot que vous recherchez "+depot.getNumeroDepot()+" est introuvable");
        }else{
            entityManager.getTransaction().begin();
            entityManager.merge(depot);
            entityManager.getTransaction().commit();
        }
    }
}
