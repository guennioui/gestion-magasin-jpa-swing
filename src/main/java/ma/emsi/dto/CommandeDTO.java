/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import ma.emsi.entities.Client;
/**
 *
 * @author abdoe
 */
public class CommandeDTO {
    private String idCommande;
    private LocalDate dateCommande;
    private BigDecimal montantCommande;
    private String idClient;
    private String telephone;
    

    public CommandeDTO() {
    }

    public CommandeDTO(String idCommande, LocalDate dateCommande, BigDecimal montantCommande, String idClient, String telephone) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.montantCommande = montantCommande;
        this.idClient = idClient;
        this.telephone = telephone;
    }
    
    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public BigDecimal getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(BigDecimal montantCommande) {
        this.montantCommande = montantCommande;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "CommandeDTO{" + "idCommande=" + idCommande + ", dateCommande=" + dateCommande + ", montantCommande=" + montantCommande + ", idClient=" + idClient + ", telephone=" + telephone + '}';
    }    
}
