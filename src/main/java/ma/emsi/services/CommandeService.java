/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.entities.Article;

/**
 *
 * @author abdoe
 */
public class CommandeService {
    IDaoArticle iDaoArticle = new IDaoArticleImpl();
    
    public void fillJList(JList jList, EntityManager entityManager){
        DefaultListModel defaultListModel = new DefaultListModel();
        List<Article> articles = iDaoArticle.findAllArticles(entityManager);        
            defaultListModel.addAll(articles
                    .stream()
                    .map(article -> article.getCode())
                    .toList()
                    );        
            jList.setModel(defaultListModel);        
    }
}
