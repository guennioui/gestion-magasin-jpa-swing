package ma.emsi.persistence;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class OurCustomPersistenceUnit implements PersistenceUnitInfo {
    private final String PuName;

    public OurCustomPersistenceUnit(String puName) {
        PuName = puName;
    }

    @Override
    public String getPersistenceUnitName() {
        return this.PuName;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/gestion-magasin?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
                "ma.emsi.entities.Client",
                "ma.emsi.entities.Commande",
                "ma.emsi.entities.Article",
                "ma.emsi.entities.LigneCommande",                
                "ma.emsi.entities.Categorie",
                "ma.emsi.entities.Depot",
                "ma.emsi.entities.Fournisseur",
                "ma.emsi.entities.LigneLivraison",
                "ma.emsi.entities.Livraison",
                "ma.emsi.entities.SocieteDistribution",
                "ma.emsi.entities.Stock",
                "ma.emsi.authentication.Admin"
        );
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
