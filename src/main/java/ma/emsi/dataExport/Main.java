package ma.emsi.dataExport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoClient;
import ma.emsi.IDao.IDaoFournisseur;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoSocieteDistribution;
import ma.emsi.IDaoImpl.IDaoClientImpl;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoSocieteDistributionImpl;
import ma.emsi.entities.Client;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLegendImpl;

import java.util.*;
import ma.emsi.IDaoImpl.IDaoFournisseurImpl;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.SocieteDistribution;

public class Main {
    public static void main(String[] args) {
        String puName = "persistenceUnit";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        IDaoClient iDaoClient = new IDaoClientImpl();
        IDaoFournisseur iDaoFournisseur = new IDaoFournisseurImpl();
        IDaoArticle iDaoArticle = new IDaoArticleImpl();
        IDaoSocieteDistribution iDaoSocieteDistribution = new IDaoSocieteDistributionImpl();
        
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            
            List<Client> queryClient =  iDaoClient.findAllClients(entityManager);
            String filePathClient = "C:\\Users\\abdoe\\Desktop\\java\\gestion-magasin\\src\\main\\java\\ma\\emsi\\exportedData\\clients.xlsx";
            ExcelFile.exportClients(queryClient, filePathClient);
            
            List<Fournisseur> queryFournisseur = iDaoFournisseur.findAllFournisseurs(entityManager);
            String filePathFournisseur = "C:\\Users\\abdoe\\Desktop\\java\\gestion-magasin\\src\\main\\java\\ma\\emsi\\exportedData\\fournisseurs.xlsx";
            FournisseurExport.exportFournisseurs(queryFournisseur, filePathFournisseur);            
            
            List<Object[]> queryArticle = iDaoArticle.ArticlesDetails(entityManager);
            String filePathArticle = "C:\\Users\\abdoe\\Desktop\\java\\gestion-magasin\\src\\main\\java\\ma\\emsi\\exportedData\\articles.xlsx";
            ArticleExport.exportArticles(queryArticle, filePathArticle); 
            
            List<SocieteDistribution> querySocieteDistributions = iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager);
            String filePathSocieteDistributions = "C:\\Users\\abdoe\\Desktop\\java\\gestion-magasin\\src\\main\\java\\ma\\emsi\\exportedData\\societe_Distributions.xlsx";
            SocieteDistributionExport.exportSocieteDistribution(querySocieteDistributions, filePathSocieteDistributions);
            
        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.close();
        }
    }
}
