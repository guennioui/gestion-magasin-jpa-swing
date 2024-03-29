/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ma.emsi.IDaoImpl.IDaoClientImpl;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.exceptions.ClientNotExistException;

/**
 *
 * @author abdoe
 */
public class ClientService {

    IDaoClientImpl iDaoClientImpl = new IDaoClientImpl();

    public void addClient(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JTextField jTextField5,
            JTextField jTextField6,
            EntityManager entityManager
    ) {
       if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[A-Za-z]+$") 
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jTextField5.getText().matches("^[A-Za-z]+$") && jTextField6.getText().matches("^0\\d{9}$")                 
                ) {
            Client client = new Client();
            client.setNom(jTextField1.getText());
            client.setPrenom(jTextField2.getText());
            client.setAdresse(jTextField3.getText());
            client.setVille(jTextField4.getText());
            client.setPays(jTextField5.getText());
            client.setTelephone(jTextField6.getText());
            
            iDaoClientImpl.addNewClient(client, entityManager);
            JOptionPane.showMessageDialog(null, "Le client <<"+client.getNom()+">> a été bien ajouter!", "success", JOptionPane.INFORMATION_MESSAGE);
            
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modifyClient(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JTextField jTextField5,
            JTextField jTextField6,
            JButton jButton,
            String idClient,
            EntityManager entityManager) throws ClientNotExistException {
        jButton.setEnabled(false);
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[A-Za-z]+$") 
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jTextField5.getText().matches("^[A-Za-z]+$") && jTextField6.getText().matches("^0\\d{9}$")                 
                ) {
            Client client = iDaoClientImpl.findClientById(idClient, entityManager);
            client.setNom(jTextField1.getText());
            client.setPrenom(jTextField2.getText());
            client.setAdresse(jTextField3.getText());
            client.setVille(jTextField4.getText());
            client.setPays(jTextField5.getText());
            client.setTelephone(jTextField6.getText());
            
            iDaoClientImpl.modifyClient(client, entityManager);
            JOptionPane.showMessageDialog(null, "Le client <<"+client.getNom()+">> a été bien modifier!", "success", JOptionPane.INFORMATION_MESSAGE);        
                    
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);            
        }
    }

    public void allClient(JTable jTable, EntityManager entityManager) {
        List<Client> clients = iDaoClientImpl.findAllClients(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Client client : clients) {
            Object[] rowData = {
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),
                client.getVille(),
                client.getPays(),
                client.getTelephone()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void clearJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
    }

    public void refresh(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        allClient(jTable, entityManager);
    }

    public void fillJTable(JTable jTable, List<Client> clients) {
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Client client : clients) {
            Object[] rowData = {
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),
                client.getVille(),
                client.getPays(),
                client.getTelephone()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void removeClient(String id, EntityManager entityManager) throws ClientNotExistException {
        iDaoClientImpl.removeClient(
                iDaoClientImpl.findClientById(id, entityManager),
                 entityManager);
    }

    public List<Client> fetchClientLike(String str, EntityManager entityManager) {
        TypedQuery<Client> clientLike = entityManager.createNamedQuery("Client.findClientLike", Client.class);
        clientLike.setParameter("id", "%" + str + "%");
        clientLike.setParameter("nom", "%" + str + "%");
        clientLike.setParameter("prenom", "%" + str + "%");
        clientLike.setParameter("adresse", "%" + str + "%");
        clientLike.setParameter("ville", "%" + str + "%");
        clientLike.setParameter("pays", "%" + str + "%");
        clientLike.setParameter("telephone", "%" + str + "%");
        return clientLike.getResultList();
    }

    public void fillJComboBox(JComboBox jComboBox, EntityManager entityManager) {
        jComboBox.removeAllItems();
        TypedQuery<Client> query = entityManager.createNamedQuery("Client.findAll", Client.class);
        List<String> result = query
                .getResultList()
                .stream()
                .map(c -> c.getVille())
                .distinct()
                .toList();       
        for (String client : result) {
            jComboBox.addItem(client);
        }
    }

    public List<Client> fetchClientByVille(String str, EntityManager entityManager) {
        TypedQuery<Client> query = entityManager.createNamedQuery("Client.findClientByVille", Client.class);
        query.setParameter("ville", str);
        return query.getResultList().stream().distinct().toList();
    }
}
