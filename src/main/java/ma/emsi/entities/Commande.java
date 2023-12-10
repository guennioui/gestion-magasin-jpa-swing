package ma.emsi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numero;
    private LocalDateTime dateCommende;
    private BigDecimal montant;

    @OneToMany
    @JoinColumn(name = "id_Commande")
    private List<LigneCommande> ligneCommandes;

    public Commande() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getDateCommende() {
        return dateCommende;
    }

    public void setDateCommende(LocalDateTime dateCommende) {
        this.dateCommende = dateCommende;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numero='" + numero + '\'' +
                ", dateCommende=" + dateCommende +
                ", montant=" + montant +
                '}';
    }
}
