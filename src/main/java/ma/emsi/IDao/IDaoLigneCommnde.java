package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import ma.emsi.entities.Article;
import ma.emsi.entities.Commande;
import ma.emsi.primaryKeys.PkOfLigneCommande;

public interface IDaoLigneCommnde {
    void addNewLigneCommande(Article article, Commande commande, EntityManager entityManager);
    void removeLigneCommande(PkOfLigneCommande pkOfLigneCommande, EntityManager entityManager);
    void modifyLigneCommande(PkOfLigneCommande pkOfLigneCommande, EntityManager entityManager);
}
