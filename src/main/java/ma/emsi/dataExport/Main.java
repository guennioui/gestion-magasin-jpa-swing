package ma.emsi.dataExport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoClient;
import ma.emsi.IDaoImpl.IDaoClientImpl;
import ma.emsi.entities.Client;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLegendImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String puName = "persistenceUnit";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        IDaoClient iDaoClient = new IDaoClientImpl();
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            List<Client> query =  iDaoClient.findAllClients(entityManager);

            String filePath = "C:\\Users\\abdoe\\Desktop\\java\\gestion-magasin\\src\\main\\java\\ma\\emsi\\exportedData\\dataV2.xlsx";
            ExcelFile.exportClients(query, filePath);

        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.close();
        }
    }
}
