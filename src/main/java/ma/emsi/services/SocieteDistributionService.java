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
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jComboBox1.getSelectedIndex() != -1) {
            SocieteDistribution societeDistribution = new SocieteDistribution();
            societeDistribution.setNom(jTextField1.getText());
            societeDistribution.setTelephone(jTextField2.getText());
            societeDistribution.setVille(jTextField4.getText());
            societeDistribution.setAdresse(jTextField3.getText());
            Fournisseur fournisseur = iDaoFournisseur.findFournisseurById(
                    jComboBox1.getSelectedItem().toString(),
                    entityManager);
            if (fournisseur != null) {
                iDaoSocieteDistribution.addNewSocieteDistribution(societeDistribution, entityManager);
                iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution, fournisseur, entityManager);
                JOptionPane.showMessageDialog(null, "La societer <<" + societeDistribution.getNom() + ">> a été bien ajouter!", "success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'insertion!", "erreur", JOptionPane.ERROR_MESSAGE);
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
            JButton jButton,
            String idSociete,
            EntityManager entityManager)
            throws FournisseurNotExistException,
            SocieteDistributionNotExistException {
        jButton.setEnabled(false);
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^0\\d{9}$")
                && jTextField3.getText().matches("^[a-zA-Z0-9]+$") && jTextField4.getText().matches("^[A-Za-z]+$")
                && jComboBox1.getSelectedIndex() != -1) {
            SocieteDistribution societeDistribution = iDaoSocieteDistribution.findSocieteById(idSociete, entityManager);
            if (societeDistribution != null) {
                societeDistribution.setNom(jTextField1.getText());
                societeDistribution.setTelephone(jTextField2.getText());
                societeDistribution.setVille(jTextField4.getText());
                societeDistribution.setAdresse(jTextField3.getText());
                Fournisseur fournisseur = iDaoFournisseur.findFournisseurById(
                        jComboBox1.getSelectedItem().toString(),
                        entityManager);
                if (fournisseur != null) {
                    iDaoSocieteDistribution.modifySocieteDistribution(societeDistribution, entityManager);
                    iDaoSocieteDistribution.addFournisseurToSocieteDistribution(societeDistribution, fournisseur, entityManager);
                    JOptionPane.showMessageDialog(null, "La societer <<" + societeDistribution.getNom() + ">> a été bien modifier!", "success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors du modification", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(-1);
            jButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allSocieteDistribution(JTable jTable, EntityManager entityManager) {
        List<SocieteDistribution> societeDistributions = iDaoSocieteDistribution.FindAllSocieteDistribution(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (SocieteDistribution societeDistribution : societeDistributions) {
            Object[] rowData = {
                societeDistribution.getId(),
                societeDistribution.getNom(),
                societeDistribution.getAdresse(),
                societeDistribution.getVille(),
                societeDistribution.getTelephone(),
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
                societeDistribution.getAdresse(),
                societeDistribution.getVille(),
                societeDistribution.getTelephone(),
                societeDistribution.getFournisseur().getNom()
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

    public void fillVilleJComboBox(JComboBox jComboBox, EntityManager entityManager) {
        jComboBox.removeAllItems();
        TypedQuery<SocieteDistribution> query = entityManager.createNamedQuery("SocieteDistribution.findAll", SocieteDistribution.class);
        List<String> result = query
                .getResultList()
                .stream()
                .map(s -> s.getVille())
                .distinct()
                .toList();
        for (String societeDistribution : result) {
            jComboBox.addItem(societeDistribution);
        }
    }

    public List<SocieteDistribution> fetchSocieteByVille(String str, EntityManager entityManager) {
        TypedQuery<SocieteDistribution> query = entityManager.createNamedQuery("SocieteDistribution.findVilleLike", SocieteDistribution.class);
        query.setParameter("ville", str);
        return query.getResultList();
    }
}
