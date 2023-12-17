package ma.emsi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQueries(
        @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
)
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numero;
    private LocalDateTime dateCommande;
    private BigDecimal montant;
    @OneToOne(mappedBy = "commande")
    private Facture facture;

    @OneToMany
    @JoinColumn(name = "id_Commande")
    private List<LigneCommande> ligneCommandes;

    public Commande() {
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getDateCommende() {
        return dateCommande;
    }

    public void setDateCommende(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
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
                ", dateCommende=" + dateCommande +
                ", montant=" + montant +
                '}';
    }
}
