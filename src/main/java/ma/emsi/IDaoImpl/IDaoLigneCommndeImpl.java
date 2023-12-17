package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import ma.emsi.IDao.IDaoLigneCommnde;
import ma.emsi.entities.Article;
import ma.emsi.entities.Commande;
import ma.emsi.primaryKeys.PkOfLigneCommande;

public class IDaoLigneCommndeImpl implements IDaoLigneCommnde {
    @Override
    public void addNewLigneCommande(Article article, Commande commande, EntityManager entityManager) {
    }

    @Override
    public void removeLigneCommande(PkOfLigneCommande pkOfLigneCommande, EntityManager entityManager) {

    }

    @Override
    public void modifyLigneCommande(PkOfLigneCommande pkOfLigneCommande, EntityManager entityManager) {

    }
}
