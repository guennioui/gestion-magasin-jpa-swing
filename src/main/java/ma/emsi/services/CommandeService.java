/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import com.toedter.calendar.JDateChooser;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoArticle;
import ma.emsi.IDao.IDaoClient;
import ma.emsi.IDao.IDaoCommande;
import ma.emsi.IDaoImpl.IDaoArticleImpl;
import ma.emsi.IDaoImpl.IDaoClientImpl;
import ma.emsi.IDaoImpl.IDaoCommandeImpl;
import ma.emsi.dto.CommandeDTO;
import ma.emsi.dto.CommandeDetailsDTO;
import ma.emsi.entities.Article;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.ArticleNotExistException;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.CommandeNotExistException;

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
            JDateChooser jDateChooser,
            JComboBox jComboBox,
            EntityManager entityManager)
            throws ArticleNotExistException,
            ClientNotExistException,
            CommandeNotExistException {
        List<Article> articleObjects = new ArrayList<>();
        int[] qantityOfArticle = new int[articles.size()];
        int i = 0;
        Commande commande = new Commande();
        LocalDate selecteDate = jDateChooser
                .getDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        commande.setDateCommende(selecteDate);
        iDaoCommande.addNewCommande(entityManager, commande);
        Client client = iDaoClient.findClientById(jComboBox.getSelectedItem().toString(), entityManager);
        if (client != null) {
            iDaoCommande.addCommandeToClient(client, commande, entityManager);
        } else {
            System.out.println("erreur");
        }
        for (Map.Entry<String, Integer> entry : articles.entrySet()) {
            Article article = iDaoArticle.findArticleById(entityManager, entry.getKey());
            if (article != null) {
                articleObjects.add(article);
                qantityOfArticle[i] = entry.getValue();
                ++i;
            } else {
                System.out.println("erreur");
            }
        }
        iDaoCommande.addArticlesToCommande(commande, articleObjects, qantityOfArticle, entityManager);
        iDaoCommande.updateMontantCommande(commande, entityManager);
        jDateChooser.setDate(null);
        jComboBox.setSelectedIndex(-1);        
    }
    
    public void clearJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
    }
    
    public void refresh(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        allCommandes(jTable, entityManager);
    }
    
    public void fillJtableCommande(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        String query = "SELECT NEW ma.emsi.dto.CommandeDTO(c.numero, c.dateCommande, c.montant, cl.id, cl.telephone) FROM Commande c JOIN c.client cl";
        TypedQuery<CommandeDTO> commandesDeails = entityManager.createQuery(query, CommandeDTO.class);
        List<CommandeDTO> resultList = commandesDeails.getResultList();
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (CommandeDTO commandeDTO : resultList) {
            Object[] row = {
                commandeDTO.getIdCommande(),
                commandeDTO.getDateCommande(),
                commandeDTO.getMontantCommande(),
                commandeDTO.getIdClient(),
                commandeDTO.getTelephone()
            };
            model.addRow(row);
        }
        jTable.setModel(model);
    }
    
    public void allCommandes(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        String query = "SELECT NEW ma.emsi.dto.CommandeDTO(c.numero, c.dateCommande, c.montant, cl.id, cl.telephone) FROM Commande c JOIN c.client cl";
        TypedQuery<CommandeDTO> commandesDeails = entityManager.createQuery(query, CommandeDTO.class);
        List<CommandeDTO> resultList = commandesDeails.getResultList();
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (CommandeDTO commandeDTO : resultList) {
            Object[] row = {
                commandeDTO.getIdCommande(),
                commandeDTO.getDateCommande(),
                commandeDTO.getMontantCommande(),
                commandeDTO.getIdClient(),
                commandeDTO.getTelephone()
            };
            model.addRow(row);
        }
        jTable.setModel(model);
    }
    
    public void fillJtableLigneCommande(JTable jTable, String idCommande, EntityManager entityManager) {
        this.clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        String query = "SELECT id_commande, id_article, nom, quantite, prixUnitaire FROM lignecommande inner join article on lignecommande.id_article = article.code where id_commande ='" + idCommande + "'";        
        Query commandeDetails = entityManager.createNativeQuery(query);
        List<Object[]> result = commandeDetails.getResultList();        
        for (Object[] details : result) {           
            BigDecimal pu = (BigDecimal) details[4];
            Number qte = (Number) details[3];
            BigDecimal montantLigne = pu.multiply(BigDecimal.valueOf(qte.doubleValue()));                        
            Object[] row = {
                details[0],
                details[1],
                details[2],
                details[4],
                details[3],
                montantLigne
            };
            model.addRow(row);            
        }
        jTable.setModel(model);        
    }
    
    public void removeCommande(String id, EntityManager entityManager) throws CommandeNotExistException {
        iDaoCommande.deleteCommande(
                entityManager,
                iDaoCommande.findCommandeById(entityManager, id)
        );        
    }
}
