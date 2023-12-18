package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoLivraison;
import ma.emsi.entities.Article;
import ma.emsi.entities.LigneLivraison;
import ma.emsi.entities.Livraison;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.LivraisonNotFoundException;
import ma.emsi.primaryKeys.PkOfLigneLivraison;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoLivraisonImpl implements IDaoLivraison {
    @Override
    public void addNewLivraison(Livraison livraison, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(livraison);
        entityManager.getTransaction().commit();
    }

    @Override
    public Livraison findLivraisonById(String id, EntityManager entityManager) throws LivraisonNotFoundException {
        Optional<Livraison> optionalLivraison = Optional.ofNullable(entityManager.find(Livraison.class, id));
        if(optionalLivraison.isEmpty()){
            throw new LivraisonNotFoundException("La livraison que vous recherchez "+id+" n'existe pas");
        }
        return optionalLivraison.get();
    }

    @Override
    public void removeLivraison(Livraison livraison, EntityManager entityManager) throws LivraisonNotFoundException{
        Optional<Livraison> optionalLivraison = Optional.ofNullable(entityManager.find(Livraison.class, livraison.getNumeroLivraison()));
        if(optionalLivraison.isEmpty()){
            throw new LivraisonNotFoundException("La livraison que vous recherchez "+livraison.getNumeroLivraison()+" n'existe pas");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(livraison);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifyLivraison(Livraison livraison, Livraison newLivraison, EntityManager entityManager) {

    }

    @Override
    public List<Livraison> listAllLivraison(EntityManager entityManager) {
        TypedQuery<Livraison> query = entityManager.createNamedQuery("Livraison.findAll", Livraison.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public void addArticleToLivraison(Article article, Livraison livraison, int quantity, EntityManager entityManager) throws ArticleNotExistException, LivraisonNotFoundException {
        Optional<Livraison> optionalLivraison = Optional.ofNullable(entityManager.find(Livraison.class, livraison.getNumeroLivraison()));
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, article.getCode()));
        if(optionalLivraison.isEmpty()){
            throw new LivraisonNotFoundException("La livraison que vous recherchez "+livraison.getNumeroLivraison()+" n'existe pas");
        }else if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+article.getCode()+" est introuvable");
        }
        LigneLivraison ligneLivraison = new LigneLivraison(
                new PkOfLigneLivraison(article.getCode(), livraison.getNumeroLivraison()),
                LocalDateTime.now(),
                quantity
        );
        System.out.println("ligne Livraison: ## "+ligneLivraison);
        entityManager.persist(ligneLivraison);
        if(article.getLigneLivraisons() == null){
            entityManager.getTransaction().begin();
            article.setLigneLivraisons(new ArrayList<>(List.of(ligneLivraison)));
            livraison.setLigneLivraisons(new ArrayList<>(List.of(ligneLivraison)));
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            article.getLigneLivraisons().add(ligneLivraison);
            livraison.getLigneLivraisons().add(ligneLivraison);
            entityManager.getTransaction().commit();
        }
    }
}
