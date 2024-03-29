/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoFournisseur;
import ma.emsi.IDaoImpl.IDaoFournisseurImpl;
import ma.emsi.entities.Client;
import ma.emsi.entities.Fournisseur;
import ma.emsi.exceptions.ClientNotExistException;
import ma.emsi.exceptions.FournisseurNotExistException;

/**
 *
 * @author abdoe
 */
public class FournisseurService {

    IDaoFournisseurImpl iDaoFournisseurImpl = new IDaoFournisseurImpl();

    public void addFournisseur(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JTextField jTextField5,
            EntityManager entityManager) {
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[A-Za-z]+$")
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jTextField5.getText().matches("^0\\d{9}$")) {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setNom(jTextField1.getText());
            fournisseur.setPrenom(jTextField2.getText());
            fournisseur.setAdresse(jTextField3.getText());
            fournisseur.setVille(jTextField4.getText());
            fournisseur.setTelephone(jTextField5.getText());

            iDaoFournisseurImpl.addNewFournisseur(fournisseur, entityManager);
            JOptionPane.showMessageDialog(null, "Le fournissseur <<" + fournisseur.getNom() + ">> a été bien ajouter!", "success", JOptionPane.INFORMATION_MESSAGE);

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modifyFournisseur(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JTextField jTextField5,
            JButton jButton,
            String idFournisseur,
            EntityManager entityManager) throws FournisseurNotExistException {
        jButton.setEnabled(false);
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[A-Za-z]+$")
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jTextField5.getText().matches("^0\\d{9}$")) {
            Fournisseur fournisseur = iDaoFournisseurImpl.findFournisseurById(idFournisseur, entityManager);
            fournisseur.setNom(jTextField1.getText());
            fournisseur.setPrenom(jTextField2.getText());
            fournisseur.setAdresse(jTextField3.getText());
            fournisseur.setVille(jTextField4.getText());
            fournisseur.setTelephone(jTextField5.getText());

            iDaoFournisseurImpl.modifyFournisseur(fournisseur, entityManager);
            JOptionPane.showMessageDialog(null, "Le fournisseur <<" + fournisseur.getNom() + ">> a été bien modifier!", "success", JOptionPane.INFORMATION_MESSAGE);

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allFournisseur(JTable jTable, EntityManager entityManager) {
        List<Fournisseur> fournisseurs = iDaoFournisseurImpl.findAllFournisseurs(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Fournisseur fournisseur : fournisseurs) {
            Object[] rowData = {
                fournisseur.getNumFournisseur(),
                fournisseur.getNom(),
                fournisseur.getPrenom(),
                fournisseur.getAdresse(),
                fournisseur.getVille(),
                fournisseur.getTelephone()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void removeFournisseur(String id, EntityManager entityManager) throws FournisseurNotExistException {
        iDaoFournisseurImpl.removeFournisseur(
                iDaoFournisseurImpl.findFournisseurById(id, entityManager),
                entityManager);
    }

    public void clearJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
    }

    public void refresh(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        allFournisseur(jTable, entityManager);
    }

    public void fillJTable(JTable jTable, List<Fournisseur> fournisseurs) {
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Fournisseur fournisseur : fournisseurs) {
            Object[] rowData = {
                fournisseur.getNumFournisseur(),
                fournisseur.getNom(),
                fournisseur.getPrenom(),
                fournisseur.getAdresse(),
                fournisseur.getVille(),
                fournisseur.getTelephone()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public List<Fournisseur> fetchFournisseurLike(String str, EntityManager entityManager) {
        TypedQuery<Fournisseur> fournisseurLike = entityManager.createNamedQuery("Fournisseur.findFournisseurLike", Fournisseur.class);
        fournisseurLike.setParameter("numFournisseur", "%" + str + "%");
        fournisseurLike.setParameter("nom", "%" + str + "%");
        fournisseurLike.setParameter("prenom", "%" + str + "%");
        fournisseurLike.setParameter("adresse", "%" + str + "%");
        fournisseurLike.setParameter("ville", "%" + str + "%");
        fournisseurLike.setParameter("telephone", "%" + str + "%");
        return fournisseurLike.getResultList();
    }

    public void fillJComboBox(JComboBox jComboBox, EntityManager entityManager) {
        jComboBox.removeAllItems();
        TypedQuery<Fournisseur> query = entityManager.createNamedQuery("Fournisseur.findAll", Fournisseur.class);
        List<String> result = query
                .getResultList()
                .stream()
                .map(F -> F.getVille())
                .distinct()
                .toList();       
        for (String fournisseur : result) {
            jComboBox.addItem(fournisseur);
        }
    }

    public List<Fournisseur> fetchFournisseurByVille(String str, EntityManager entityManager) {
        TypedQuery<Fournisseur> query = entityManager.createNamedQuery("Fournisseur.findVilleLike", Fournisseur.class);
        query.setParameter("ville", str);
        return query.getResultList();
    }
}
