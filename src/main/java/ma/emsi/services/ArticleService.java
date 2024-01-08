/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoCategorie;
import ma.emsi.IDao.IDaoDepot;
import ma.emsi.IDao.IDaoFournisseur;
import ma.emsi.IDao.IDaoLivraison;
import ma.emsi.IDao.IDaoSocieteDistribution;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoCategorieImpl;
import ma.emsi.IDaoImpl.IDaoDepotImpl;
import ma.emsi.IDaoImpl.IDaoFournisseurImpl;
import ma.emsi.IDaoImpl.IDaoLivraisonImpl;
import ma.emsi.IDaoImpl.IDaoSocieteDistributionImpl;
import ma.emsi.entities.Article;
import ma.emsi.entities.Categorie;
import ma.emsi.entities.Depot;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.Livraison;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.CategorieNotExistException;
import ma.emsi.exceptions.DepotNotFoundException;
import ma.emsi.exceptions.FournisseurNotExistException;
import ma.emsi.exceptions.LivraisonNotFoundException;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

/**
 *
 * @author abdoe
 */
public class ArticleService {

    IDaoArticle iDaoArticle = new IDaoArticleImpl();
    IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
    IDaoDepot idDaoDepot = new IDaoDepotImpl();
    IDaoSocieteDistribution iDaoSocieteDistribution = new IDaoSocieteDistributionImpl();
    IDaoFournisseur iDaoFournisseur = new IDaoFournisseurImpl();

    public void addArticle(
            JTextField jTextField1,
            JTextField jTextField2,
            JSpinner jSpinner,
            JComboBox jComboBox1,
            JComboBox jComboBox2,
            JComboBox jComboBox3,
            JComboBox jComboBox4,
            JTextField jTextField4,
            EntityManager entityManager
    ) throws CategorieNotExistException,
            DepotNotFoundException,
            FournisseurNotExistException,
            SocieteDistributionNotExistException,
            ArticleNotExistException,
            LivraisonNotFoundException {
        if (!jTextField1.getText().equals("") && !jTextField2.getText().equals("")
                && (int) jSpinner.getValue() != 0 && !jTextField4.getText().equals("")
                && jComboBox1.getSelectedIndex() != -1 && jComboBox2.getSelectedIndex() != -1
                && jComboBox3.getSelectedIndex() != -1 && jComboBox4.getSelectedIndex() != -1) {
            Article article = new Article();
            Categorie categorie = iDaoCategorie.findCategoriByName(jComboBox1.getSelectedItem().toString(), entityManager);
            Depot depot = idDaoDepot.findDepotById(jComboBox2.getSelectedItem().toString(), entityManager);
            Fournisseur fournisseur = iDaoFournisseur.findFournisseurById(jComboBox4.getSelectedItem().toString(), entityManager);
            System.out.println(fournisseur);
            SocieteDistribution societeDistribution = iDaoSocieteDistribution.findSocieteById(jComboBox3.getSelectedItem().toString(), entityManager);
            IDaoLivraison iDaoLivraison = new IDaoLivraisonImpl();

            if (categorie == null) {
                throw new CategorieNotExistException("la categorie que vous rechercher " + jComboBox1.getSelectedItem().toString() + " n'existe pas!");
            }
            if (depot == null) {
                throw new DepotNotFoundException("Le depot que vous recherchez " + jComboBox2.getSelectedItem().toString() + " est introuvable");
            }
            if (fournisseur == null) {
                throw new FournisseurNotExistException("le fournisseur que vous recherchez " + jComboBox3.getSelectedItem().toString() + " est introuvable");
            }
            if (societeDistribution == null) {
                throw new SocieteDistributionNotExistException("la societe de distribution que vous recherchez " + jComboBox4.getSelectedItem().toString() + " est introuvable");
            }
            if (categorie != null && depot != null && fournisseur != null && societeDistribution != null) {
                article.setNom(jTextField1.getText());
                article.setPrixUnitaire(new BigDecimal(jTextField2.getText()));
                article.setCategorie(categorie);
                iDaoArticle.addNewArticle(entityManager, article);
                iDaoCategorie.addArticleToCategorie(categorie, article, entityManager);
                iDaoArticle.addArticleToDepot(article, depot, (int) jSpinner.getValue(), LocalDate.parse(jTextField4.getText()), entityManager);
                Livraison livraison = new Livraison();
                livraison.setDateLivraison(LocalDate.parse(jTextField4.getText()));
                iDaoLivraison.addNewLivraison(livraison, entityManager);
                iDaoLivraison.addArticleToLivraison(article, livraison, (int) jSpinner.getValue(), entityManager);
                iDaoFournisseur.addLivraisonToFournisseur(livraison, fournisseur, entityManager);
            }
            jTextField1.setText("");
            jTextField2.setText("");
            jSpinner.setValue(0);
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(-1);
            jComboBox2.setSelectedIndex(-1);
            jComboBox3.setSelectedIndex(-1);
            jComboBox4.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allArticles(JTable jTable, EntityManager entityManager) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        String sql = "SELECT article.code, article.nom, categorie.nomCategorie, article.prixUnitaire, stock.quantite, depot.nomDepot, stock.dateDepot, fournisseur.nom, societeDistribution.nom\n"
                + "from article inner join categorie\n"
                + "on article.idCategorie = categorie.numCategorie\n"
                + "inner join stock\n"
                + "on stock.id_article = article.code\n"
                + "inner join depot\n"
                + "on stock.id_depot = depot.numeroDepot\n"
                + "inner join lignelivraison\n"
                + "on article.code = lignelivraison.id_article\n"
                + "inner join livraison\n"
                + "on lignelivraison.id_Livraison = livraison.numeroLivraison\n"
                + "inner join Fournisseur\n"
                + "on fournisseur.numFournisseur = livraison.id_fournisseur\n"
                + "inner join SocieteDistribution\n"
                + "on fournisseur.numFournisseur = SocieteDistribution.id_fournisseur";
        
        Query quey = entityManager.createNativeQuery(sql);
        List<Object[]> articles = quey.getResultList();
        for (Object[] article : articles) {
            Object[] row = {
                article[0],
                article[1],
                article[2],
                article[3],
                article[4],
                article[5],
                article[6],
                article[7],
                article[8]
                };
            model.addRow(row);
        }
        jTable.setModel(model);
    }

    public void clearJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
    }

    public void refresh(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        allArticles(jTable, entityManager);
    }

    public void fillJTable(JTable jTable, List<Article> articles) {
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Article article : articles) {
            Object[] rowData = {
                article.getCode(),
                article.getNom(),
                article.getPrixUnitaire(),
                article.getCategorie()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void removeArticle(String id, EntityManager entityManager) throws ArticleNotExistException {
        iDaoArticle.removeArticle(
                entityManager,
                iDaoArticle.findArticleById(entityManager, id)
        );
    }

    public List<Article> fetchArticleLike(String str, EntityManager entityManager) {
        TypedQuery<Article> articleLike = entityManager.createNamedQuery("Categorie.findCategorieLike", Article.class);
        articleLike.setParameter("code", "%" + str + "%");
        articleLike.setParameter("nom", "%" + str + "%");
        articleLike.setParameter("prixUnitaire", str);
        articleLike.setParameter("categorie", "%" + str + "%");
        return articleLike.getResultList();
    }

    public void fillCategorieComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<Categorie> catepories = iDaoCategorie.listAllCategories(entityManager);
        for (Categorie categorie : catepories) {
            jComboBox.addItem(categorie.getNomCategorie());
        }
    }

    public void fillDepotComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<Depot> depots = idDaoDepot.findAllDepot(entityManager);
        for (Depot depot : depots) {
            jComboBox.addItem(depot.getNumeroDepot());
        }
    }

    public void fillSocieteDistributionComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<SocieteDistribution> societeDistributions = iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager);
        for (SocieteDistribution societeDistribution : societeDistributions) {
            jComboBox.addItem(societeDistribution.getId());
        }
    }

    public void fillFournisseurComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<Fournisseur> fournisseurs = iDaoFournisseur.findAllFournisseurs(entityManager);
        for (Fournisseur fournisseur : fournisseurs) {
            jComboBox.addItem(fournisseur.getNumFournisseur());
        }
    }
}
