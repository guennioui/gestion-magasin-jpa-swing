package ma.emsi.authentication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.emsi.exceptions.AdminNotExistException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AdminService {
    public void addAdmin(Admin admin, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }

    public Admin findAdminByEmail(String email, EntityManager entityManager) throws AdminNotExistException {
        Optional<Admin> optionalAdmin = Optional.ofNullable(entityManager.find(Admin.class, email));
        if (optionalAdmin.isEmpty()) {
            throw new AdminNotExistException("L'admin que vous recherchez " + email + " n'existe pas");
        }else{
            return optionalAdmin.get();
        }
    }

    public boolean authenticate(String email, String password, EntityManager entityManager) throws AdminNotExistException {
        Admin admin = this.findAdminByEmail(email, entityManager);
        boolean autheticated = false;
       if(admin != null){
           String JPQlQuery = "SELECT A.password FROM Admin A WHERE A.email = :email";
           TypedQuery<Admin> query = entityManager.createQuery(JPQlQuery, Admin.class);
           query.setParameter("email", email);
           String hashedPassword = String.valueOf(query.getSingleResult());
           if(BCrypt.checkpw(password, hashedPassword)){
               System.out.println("authenticated");
               autheticated = true;
           }else{
               System.out.println("bad credentials!");
               autheticated = false;
           }
       }else{
           throw new AdminNotExistException("L'admin que vous recherchez "+email+" n'existe pas!");
       }
       return autheticated;
    }
}