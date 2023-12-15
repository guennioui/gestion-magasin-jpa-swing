package ma.emsi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.entities.*;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import ma.emsi.primaryKeys.PkOfLigneCommande;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        String puName = "myPersistenceUnit";
//        Map<String, String> props = new HashMap<>();
//        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.auto", "create");
//
//        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
//                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try{
//            entityManager.getTransaction().begin();
//
//            Client client = new Client();
//            client.setNom("guennioui");
//            client.setPrenom("abdellah");
//            client.setAdresse("sale 13150");
//            client.setVille("sale");
//            client.setPays("maroc");
//            client.setTelephone("0762729054");
//
//            Categorie categorie = new Categorie();
//            categorie.setNomCategorie("electronics");
//
//            Article article1 = new Article();
//            Article article2 = new Article();
//            Article article3 = new Article();
//
//            article1.setNom("airpods");
//            article1.setPrixUnitaire(new BigDecimal("120.54"));
//            article2.setNom("smart watch");
//            article2.setPrixUnitaire(new BigDecimal("450.00"));
//            article3.setNom("camera");
//            article3.setPrixUnitaire(new BigDecimal("1200.00"));
//
//            LigneCommande ligneCommande1 = new LigneCommande();
//            LigneCommande ligneCommande2 = new LigneCommande();
//            LigneCommande ligneCommande3 = new LigneCommande();
//            ligneCommande1.setQuantite(10);
//            ligneCommande2.setQuantite(5);
//            ligneCommande3.setQuantite(8);
//
//            Commande commande = new Commande();
//            commande.setDateCommende(LocalDateTime.now());
//            commande.setMontant(new BigDecimal("120.54"));
//
//            client.setCommandes(List.of(commande));
//            categorie.setArticles(List.of(article1, article2, article3));
//
//            article1.setLigneCommandes(List.of(ligneCommande1));
//            article2.setLigneCommandes(List.of(ligneCommande2));
//            article3.setLigneCommandes(List.of(ligneCommande3));
//
//            entityManager.persist(client);
//            entityManager.persist(categorie);
//            entityManager.persist(article1);
//            entityManager.persist(article2);
//            entityManager.persist(article3);
//            entityManager.persist(commande);
//
//            PkOfLigneCommande pkOfLigneCommande1 = new PkOfLigneCommande(commande.getNumero(), article1.getCode());
//            PkOfLigneCommande pkOfLigneCommande2 = new PkOfLigneCommande(commande.getNumero(), article2.getCode());
//            PkOfLigneCommande pkOfLigneCommande3 = new PkOfLigneCommande(commande.getNumero(), article3.getCode());
//            ligneCommande1.setNumero(pkOfLigneCommande1);
//            ligneCommande2.setNumero(pkOfLigneCommande2);
//            ligneCommande3.setNumero(pkOfLigneCommande3);
//
//            entityManager.persist(ligneCommande1);
//            entityManager.persist(ligneCommande2);
//            entityManager.persist(ligneCommande3);
//
//            entityManager.getTransaction().commit();
//        }finally {
//            entityManager.close();
//        }
    }
}