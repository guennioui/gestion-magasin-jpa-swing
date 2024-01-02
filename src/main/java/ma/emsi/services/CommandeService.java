/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoClient;
import ma.emsi.IDao.IDaoCommande;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoClientImpl;
import ma.emsi.IDaoImpl.IDaoCommandeImpl;
import ma.emsi.entities.Article;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.ClientNotExistException;

/**
 *
 * @author abdoe
 */
public class CommandeService {

    IDaoArticle iDaoArticle = new IDaoArticleImpl();
    IDaoCommande iDaoCommande = new IDaoCommandeImpl();
    IDaoClient iDaoClient = new IDaoClientImpl();

    public void fillJList(JList jList, EntityManager entityManager) {
        DefaultListModel defaultListModel = new DefaultListModel();
        List<Article> articles = iDaoArticle.findAllArticles(entityManager);
        defaultListModel.addAll(articles
                .stream()
                .map(article -> article.getCode())
                .toList()
        );
        jList.setModel(defaultListModel);
    }

    public void fillComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<Client> clients = iDaoClient.findAllClients(entityManager);
        for (Client client : clients) {
            jComboBox.addItem(client.getId());
        }
    }

    public void addCommande(
            Map<String, Integer> articles,
            JTextField jTextField,
            JComboBox jComboBox,
            EntityManager entityManager)
            throws ArticleNotExistException,
            ClientNotExistException 
    {
        List<Article> articleObjects = new ArrayList<>();
        int[] qantityOfArticle = new int[articles.size()];
        int i = 0;
        Commande commande = new Commande();
        commande.setDateCommende(LocalDate.parse(jTextField.getText()));
        iDaoCommande.addNewCommande(entityManager, commande);
        Client client = iDaoClient.findClientById(jComboBox.getSelectedItem().toString(), entityManager);
        if(client != null){
            iDaoCommande.addCommandeToClient(client, commande, entityManager);
        }else{
            System.out.println("erreur");
        }
        for (Map.Entry<String, Integer> entry : articles.entrySet()) {
            Article article = iDaoArticle.findArticleById(entityManager, entry.getKey());
            if (article != null) {
                articleObjects.add(article);
                qantityOfArticle[i] = entry.getValue();
                ++i;                
            }else{
                System.out.println("erreur");
            }
        }
        System.out.println(qantityOfArticle);
        System.out.println(articles);
        iDaoCommande.addArticlesToCommande(commande, articleObjects, qantityOfArticle, entityManager);
    }
}
