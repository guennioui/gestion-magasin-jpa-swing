package ma.emsi.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.IDao.*;
import ma.emsi.IDaoImpl.*;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import org.hibernate.jpa.HibernatePersistenceProvider;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IDaoClient iDaoClient= new IDaoClientImpl();
        IDaoCommande iDaoCommande = new IDaoCommandeImpl();
        IDaoFournisseur iDaoFournisseur = new IDaoFournisseurImpl();
        IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
        IDaoArticle iDaoArticle = new IDaoArticleImpl();

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

            Client client1 = new Client();
            client1.setNom("guennioui");
            client1.setPrenom("abdellah");
            client1.setAdresse("sale 13150");
            client1.setVille("sale");
            client1.setPays("maroc");
            client1.setTelephone("0762729055");

            Client client2 = new Client();
            client2.setNom("guennioui");
            client2.setPrenom("abdellah");
            client2.setAdresse("sale 13150");
            client2.setVille("sale");
            client2.setPays("maroc");
            client2.setTelephone("0762729056");

            iDaoClient.addNewClient(client, entityManager);
            iDaoClient.addNewClient(client1, entityManager);
            iDaoClient.addNewClient(client2, entityManager);

            System.out.println("\nfind client by id:\n");
            System.out.println(iDaoClient.findClientById(client, entityManager).toString());
            System.out.println("\nliste des clients:\n");
            iDaoClient.findAllClients(entityManager).forEach(System.out::println);
            iDaoClient.removeClient(client1, entityManager);
            System.out.println("\nliste des clients:\n");
            iDaoClient.findAllClients(entityManager).forEach(System.out::println);

            /* Test crud Commande => valide */
            Commande commande = new Commande();
            commande.setDateCommende(LocalDateTime.now());
            commande.setMontant(new BigDecimal("120.54"));

            Commande commande1 = new Commande();
            commande1.setDateCommende(LocalDateTime.now());
            commande1.setMontant(new BigDecimal("120.54"));

            Commande commande2 = new Commande();
            commande2.setDateCommende(LocalDateTime.now());
            commande2.setMontant(new BigDecimal("120.54"));

            iDaoCommande.addNewCommande(entityManager, commande);
            iDaoCommande.addNewCommande(entityManager, commande1);
            iDaoCommande.addNewCommande(entityManager, commande2);

            System.out.println("\nfind commande by id: \n");
            System.out.println(iDaoCommande.findCommandeById(entityManager, commande.getNumero().toString()));
            System.out.println("\nlist of all commandes: \n");
            iDaoCommande.listAllCommande(entityManager).forEach(System.out::println);
            iDaoCommande.deleteCommande(entityManager, commande);
            System.out.println("\nlist of all commandes: \n");
            iDaoCommande.listAllCommande(entityManager).forEach(System.out::println);

            /* Test crud Fournisseur => valide */
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setNom("GUENNIOUI");
            fournisseur.setAdresse("SALE 11100");
            fournisseur.setVille("SALE");

            Fournisseur fournisseur1 = new Fournisseur();
            fournisseur1.setNom("GUENNIOUI");
            fournisseur1.setAdresse("SALE 11100");
            fournisseur1.setVille("SALE");

            Fournisseur fournisseur2 = new Fournisseur();
            fournisseur2.setNom("GUENNIOUI");
            fournisseur2.setAdresse("SALE 11100");
            fournisseur2.setVille("SALE");

            iDaoFournisseur.addNewFournisseur(fournisseur, entityManager);
            iDaoFournisseur.addNewFournisseur(fournisseur1, entityManager);
            iDaoFournisseur.addNewFournisseur(fournisseur2, entityManager);

            System.out.println("\nfind Fournisseur by id: \n");
            System.out.println(iDaoFournisseur.findFournisseurById(fournisseur, entityManager));
            System.out.println("\nlist of all fournisseurs: \n");
            iDaoFournisseur.findAllFournisseurs(entityManager).forEach(System.out::println);
            iDaoFournisseur.removeFournisseur(fournisseur, entityManager);
            System.out.println("\nlist of all fournisseurs: \n");
            iDaoFournisseur.findAllFournisseurs(entityManager).forEach(System.out::println);

            /* Test Crud Categorie => valide*/
            Categorie categorie = new Categorie();
            categorie.setNomCategorie("electronics");

            Categorie categorie1 = new Categorie();
            categorie1.setNomCategorie("electronics");

            Categorie categorie2 = new Categorie();
            categorie2.setNomCategorie("electronics");

            iDaoCategorie.addNewCategorie(entityManager, categorie);
            iDaoCategorie.addNewCategorie(entityManager, categorie1);
            iDaoCategorie.addNewCategorie(entityManager, categorie2);

            System.out.println("\nfind Categorie by id: \n");
            System.out.println(iDaoCategorie.findCategorieById(entityManager, categorie1.getNumCategorie()));
            System.out.println("\nlist of all categories: \n");
            iDaoCategorie.listAllCategories(entityManager).forEach(System.out::println);
            iDaoCategorie.deleteCategorie(entityManager, categorie);
            System.out.println("\nlist of all categories: \n");
            iDaoCategorie.listAllCategories(entityManager).forEach(System.out::println);

            /* Test crud Article =>  valide*/
            Article article1 = new Article();
            article1.setNom("airpods");
            article1.setPrixUnitaire(new BigDecimal("120.54"));

            Article article2 = new Article();
            article2.setNom("smart watch");
            article2.setPrixUnitaire(new BigDecimal("450.00"));

            Article article3 = new Article();
            article3.setNom("camera");
            article3.setPrixUnitaire(new BigDecimal("1200.00"));

            iDaoArticle.addNewArticle(entityManager, article1);
            iDaoArticle.addNewArticle(entityManager, article2);
            iDaoArticle.addNewArticle(entityManager, article3);

            System.out.println("\nfind Article by id: \n");
            System.out.println(iDaoArticle.findArticleById(entityManager, article1.getCode()));
            System.out.println("\nlist of all articles: \n");
            iDaoArticle.findAllArticles(entityManager).forEach(System.out::println);
            iDaoArticle.removeArticle(entityManager, article1);
            System.out.println("\nlist of all articles: \n");
            iDaoArticle.findAllArticles(entityManager).forEach(System.out::println);

        }catch(Exception exception){
            entityManager.close();
        }
    }
}
