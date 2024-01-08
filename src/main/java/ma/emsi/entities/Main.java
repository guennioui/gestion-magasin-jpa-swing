package ma.emsi.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
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
import ma.emsi.dto.CommandeDTO;

public class Main {

    public static void main(String[] args) {
        IDaoClient iDaoClient = new IDaoClientImpl();
        IDaoCommande iDaoCommande = new IDaoCommandeImpl();
        IDaoFournisseur iDaoFournisseur = new IDaoFournisseurImpl();
        IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
        IDaoArticle iDaoArticle = new IDaoArticleImpl();
        IDaoSocieteDistribution iDaoSocieteDistribution = new IDaoSocieteDistributionImpl();
        IDaoDepot iDaoDepot = new IDaoDepotImpl();
        IDaoLivraison iDaoLivraison = new IDaoLivraisonImpl();

        String puName = "myPersistenceUnit";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "create");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);

        EntityManager entityManager = entityManagerFactory.createEntityManager();                                
            
    }
}
