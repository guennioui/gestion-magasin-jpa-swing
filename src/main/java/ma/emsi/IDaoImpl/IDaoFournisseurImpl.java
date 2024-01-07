package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoFournisseur;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.Livraison;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.FournisseurNotExistException;
import ma.emsi.exceptions.LivraisonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoFournisseurImpl implements IDaoFournisseur {
    public IDaoFournisseurImpl() {
    }

    @Override
    public void addNewFournisseur(Fournisseur fournisseur, EntityManager entityManager) {
        entityManager.getTransaction().begin();        
        fournisseur.setNumFournisseur(fournisseur.generateId());
        entityManager.persist(fournisseur);        
        entityManager.getTransaction().commit();
    }

    @Override
    public Fournisseur findFournisseurById(String id, EntityManager entityManager) throws FournisseurNotExistException {
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, id));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher "+id+" n'existe pas");
        }
        return optionalFournisseur.get();
    }

    @Override
    public void modifyFournisseur(Fournisseur fournisseur, EntityManager entityManager)throws FournisseurNotExistException{
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, fournisseur.getNumFournisseur()));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher"+fournisseur.getNumFournisseur()+"n'existe pas");
        }else{
            entityManager.getTransaction().begin();
            entityManager.merge(fournisseur);
            entityManager.getTransaction().commit();
        }
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

    @Override
    public void addLivraisonToFournisseur(Livraison livraison, Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException, LivraisonNotFoundException {
        Optional<Livraison> optionalLivraison = Optional.ofNullable(entityManager.find(Livraison.class, livraison.getNumeroLivraison()));
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, fournisseur.getNumFournisseur()));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher"+fournisseur.getNumFournisseur()+"n'existe pas");
        }else if(optionalLivraison.isEmpty()){
            throw new LivraisonNotFoundException("La livraison que vous rechercher "+livraison.getNumeroLivraison()+" n'existe pas");
        }
        if(fournisseur.getLivraisons() == null){
            entityManager.getTransaction().begin();
            fournisseur.setLivraisons(new ArrayList<>(List.of(livraison)));
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            fournisseur.getLivraisons().add(livraison);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void addLivraisonsToFournisseur(List<Livraison> livraisons, Fournisseur fournisseur, EntityManager entityManager) throws FournisseurNotExistException {
        Optional<Fournisseur> optionalFournisseur = Optional.ofNullable(entityManager.find(Fournisseur.class, fournisseur.getNumFournisseur()));
        if (optionalFournisseur.isEmpty()) {
            throw new FournisseurNotExistException("le fournisseur que vous rechercher"+fournisseur.getNumFournisseur()+"n'existe pas");
        }
        if(fournisseur.getLivraisons() == null){
            entityManager.getTransaction().begin();
            fournisseur.setLivraisons(livraisons);
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            fournisseur.getLivraisons().addAll(livraisons);
            entityManager.getTransaction().commit();
        }
    }
}
