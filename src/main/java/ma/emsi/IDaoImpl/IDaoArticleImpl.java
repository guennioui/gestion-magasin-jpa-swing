package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.entities.Article;
import ma.emsi.entities.Depot;
import ma.emsi.entities.Stock;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.DepotNotFoundException;
import ma.emsi.primaryKeys.PkOfStock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoArticleImpl implements IDaoArticle {
    @Override
    public void addNewArticle(EntityManager entityManager, Article article) {
        entityManager.getTransaction().begin();
        article.setCode(article.generateid());
        entityManager.persist(article);        
        entityManager.getTransaction().commit();
    }

    @Override
    public Article findArticleById(EntityManager entityManager, String id) throws ArticleNotExistException {
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, id));
        if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+id+" est introuvable");
        }else{
            return optionalArticle.get();
        }
    }

    @Override
    public void removeArticle(EntityManager entityManager, Article article) throws ArticleNotExistException {
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, article.getCode()));
        if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+article.getCode()+" est introuvable");
        }else{
            entityManager.getTransaction().begin();
            entityManager.remove(article);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void modifyArticle(EntityManager entityManager, Article article, Article newArticle) {

    }

    @Override
    public List<Article> findAllArticles(EntityManager entityManager) {
        TypedQuery<Article> query = entityManager.createNamedQuery("Artcile.findAll", Article.class);
        return query.getResultList();
    }

    @Override
    public void addArticleToDepot(Article article, Depot depot, int quantity, LocalDate dateDepot, EntityManager entityManager) 
            throws  DepotNotFoundException{
        boolean articleStock = false;
        boolean depotStock = false;
        Optional<Depot> optionalDepot = Optional.ofNullable(entityManager.find(Depot.class, depot.getNumeroDepot()));
        if (optionalDepot.isEmpty()){
            throw new DepotNotFoundException("Le depot que vous recherchez "+depot.getNumeroDepot()+" est introuvable");
        }else{
            PkOfStock pkOfStock = new PkOfStock(article.getCode(), depot.getNumeroDepot());
            Stock stock = new Stock();
            stock.setStockId(pkOfStock);
            stock.setDateDepot(dateDepot);
            stock.setQuantite(quantity);            
            entityManager.persist(stock);
            if(article.getStocks() == null){
                articleStock = true;
                entityManager.getTransaction().begin();
                article.setStocks(new ArrayList<>(List.of(stock)));
                entityManager.getTransaction().commit();
            }
            if(depot.getStocks() == null){
                depotStock = true;
                entityManager.getTransaction().begin();
                depot.setStocks(new ArrayList<>(List.of(stock)));
                entityManager.getTransaction().commit();
            }
            if(articleStock == false){
                entityManager.getTransaction().begin();
                article.getStocks().add(stock);
                entityManager.getTransaction().commit();
            }
            if (depotStock == false){
                entityManager.getTransaction().begin();
                depot.getStocks().add(stock);
                entityManager.getTransaction().commit();
            }
        }

    }

    @Override
    public void addArticlesToDepot(List<Article> articles, Depot depot, EntityManager entityManager) throws DepotNotFoundException {

    }

    @Override
    public List<Object[]> ArticlesDetails(EntityManager entityManager) {
       String sql = "SELECT article.code, article.nom, categorie.nomCategorie, article.prixUnitaire, stock.quantite, depot.nomDepot, stock.dateDepot, fournisseur.nom, societeDistribution.nom\n"
                + "from article inner join categorie\n"
                + "on article.idCategorie = categorie.numCategorie\n"
                + "inner join stock\n"
                + "on stock.id_article = article.code\n"
                + "inner join depot\n"
                + "on stock.id_depot = depot.numeroDepot\n"
                + "inner join lignelivraison\n"
                + "on article.code = lignelivraison.id_article\n"
                + "inner join livraison\n"
                + "on lignelivraison.id_Livraison = livraison.numeroLivraison\n"
                + "inner join Fournisseur\n"
                + "on fournisseur.numFournisseur = livraison.id_fournisseur\n"
                + "inner join SocieteDistribution\n"
                + "on fournisseur.numFournisseur = SocieteDistribution.id_fournisseur";

        Query quey = entityManager.createNativeQuery(sql);
        return quey.getResultList();
    }
}
