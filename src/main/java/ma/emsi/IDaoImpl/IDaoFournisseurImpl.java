package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoFournisseur;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.FournisseurNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoFournisseurImpl implements IDaoFournisseur {
    public IDaoFournisseurImpl() {
    }

    @Override
    public void addNewFournisseur(Fournisseur fournisseur, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(fournisseur);
        entityManager.getTransaction().commit();
    }

    @Override
    public Fournisseur findFournisseurById(Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException {
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, fournisseur.getNumFournisseur()));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher "+fournisseur.getNumFournisseur()+"n'existe pas");
        }
        return optionalFournisseur.get();
    }

    @Override
    public void modifyFournisseur(Fournisseur fournisseur, Fournisseur newFournisseur, EntityManager entityManager) {

    }

    @Override
    public void removeFournisseur(Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException {
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, fournisseur.getNumFournisseur()));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher"+fournisseur.getNumFournisseur()+"n'existe pas");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(fournisseur);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Fournisseur> findAllFournisseurs(EntityManager entityManager) {
        TypedQuery<Fournisseur> query = entityManager.createNamedQuery("Fournisseur.findAll", Fournisseur.class);
        return new ArrayList<Fournisseur>(query.getResultList());
    }
}
