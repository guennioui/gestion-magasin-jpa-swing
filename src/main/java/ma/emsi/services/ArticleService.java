/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoCategorie;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoCategorieImpl;
import ma.emsi.entities.Article;
import ma.emsi.entities.Categorie;
import ma.emsi.exceptions.CategorieNotExistException;

/**
 *
 * @author abdoe
 */
public class ArticleService {
    IDaoArticle iDaoArticle = new IDaoArticleImpl();
    IDaoCategorie iDaoCategorie = new IDaoCategorieImpl();
    
    public void addArticle(
            JTextField jTextField1,
            JTextField jTextField2,
            JComboBox jComboBox,
            EntityManager entityManager
            ) throws CategorieNotExistException{
        if(!jTextField1.getText().equals("") && !jTextField2.getText().equals("") 
                && jComboBox.getSelectedIndex()!= -1){
            Article article = new Article();
            article.setNom(jTextField1.getText());
            article.setPrixUnitaire(new BigDecimal(jTextField2.getText())); 
            Categorie categorie = iDaoCategorie.findCategoriByName(jComboBox.getSelectedItem().toString(), entityManager);
            if(categorie != null){
                iDaoArticle.addNewArticle(entityManager, article);
                System.out.println(article);
                System.out.println(categorie);
                iDaoCategorie.addArticleToCategorie(categorie, article, entityManager); 
                
            }else{
                System.out.println("erreur:ArticleService:46");
            }
            jTextField1.setText("");                    
            jTextField2.setText("");
            jComboBox.setSelectedIndex(-1);
        }else{
             JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }    
        
    public void allCategories(JTable jTable, EntityManager entityManager){          
        List<Categorie> categories = iDaoCategorie.listAllCategories(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();         
        for(Categorie categorie : categories){
            Object[] rowData = {
                categorie.getNumCategorie(),
                categorie.getNomCategorie()
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
    allCategories(jTable, entityManager);
    }   
    
    public void fillJTable(JTable jTable, List<Categorie> categories){                
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();        
        for(Categorie categorie : categories){
            Object[] rowData = {
                categorie.getNumCategorie(),
                categorie.getNomCategorie()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }
    
    public void removeCategorie(String id, EntityManager entityManager)throws CategorieNotExistException{
        iDaoCategorie.deleteCategorie(
                entityManager,
                iDaoCategorie.findCategorieById(entityManager, id)
        );               
    }     
    
    public List<Categorie> fetchCategorieLike(String str, EntityManager entityManager){
        TypedQuery<Categorie> categorieLike = entityManager.createNamedQuery("Categorie.findCategorieLike", Categorie.class);
        categorieLike.setParameter("nomCategorie", "%"+str+"%");      
        return categorieLike.getResultList();
    }
    public void fillJComboBox(JComboBox jComboBox, EntityManager entityManager){
        List<Categorie> catepories = iDaoCategorie.listAllCategories(entityManager);
        for(Categorie categorie : catepories){
            jComboBox.addItem(categorie.getNomCategorie());
        }        
    }
}
