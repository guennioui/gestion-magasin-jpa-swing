/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import ma.emsi.IDao.IDaoSocieteDistribution;
import ma.emsi.IDaoImpl.IDaoFournisseurImpl;
import ma.emsi.IDaoImpl.IDaoSocieteDistributionImpl;
import ma.emsi.entities.Fournisseur;
import ma.emsi.entities.SocieteDistribution;
import ma.emsi.exceptions.FournisseurNotExistException;
import ma.emsi.exceptions.SocieteDistributionNotExistException;

/**
 *
 * @author abdoe
 */
public class SocieteDistributionService {

    IDaoSocieteDistribution iDaoSocieteDistribution = new IDaoSocieteDistributionImpl();
    IDaoFournisseurImpl iDaoFournisseur = new IDaoFournisseurImpl();

    public void addSocieteDistribution(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JComboBox jComboBox1,
            EntityManager entityManager)
            throws FournisseurNotExistException,
            SocieteDistributionNotExistException {
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^0\\d{9}$")
                && jTextField3.getText().matches("^[A-Za-z]+$") && jTextField4.getText().matches("^[a-zA-Z0-9]+$")) {
            SocieteDistribution societeDistribution = new SocieteDistribution();
            societeDistribution.setNom(jTextField1.getText());
            societeDistribution.setTelephone(jTextField2.getText());
            societeDistribution.setVille(jTextField3.getText());
            societeDistribution.setAdresse(jTextField4.getText());
            Fournisseur fournisseur = iDaoFournisseur.findFournisseurById(
                    jComboBox1.getSelectedItem().toString(),
                    entityManager);
            if (fournisseur != null) {
                iDaoSocieteDistribution.addNewSocieteDistribution(societeDistribution, entityManager);
                iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution, fournisseur, entityManager);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Une erreur est survenue lors de l'operation", "erreur", JOptionPane.ERROR_MESSAGE);
            }
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modifySocieteDistribution(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JComboBox jComboBox1,
            String idSociete,
            EntityManager entityManager)
            throws FournisseurNotExistException,
            SocieteDistributionNotExistException {
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^0\\d{9}$")
                && jTextField3.getText().matches("^[A-Za-z]+$") && jTextField4.getText().matches("^[a-zA-Z0-9]+$")) {
            SocieteDistribution societeDistribution = iDaoSocieteDistribution.findSocieteById(idSociete, entityManager);
            if (societeDistribution != null) {
                societeDistribution.setNom(jTextField1.getText());
                societeDistribution.setTelephone(jTextField2.getText());
                societeDistribution.setVille(jTextField3.getText());
                societeDistribution.setAdresse(jTextField4.getText());
                Fournisseur fournisseur = iDaoFournisseur.findFournisseurById(
                        jComboBox1.getSelectedItem().toString(),
                        entityManager);
                if (fournisseur != null) {
                    iDaoSocieteDistribution.modifySocieteDistribution(societeDistribution, entityManager);
                    iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution, fournisseur, entityManager);
                } else {
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'operation", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allSocieteDistribution(JTable jTable, EntityManager entityManager) {
        List<SocieteDistribution> societeDistributions = iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (SocieteDistribution societeDistribution : societeDistributions) {
            Object[] rowData = {
                societeDistribution.getId(),
                societeDistribution.getNom(),
                societeDistribution.getTelephone(),
                societeDistribution.getVille(),
                societeDistribution.getAdresse(),
                societeDistribution.getFournisseur().getNom()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void removeSocieteDistribution(String id, EntityManager entityManager) throws SocieteDistributionNotExistException {
        iDaoSocieteDistribution.removeSocieteDistribution(
                iDaoSocieteDistribution.findSocieteById(id, entityManager),
                entityManager);
    }

    public void clearJTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
    }

    public void refresh(JTable jTable, EntityManager entityManager) {
        clearJTable(jTable);
        allSocieteDistribution(jTable, entityManager);
    }

    public void fillJTable(JTable jTable, List<SocieteDistribution> societeDistributions) {
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (SocieteDistribution societeDistribution : societeDistributions) {
            Object[] rowData = {
                societeDistribution.getId(),
                societeDistribution.getNom(),
                societeDistribution.getTelephone(),
                societeDistribution.getVille(),
                societeDistribution.getAdresse()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public List<SocieteDistribution> fetchSocieteDistributionLike(String str, EntityManager entityManager) {
        TypedQuery<SocieteDistribution> societeDistributionLike = entityManager.createNamedQuery("SocieteDistribution.findSocieteDistributionLike", SocieteDistribution.class);
        societeDistributionLike.setParameter("id", "%" + str + "%");
        societeDistributionLike.setParameter("nom", "%" + str + "%");
        societeDistributionLike.setParameter("ville", "%" + str + "%");
        societeDistributionLike.setParameter("adresse", "%" + str + "%");
        societeDistributionLike.setParameter("telephone", "%" + str + "%");
        return societeDistributionLike.getResultList();
    }

    public void fillJComboBox(JComboBox jComboBox, EntityManager entityManager) {
        List<Fournisseur> fournisseurs = iDaoFournisseur.findAllFournisseurs(entityManager);
        for (Fournisseur fournisseur : fournisseurs) {
            jComboBox.addItem(fournisseur.getNumFournisseur());
        }
    }
}
