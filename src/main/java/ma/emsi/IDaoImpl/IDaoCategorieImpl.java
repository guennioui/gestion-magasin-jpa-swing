package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoCategorie;
import ma.emsi.entities.Article;
import ma.emsi.entities.Categorie;
import ma.emsi.exceptions.CategorieNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoCategorieImpl implements IDaoCategorie {
    @Override
    public void addNewCategorie(EntityManager entityManager, Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.persist(categorie);
        entityManager.getTransaction().commit();
    }

    @Override
    public Categorie findCategorieById(EntityManager entityManager, String id) throws CategorieNotExistException {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(entityManager.find(Categorie.class, id));
        if(optionalCategorie.isEmpty()){
            throw new CategorieNotExistException("La categorie que vous rechercher "+id+" n'existe pas");
        }
        return optionalCategorie.get();
    }

    @Override
    public void deleteCategorie(EntityManager entityManager, Categorie categorie) throws CategorieNotExistException {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(entityManager.find(Categorie.class, categorie.getNumCategorie()));
        if(optionalCategorie.isEmpty()){
            throw new CategorieNotExistException("La categorie que vous rechercher "+categorie.getNumCategorie()+" n'existe pas");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(categorie);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifyCategorie(EntityManager entityManager, Categorie categorie, Categorie newCategorie) {

    }

    @Override
    public List<Categorie> listAllCategories(EntityManager entityManager) {
        TypedQuery<Categorie> allCategories = entityManager.createNamedQuery("Categorie.findAll", Categorie.class);
        return new ArrayList<Categorie>(allCategories.getResultList());
    }

    @Override
    public void addArticleToCategorie(Categorie categorie, Article article, EntityManager entityManager) throws CategorieNotExistException {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(entityManager.find(Categorie.class, categorie.getNumCategorie()));
        if (optionalCategorie.isEmpty()) {
            throw new CategorieNotExistException("La categorie que vous rechercher "+categorie.getNumCategorie()+" n'existe pas");
        }if(categorie.getArticles() == null){
            entityManager.getTransaction().begin();
            categorie.setArticles(List.of(article));
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            categorie.getArticles().add(article);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void addArticlesToCategorie(Categorie categorie, List<Article> articles, EntityManager entityManager) throws CategorieNotExistException {
        Optional<Categorie> optionalCategorie = Optional.ofNullable(entityManager.find(Categorie.class, categorie.getNumCategorie()));
        if (optionalCategorie.isEmpty()) {
            throw new CategorieNotExistException("La categorie que vous rechercher "+categorie.getNumCategorie()+" n'existe pas");
        }if(categorie.getArticles() == null){
            entityManager.getTransaction().begin();
            categorie.setArticles(articles);
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            categorie.getArticles().addAll(articles);
            entityManager.getTransaction().commit();
        }
    }
}
