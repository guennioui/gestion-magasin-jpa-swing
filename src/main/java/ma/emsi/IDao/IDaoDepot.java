package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Depot;
import ma.emsi.exceptions.DepotNotFoundException;

import java.util.List;

public interface IDaoDepot {
    void addNewDepot(Depot depot, EntityManager entityManager);
    Depot findDepotById(String id, EntityManager entityManager) throws DepotNotFoundException;
    void removeDepot(Depot depot, EntityManager entityManager) throws DepotNotFoundException;
    void modifyDepot(Depot depot, EntityManager entityManager) throws DepotNotFoundException;
    List<Depot> findAllDepot(EntityManager entityManager);
}
