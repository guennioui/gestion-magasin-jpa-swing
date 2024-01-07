/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.dto;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import ma.emsi.primaryKeys.PkOfLigneCommande;

/**
 *
 * @author abdoe
 */
public class CommandeDetailsDTO {
    private String idCommande; 
    private String idArticle;
    private String nomArticle;
    private int quantity;

    public CommandeDetailsDTO(String idCommande, String idArticle, String nomArticle, int quantity) {
        this.idCommande = idCommande;
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.quantity = quantity;
    }  
    
    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public String toString() {
        return "CommandeDetailsDTO{" + "idCommande=" + idCommande + ", idArticle=" + idArticle + ", nomArticle=" + nomArticle + ", quantity=" + quantity + '}';
    }                  
}
