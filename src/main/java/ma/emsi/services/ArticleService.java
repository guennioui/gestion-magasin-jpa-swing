/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoCategorie;
import ma.emsi.IDao.IDaoDepot;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoCategorieImpl;
import ma.emsi.IDaoImpl.IDaoDepotImpl;
import ma.emsi.entities.Article;
import ma.emsi.entities.Categorie;
import ma.emsi.entities.Depot;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.CategorieNotExistException;
import ma.emsi.exceptions.DepotNotFoundException;

/**
 *
 * @author abdoe
 */
public class ArticleService {
    IDaoArticle iDaoArticle = new IDaoArticleImpl();
    IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
    IDaoDepot idDaoDepot = new IDaoDepotImpl();
    
    public void addArticle(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JComboBox jComboBox1,
            JComboBox jComboBox2,
            JTextField jTextField4,
            EntityManager entityManager
            ) throws CategorieNotExistException,
                     DepotNotFoundException{
        if(!jTextField1.getText().equals("") && !jTextField2.getText().equals("") 
                && !jTextField3.getText().equals("") && !jTextField4.getText().equals("")
                &&  jComboBox1.getSelectedIndex()!= -1 && jComboBox2.getSelectedIndex()!= -1){            
            Article article = new Article();           
            Categorie categorie = iDaoCategorie.findCategoriByName(jComboBox1.getSelectedItem().toString(), entityManager);
            Depot depot = idDaoDepot.findDepotById(jComboBox2.getSelectedItem().toString(), entityManager);
            if(categorie == null){
                throw new CategorieNotExistException("la categorie que vous rechercher "+jComboBox1.getSelectedItem().toString()+" n'existe pas!"); 
            }
            if(depot == null){
                throw new DepotNotFoundException("Le depot que vous recherchez "+jComboBox2.getSelectedItem().toString()+" est introuvable");
            }            
            if(categorie != null && depot != null){
                article.setNom(jTextField1.getText());
                article.setPrixUnitaire(new BigDecimal(jTextField2.getText()));          
                article.setCategorie(categorie);
                System.out.println(article);
                System.out.println(categorie);
                iDaoArticle.addNewArticle(entityManager, article);                
                iDaoCategorie.addArticleToCategorie(categorie, article, entityManager); 
                iDaoArticle.addArticleToDepot(article, depot,Integer.parseInt(jTextField3.getText()), LocalDate.parse(jTextField4.getText()), entityManager);                
            }
            jTextField1.setText("");                    
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(-1);
            jComboBox2.setSelectedIndex(-1);
        }else{
             JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }    
        
    public void allArticles(JTable jTable, EntityManager entityManager){          
        List<Article> articles = iDaoArticle.findAllArticles(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();         
        for(Article article : articles){
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
    
    public void clearJTable(JTable jTable){
    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
    model.setRowCount(0);
    }  
    public void refresh(JTable jTable, EntityManager entityManager){
    clearJTable(jTable);
    allArticles(jTable, entityManager);
    }   
    
    public void fillJTable(JTable jTable, List<Article> articles){                
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();        
         for(Article article : articles){
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
    
    public void removeArticle(String id, EntityManager entityManager)throws ArticleNotExistException{
        iDaoArticle.removeArticle(
                entityManager,
                iDaoArticle.findArticleById(entityManager, id)
        );
    }     
    
    public List<Article> fetchArticleLike(String str, EntityManager entityManager){
        TypedQuery<Article> articleLike = entityManager.createNamedQuery("Categorie.findCategorieLike", Article.class);
        articleLike.setParameter("code", "%"+str+"%");
        articleLike.setParameter("nom", "%"+str+"%");
        articleLike.setParameter("prixUnitaire", str);
        articleLike.setParameter("categorie", "%"+str+"%");        
        return articleLike.getResultList();
    }
    public void fillCategorieComboBox(JComboBox jComboBox, EntityManager entityManager){
        List<Categorie> catepories = iDaoCategorie.listAllCategories(entityManager);
        for(Categorie categorie : catepories){
            jComboBox.addItem(categorie.getNomCategorie());
        }        
    }
    public void fillDepotComboBox(JComboBox jComboBox, EntityManager entityManager){
        List<Depot> depots = idDaoDepot.findAllDepot(entityManager);
        for(Depot depot : depots){
            jComboBox.addItem(depot.getNumeroDepot());
        }        
    }        
}
