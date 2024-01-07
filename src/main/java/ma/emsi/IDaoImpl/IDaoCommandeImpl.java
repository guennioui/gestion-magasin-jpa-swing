package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import ma.emsi.IDao.IDaoCommande;
import ma.emsi.entities.Article;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.entities.LigneCommande;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.CommandeNotExistException;
import ma.emsi.primaryKeys.PkOfLigneCommande;

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

    @Override
    public void addCommandeToClient(Client client, Commande commande, EntityManager entityManager) throws ClientNotExistException {
        Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
        if (optionalClient.isEmpty()) {
            throw new ClientNotExistException("le client que vous rechercher "+client.getId()+" n'existe pas");
        }if(client.getCommandes() == null){
            entityManager.getTransaction().begin();
            client.setCommandes(List.of(commande));
            commande.setClient(client);
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            client.getCommandes().add(commande);
            commande.setClient(client);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void addCommandesToClient(Client client, List<Commande> commandes, EntityManager entityManager) throws ClientNotExistException {
        Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
        if (optionalClient.isEmpty()) {
            throw new ClientNotExistException("le client que vous rechercher " + client.getId() + " n'existe pas");
        }
        if(client.getCommandes() == null){
            entityManager.getTransaction().begin();
            client.setCommandes(commandes);
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            client.getCommandes().addAll(commandes);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void addArticleToCommande(Commande commande, Article article, int quantity,EntityManager entityManager) throws
            CommandeNotExistException,
            ArticleNotExistException
    {
        boolean articleLigneCommande = false;
        boolean commandeLigneCommande = false;
        Optional<Commande> optionalCommande = Optional.ofNullable(entityManager.find(Commande.class, commande.getNumero()));
        Optional<Article> optionalArticle = Optional.ofNullable(entityManager.find(Article.class, article.getCode()));
        if (optionalCommande.isEmpty()) {
            throw new CommandeNotExistException("la commande que vous rechercher"+commande.getNumero()+"n'existe pas");
        }else if(optionalArticle.isEmpty()){
            throw new ArticleNotExistException("L'article que demande "+article.getCode()+" est introuvable");
        }else{
            PkOfLigneCommande pkOfLigneCommande = new PkOfLigneCommande(commande.getNumero(), article.getCode());
            LigneCommande ligneCommande = new LigneCommande(pkOfLigneCommande, quantity);
            entityManager.persist(ligneCommande);
            if(commande.getLigneCommandes() == null){
                commandeLigneCommande = true;
                entityManager.getTransaction().begin();
                commande.setLigneCommandes(new ArrayList<>(List.of(ligneCommande)));
                entityManager.getTransaction().commit();
            }
            if(article.getLigneCommandes() == null) {
                articleLigneCommande = true;
                entityManager.getTransaction().begin();
                article.setLigneCommandes(new ArrayList<>(List.of(ligneCommande)));
                entityManager.getTransaction().commit();
            }
            if(articleLigneCommande = false){
                entityManager.getTransaction().begin();
                article.getLigneCommandes().add(ligneCommande);
                entityManager.getTransaction().commit();
            }
            if(commandeLigneCommande = false){
                entityManager.getTransaction().begin();
                commande.getLigneCommandes().add(ligneCommande);
                entityManager.getTransaction().commit();
            }
        }
    }

    @Override
    public void addArticlesToCommande(Commande commande, List<Article> articles, int[] quantity, EntityManager entityManager) {
        List<LigneCommande> ligneCommandes = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++){
                LigneCommande ligneCommande = new LigneCommande(
                        new PkOfLigneCommande(commande.getNumero(), articles.get(i).getCode()),quantity[i]
                );
                ligneCommandes.add(ligneCommande);
                entityManager.persist(ligneCommande);
                if(articles.get(i).getLigneCommandes() == null ){
                    entityManager.getTransaction().begin();
                    articles.get(i).setLigneCommandes(new ArrayList<>(List.of(ligneCommande)));
                    ligneCommande.setArticle(articles.get(i));
                    entityManager.getTransaction().commit();
                }else{
                    entityManager.getTransaction().begin();
                    articles.get(i).getLigneCommandes().add(ligneCommande);
                    ligneCommande.setArticle(articles.get(i));
                    entityManager.getTransaction().commit();
                }                
        }
        if(commande.getLigneCommandes() == null){
            entityManager.getTransaction().begin();
            commande.setLigneCommandes(ligneCommandes);
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().begin();
            commande.getLigneCommandes().addAll(ligneCommandes);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void updateMontantCommande(Commande commande, EntityManager entityManager) throws CommandeNotExistException {
        Optional<Commande> optionalCommande = Optional.ofNullable(entityManager.find(Commande.class, commande.getNumero()));
        if (optionalCommande.isEmpty()) {
            throw new CommandeNotExistException("la commande que vous rechercher"+commande.getNumero()+"n'existe pas");
        }else {
            entityManager.getTransaction().begin();
            commande.generateMontant();
            entityManager.getTransaction().commit();
        }
    }
}
