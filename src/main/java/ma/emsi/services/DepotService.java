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
import ma.emsi.IDao.IDaoDepot;
import ma.emsi.IDaoImpl.IDaoDepotImpl;
import ma.emsi.entities.Client;
import ma.emsi.entities.Depot;
import ma.emsi.exceptions.CategorieNotExistException;
import ma.emsi.exceptions.DepotNotFoundException;

/**
 *
 * @author abdoe
 */
public class DepotService {

    IDaoDepot iDaoDepot = new IDaoDepotImpl();

    public void addDepot(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            EntityManager entityManager
    ) {
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[a-zA-Z0-9]+$")
                && jTextField3.getText().matches("^[A-Za-z]+$") && jTextField4.getText().matches("^0\\d{9}$")) {
            Depot depot = new Depot();
            depot.setNomDepot(jTextField1.getText());
            depot.setAdresse(jTextField2.getText());
            depot.setVille(jTextField3.getText());
            depot.setTelephone(jTextField4.getText());

            iDaoDepot.addNewDepot(depot, entityManager);
            JOptionPane.showMessageDialog(null, "Le depot <<" + depot.getNomDepot() + ">> a été bien ajouter!", "success", JOptionPane.INFORMATION_MESSAGE);

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modifyDepot(
            JTextField jTextField1,
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,
            JButton jButton,
            String idDepot,
            EntityManager entityManager) throws DepotNotFoundException {
        jButton.setEnabled(false);
        if (jTextField1.getText().matches("^[A-Za-z]+$") && jTextField2.getText().matches("^[a-zA-Z0-9]+$")
                && jTextField3.getText().matches("^[A-Za-z]+$") && jTextField4.getText().matches("^0\\d{9}$")) {
            Depot depot = iDaoDepot.findDepotById(idDepot, entityManager);
            depot.setNomDepot(jTextField1.getText());
            depot.setAdresse(jTextField2.getText());
            depot.setVille(jTextField3.getText());
            depot.setTelephone(jTextField4.getText());

            iDaoDepot.modifyDepot(depot, entityManager);
            JOptionPane.showMessageDialog(null, "Le depot <<" + depot.getNomDepot() + ">> a été bien modifier!", "success", JOptionPane.INFORMATION_MESSAGE);

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Toutes les champs sont obligatoires!", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void allDepot(JTable jTable, EntityManager entityManager) {
        List<Depot> depots = iDaoDepot.findAllDepot(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Depot depot : depots) {
            Object[] rowData = {
                depot.getNumeroDepot(),
                depot.getNomDepot(),
                depot.getAdresse(),
                depot.getVille(),
                depot.getTelephone()
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
        allDepot(jTable, entityManager);
    }

    public void fillJTable(JTable jTable, List<Depot> depots) {
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (Depot depot : depots) {
            Object[] rowData = {
                depot.getNumeroDepot(),
                depot.getNomDepot(),
                depot.getAdresse(),
                depot.getVille(),
                depot.getTelephone()
            };
            model.addRow(rowData);
        }
        jTable.setModel(model);
    }

    public void removeDepot(String id, EntityManager entityManager) throws DepotNotFoundException {
        iDaoDepot.removeDepot(
                iDaoDepot.findDepotById(id, entityManager),
                entityManager);
    }

    public List<Depot> fetchDepotLike(String str, EntityManager entityManager) {
        TypedQuery<Depot> DepotLike = entityManager.createNamedQuery("Depot.findDepotLike", Depot.class);
        DepotLike.setParameter("numeroDepot", "%" + str + "%");
        DepotLike.setParameter("nomDepot", "%" + str + "%");
        DepotLike.setParameter("ville", "%" + str + "%");
        DepotLike.setParameter("adresse", "%" + str + "%");
        DepotLike.setParameter("telephone", "%" + str + "%");
        return DepotLike.getResultList();
    }

    public void fillJComboBox(JComboBox jComboBox, EntityManager entityManager) {
        jComboBox.removeAllItems();
        TypedQuery<Depot> query = entityManager.createNamedQuery("Depot.findAll", Depot.class);
        List<String> result = query
                .getResultList()
                .stream()
                .map(d -> d.getVille())
                .distinct()
                .toList();       
        for (String depot : result) {
            jComboBox.addItem(depot);
        }
    }

    public List<Depot> fetchDepotByVille(String str, EntityManager entityManager) {
        TypedQuery<Depot> query = entityManager.createNamedQuery("Depot.findVilleLike", Depot.class);
        query.setParameter("ville", str);
        return query.getResultList().stream().distinct().toList();
    }
}
