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
import ma.emsi.IDao.IDaoDepot;
import ma.emsi.IDaoImpl.IDaoDepotImpl;
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
            ){
        if(!jTextField1.getText().equals("") && !jTextField2.getText().equals("")
                && !jTextField3.getText().equals("") && !jTextField4.getText().equals("")){
            Depot depot = new Depot();
            depot.setNomDepot(jTextField1.getText());
            depot.setAdresse(jTextField2.getText());
            depot.setVille(jTextField3.getText());
            depot.setTelephone(jTextField4.getText());
            iDaoDepot.addNewDepot(depot, entityManager);
            jTextField1.setText("");                                
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
        }else{
             JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    public void modifyDepot(
            JTextField jTextField1,       
            JTextField jTextField2,
            JTextField jTextField3,
            JTextField jTextField4,           
            String idDepot,            
            EntityManager entityManager)throws DepotNotFoundException{
         if(!jTextField1.getText().equals("") && !jTextField2.getText().equals("")
                && !jTextField3.getText().equals("") && !jTextField4.getText().equals("")){
            Depot depot = iDaoDepot.findDepotById(idDepot, entityManager);
            depot.setNomDepot(jTextField1.getText());
            depot.setAdresse(jTextField2.getText());
            depot.setVille(jTextField3.getText());
            depot.setTelephone(jTextField4.getText());
            iDaoDepot.modifyDepot(depot, entityManager);
            jTextField1.setText("");                                
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Verifier vos entrer", "erreur", JOptionPane.ERROR_MESSAGE); 
        }                              
    }
    
    public void allDepot(JTable jTable, EntityManager entityManager){          
        List<Depot> depots = iDaoDepot.findAllDepot(entityManager);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();         
        for(Depot depot : depots){
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
    
    public void clearJTable(JTable jTable){
    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
    model.setRowCount(0);
    }  
    public void refresh(JTable jTable, EntityManager entityManager){
    clearJTable(jTable);
    allDepot(jTable, entityManager);
    }   
    
    public void fillJTable(JTable jTable, List<Depot> depots){                
        clearJTable(jTable);
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();        
        for(Depot depot : depots){
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
    
    public void removeDepot(String id, EntityManager entityManager)throws DepotNotFoundException{
        iDaoDepot.removeDepot(
                iDaoDepot.findDepotById(id, entityManager),
                entityManager);                         
    }     
    
    public List<Depot> fetchDepotLike(String str, EntityManager entityManager){
        TypedQuery<Depot> DepotLike = entityManager.createNamedQuery("Depot.findDepotLike", Depot.class);
        DepotLike.setParameter("numeroDepot", "%"+str+"%");
        DepotLike.setParameter("nomDepot", "%"+str+"%");
        DepotLike.setParameter("ville", "%"+str+"%");   
        DepotLike.setParameter("adresse", "%"+str+"%");
        DepotLike.setParameter("telephone", "%"+str+"%");   
        return DepotLike.getResultList();
    }    
}
