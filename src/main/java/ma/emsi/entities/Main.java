package ma.emsi.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.IDao.*;
import ma.emsi.IDaoImpl.*;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import org.hibernate.jpa.HibernatePersistenceProvider;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IDaoClient iDaoClient= new IDaoClientImpl();
        IDaoCommande iDaoCommande = new IDaoCommandeImpl();
        IDaoFournisseur iDaoFournisseur = new IDaoFournisseurImpl();
        IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
        IDaoArticle iDaoArticle = new IDaoArticleImpl();
        IDaoSocieteDistribution iDaoSocieteDistribution = new IDaoSocieteDistributionImpl();
        IDaoDepot iDaoDepot = new IDaoDepotImpl();
        IDaoLivraison iDaoLivraison = new IDaoLivraisonImpl();

        String puName = "myPersistenceUnit";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{

            /* Test crud Client => valide */
            Client client = new Client();
            client.setNom("guennioui");
            client.setPrenom("abdellah");
            client.setAdresse("sale 13150");
            client.setVille("sale");
            client.setPays("maroc");
            client.setTelephone("0762729054");
            client.setId(client.generateId());

            Client client1 = new Client();
            client1.setNom("guennioui");
            client1.setPrenom("abdellah");
            client1.setAdresse("sale 13150");
            client1.setVille("sale");
            client1.setPays("maroc");
            client1.setTelephone("0762729055");
            client1.setId(client1.generateId());

            Client client2 = new Client();
            client2.setNom("guennioui");
            client2.setPrenom("abdellah");
            client2.setAdresse("sale 13150");
            client2.setVille("sale");
            client2.setPays("maroc");
            client2.setTelephone("0762729056");
            client2.setId(client2.generateId());

            iDaoClient.addNewClient(client, entityManager);
            iDaoClient.addNewClient(client1, entityManager);
            iDaoClient.addNewClient(client2, entityManager);

            System.out.println("\nfind client by id:\n");
            System.out.println(iDaoClient.findClientById(client.getId(), entityManager).toString());
            System.out.println("\nliste des clients:\n");
            iDaoClient.findAllClients(entityManager).forEach(System.out::println);
            //iDaoClient.removeClient(client1, entityManager);
            System.out.println("\nliste des clients:\n");
            iDaoClient.findAllClients(entityManager).forEach(System.out::println);

            /* Test crud Commande => valide */
            Commande commande = new Commande();
            commande.setDateCommende(LocalDate.now());
            commande.setMontant(new BigDecimal("120.54"));

            Commande commande1 = new Commande();
            commande1.setDateCommende(LocalDate.now());
            commande1.setMontant(new BigDecimal("120.54"));

            Commande commande2 = new Commande();
            commande2.setDateCommende(LocalDate.now());
            commande2.setMontant(new BigDecimal("120.54"));

            iDaoCommande.addNewCommande(entityManager, commande);
            iDaoCommande.addNewCommande(entityManager, commande1);
            iDaoCommande.addNewCommande(entityManager, commande2);

            System.out.println("\nfind commande by id: \n");
            System.out.println(iDaoCommande.findCommandeById(entityManager, commande.getNumero().toString()));
            System.out.println("\nlist of all commandes: \n");
            iDaoCommande.listAllCommande(entityManager).forEach(System.out::println);
            //iDaoCommande.deleteCommande(entityManager, commande);
            System.out.println("\nlist of all commandes: \n");
            iDaoCommande.listAllCommande(entityManager).forEach(System.out::println);

            /* Test crud Fournisseur => valide */
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setNom("GUENNIOUI");
            fournisseur.setPrenom("ABDELLAH");
            fournisseur.setAdresse("SALE 11100");
            fournisseur.setVille("SALE");
            fournisseur.setPrenom("ABDELLAH");
            fournisseur.setNumFournisseur(fournisseur.generateId());

            Fournisseur fournisseur1 = new Fournisseur();
            fournisseur1.setNom("GUENNIOUI");
            fournisseur1.setPrenom("ABDELLAH");
            fournisseur1.setAdresse("SALE 11100");
            fournisseur1.setVille("SALE");
            fournisseur1.setNumFournisseur(fournisseur1.generateId());

            Fournisseur fournisseur2 = new Fournisseur();
            fournisseur2.setNom("GUENNIOUI");
            fournisseur2.setPrenom("ABDELLAH");
            fournisseur2.setAdresse("SALE 11100");
            fournisseur2.setVille("SALE");
            fournisseur2.setNumFournisseur(fournisseur2.generateId());

            iDaoFournisseur.addNewFournisseur(fournisseur, entityManager);
            iDaoFournisseur.addNewFournisseur(fournisseur1, entityManager);
            iDaoFournisseur.addNewFournisseur(fournisseur2, entityManager);

            System.out.println("\nfind Fournisseur by id: \n");
            System.out.println(iDaoFournisseur.findFournisseurById(fournisseur.getNumFournisseur(), entityManager));
            System.out.println("\nlist of all fournisseurs: \n");
            iDaoFournisseur.findAllFournisseurs(entityManager).forEach(System.out::println);
            //iDaoFournisseur.removeFournisseur(fournisseur, entityManager);
            System.out.println("\nlist of all fournisseurs: \n");
            iDaoFournisseur.findAllFournisseurs(entityManager).forEach(System.out::println);

            /* Test Crud Categorie => valide*/
            Categorie categorie = new Categorie();
            categorie.setNomCategorie("electronics");
            categorie.setNumCategorie(categorie.generateId());

            Categorie categorie1 = new Categorie();
            categorie1.setNomCategorie("esthetique");
            categorie1.setNumCategorie(categorie1.generateId());

            Categorie categorie2 = new Categorie();
            categorie2.setNomCategorie("electrique");
            categorie2.setNumCategorie(categorie2.generateId());

            iDaoCategorie.addNewCategorie(entityManager, categorie);
            iDaoCategorie.addNewCategorie(entityManager, categorie1);
            iDaoCategorie.addNewCategorie(entityManager, categorie2);

            System.out.println("\nfind Categorie by id: \n");
            System.out.println(iDaoCategorie.findCategorieById(entityManager, categorie1.getNumCategorie()));
            System.out.println("\nlist of all categories: \n");
            iDaoCategorie.listAllCategories(entityManager).forEach(System.out::println);
            //iDaoCategorie.deleteCategorie(entityManager, categorie);
            System.out.println("\nlist of all categories: \n");
            iDaoCategorie.listAllCategories(entityManager).forEach(System.out::println);

            /* Test crud Article =>  valide*/
            Article article1 = new Article();
            article1.setNom("airpods");
            article1.setPrixUnitaire(new BigDecimal("120.54"));
            article1.setCode(article1.generateid());


            Article article2 = new Article();
            article2.setNom("smart watch");
            article2.setPrixUnitaire(new BigDecimal("450.00"));
            article2.setCode(article2.generateid());

            Article article3 = new Article();
            article3.setNom("camera");
            article3.setPrixUnitaire(new BigDecimal("1200.00"));
            article3.setCode(article3.generateid());

            iDaoArticle.addNewArticle(entityManager, article1);
            iDaoArticle.addNewArticle(entityManager, article2);
            iDaoArticle.addNewArticle(entityManager, article3);

            System.out.println("\nfind Article by id: \n");
            System.out.println(iDaoArticle.findArticleById(entityManager, article1.getCode()));
            System.out.println("\nlist of all articles: \n");
            iDaoArticle.findAllArticles(entityManager).forEach(a -> System.out.println(a));
            //iDaoArticle.removeArticle(entityManager, article1);
            System.out.println("\nlist of all articles: \n");
            iDaoArticle.findAllArticles(entityManager).forEach(a -> System.out.println(a));

            /* Test crud societeDistribution =>  valide*/
            SocieteDistribution societeDistribution = new SocieteDistribution();
            societeDistribution.setNom("Glovo");
            societeDistribution.setAdresse("Mdina 9dima");
            societeDistribution.setVille("Sale");
            societeDistribution.setTelephone("0617281929");
            societeDistribution.setId(societeDistribution.generateId());

            SocieteDistribution societeDistribution1 = new SocieteDistribution();
            societeDistribution1.setNom("Glovo");
            societeDistribution1.setAdresse("Mdina 9dima");
            societeDistribution1.setVille("Sale");
            societeDistribution1.setTelephone("0617281929");
            societeDistribution1.setId(societeDistribution1.generateId());

            SocieteDistribution societeDistribution2 = new SocieteDistribution();
            societeDistribution2.setNom("Glovo");
            societeDistribution2.setAdresse("Mdina 9dima");
            societeDistribution2.setVille("Sale");
            societeDistribution2.setTelephone("0617281929");
            societeDistribution2.setId(societeDistribution2.generateId());

            iDaoSocieteDistribution.addNewSocieteDistribution(societeDistribution, entityManager);
            iDaoSocieteDistribution.addNewSocieteDistribution(societeDistribution1, entityManager);
            iDaoSocieteDistribution.addNewSocieteDistribution(societeDistribution2, entityManager);

            System.out.println("\nfind SocieteDistribution by id: \n");
            System.out.println(iDaoSocieteDistribution.findSocieteById(societeDistribution.getId(), entityManager));
            System.out.println("\nlist of all SocieteDistribution: \n");
            iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager).forEach(System.out::println);
            //iDaoSocieteDistribution.removeSocieteDistribution(societeDistribution, entityManager);
            System.out.println("\nlist of all SocieteDistribution: \n");
            iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager).forEach(System.out::println);

            /* Test crud Depot => valide */
            Depot depot = new Depot();
            depot.setVille("SALE");
            depot.setTelephone("0762729034");
            depot.setNumeroDepot(depot.generateId());

            Depot depot1 = new Depot();
            depot1.setVille("SALE");
            depot1.setTelephone("0762729034");
            depot1.setNumeroDepot(depot1.generateId());

            Depot depot2 = new Depot();
            depot2.setVille("SALE");
            depot2.setTelephone("0762729034");
            depot2.setNumeroDepot(depot2.generateId());

            iDaoDepot.addNewDepot(depot, entityManager);
            iDaoDepot.addNewDepot(depot1, entityManager);
            iDaoDepot.addNewDepot(depot2, entityManager);

            System.out.println("\nfind Depot by id: \n");
            System.out.println(iDaoDepot.findDepotById(depot.getNumeroDepot(), entityManager));
            System.out.println("\nfind All depots: \n");
            iDaoDepot.findAllDepot(entityManager).forEach(System.out::println);
            iDaoDepot.removeDepot(depot, entityManager);
            System.out.println("\nfind All depots: \n");
            iDaoDepot.findAllDepot(entityManager).forEach(System.out::println);

            /*Test crud Livraison => valide*/
            Livraison livraison1 = new Livraison();
            livraison1.setDateLivraison(LocalDate.now());
            livraison1.setNumeroLivraison(livraison1.generateId());

            Livraison livraison2 = new Livraison();
            livraison2.setDateLivraison(LocalDate.now().plusDays(1));
            livraison2.setNumeroLivraison(livraison2.generateId());

            Livraison livraison3 = new Livraison();
            livraison3.setDateLivraison(LocalDate.now().plusDays(2));
            livraison3.setNumeroLivraison(livraison3.generateId());

            iDaoLivraison.addNewLivraison(livraison1, entityManager);
            iDaoLivraison.addNewLivraison(livraison2, entityManager);
            iDaoLivraison.addNewLivraison(livraison3, entityManager);
            System.out.println(iDaoLivraison.findLivraisonById(livraison1.getNumeroLivraison(), entityManager));
            //iDaoLivraison.removeLivraison(livraison1, entityManager);
            System.out.println(iDaoLivraison.listAllLivraison(entityManager));

            /* Test ajouter une ou plusieurs commandes a un client => valide*/
            iDaoCommande.addCommandesToClient(client, List.of(commande1, commande2), entityManager);
            iDaoCommande.addCommandeToClient(client2, commande, entityManager);

            /* Test ajouter une ou plusieurs articles a une categorie => valide*/
            iDaoCategorie.addArticleToCategorie(categorie, article1, entityManager);
            iDaoCategorie.addArticlesToCategorie(categorie1, List.of(article2, article3), entityManager);

            /*Test ajouter une ligne commande a une commande => valide*/
            int[] quantity = {3, 5, 6};
            iDaoCommande.addArticleToCommande(commande1, article1, 3, entityManager);
            iDaoCommande.addArticleToCommande(commande1, article2, 5, entityManager);
            iDaoCommande.addArticleToCommande(commande2, article3, 6, entityManager);
            //iDaoCommande.addArticlesToCommande(commande, List.of(article1, article2, article3), quantity, entityManager);
            //System.out.println(commande.getLigneCommandes());
            //System.out.println(article1.getLigneCommandes());
            //System.out.println(article2.getLigneCommandes());
            //System.out.println(article3.getLigneCommandes());

            /*Test ajouter un fournisseur a une societe de distribution => valide*/
            iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution, fournisseur, entityManager);
            iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution1, fournisseur, entityManager);
            iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution2, fournisseur2, entityManager);
            //iDaoSocieteDistribution.addFournisseurToSocieteDistributions(List.of(societeDistribution, societeDistribution1, societeDistribution2), fournisseur, entityManager);

            /*Test ajouter une Livraison a un fournisseur => valide*/
            iDaoFournisseur.addLivraisonToFournisseur(livraison1,fournisseur, entityManager);
            iDaoFournisseur.addLivraisonToFournisseur(livraison2,fournisseur, entityManager);
            iDaoFournisseur.addLivraisonToFournisseur(livraison3,fournisseur, entityManager);

            /*Test ajouter un article a une Livraison => valide*/
            //iDaoLivraison.addArticleToLivraison(article2, livraison1, 1, entityManager);
            //iDaoLivraison.addArticleToLivraison(article3, livraison1, 2, entityManager);
            iDaoLivraison.addArticleToLivraison(article1, livraison1, 12, entityManager);

            /* Test ajouter a un article a un depot => valide*/
            iDaoArticle.addArticleToDepot(article1, depot1, 60, LocalDate.now(), entityManager);

            System.out.println("\n\n nullable Test ");
            System.out.println(article1.getLigneCommandes());
            System.out.println(article1.getLigneLivraisons());
            System.out.println(article1.getStocks());
            System.out.println(depot1.getStocks());
            System.out.println(commande1.getLigneCommandes());

        }catch(Exception exception){
            exception.printStackTrace();
            entityManager.close();
        }
    }
}
