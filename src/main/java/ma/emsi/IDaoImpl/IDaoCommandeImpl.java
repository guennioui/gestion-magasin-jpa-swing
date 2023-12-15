package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoCommande;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.CommandeNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoCommandeImpl implements IDaoCommande {
    public IDaoCommandeImpl() {
    }

    @Override
    public void addNewCommande(EntityManager entityManager, Commande commande) {
            entityManager.getTransaction().begin();
            entityManager.persist(commande);
            entityManager.getTransaction().commit();
    }

    @Override
    public Commande findCommandeById(EntityManager entityManager, String id) throws CommandeNotExistException {
        Optional<Commande> optionalCommande = Optional.ofNullable(entityManager.find(Commande.class, id));
        if (optionalCommande.isEmpty()) {
            throw new CommandeNotExistException("la commande que vous rechercher"+id+"n'existe pas");
        }
        return optionalCommande.get();
    }

    @Override
    public void deleteCommande(EntityManager entityManager, Commande commande) throws CommandeNotExistException {
        Optional<Commande> optionalCommande = Optional.ofNullable(entityManager.find(Commande.class, commande.getNumero()));
        if (optionalCommande.isEmpty()) {
            throw new CommandeNotExistException("la commande que vous rechercher"+commande.getNumero()+"n'existe pas");
        }else {
            entityManager.getTransaction().begin();
            entityManager.remove(commande);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifyCommade(EntityManager entityManager, Commande commande, Commande newCommande) {

    }

    @Override
    public List<Commande> listAllCommande(EntityManager entityManager) {
        TypedQuery<Commande> query = entityManager.createNamedQuery("Commande.findAll", Commande.class);
        return new ArrayList<Commande>(query.getResultList());
    }
}
