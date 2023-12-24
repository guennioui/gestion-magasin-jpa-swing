package ma.emsi.authentication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.persistence.OurCustomPersistenceUnit;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName = "persistenceUnit";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");

        AdminService adminService = new AdminService();

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new OurCustomPersistenceUnit(puName), props);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            Admin admin = new Admin();
            admin.setEmail("email@email.com");
            admin.setFirstName("abdellah");
            admin.setLastName("guennioui");
            admin.setPassword("pass123");
            //adminService.addAdmin(admin, entityManager);
            adminService.authenticate(admin.getEmail(), "pass123", entityManager);

        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.close();
        }
    }
}
