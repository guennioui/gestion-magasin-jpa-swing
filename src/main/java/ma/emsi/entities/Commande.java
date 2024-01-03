package ma.emsi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate dateCommande;
    private BigDecimal montant;
    @OneToOne(mappedBy = "commande")
    private Facture facture;    
    @OneToMany(cascade = CascadeType.REMOVE)
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

    public LocalDate getDateCommende() {
        return dateCommande;
    }

    public void setDateCommende(LocalDate dateCommande) {
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
    public BigDecimal generateMontant(){
        return this.montant = ligneCommandes
                        .stream()
                        .map(LC -> LC.getArticle().getPrixUnitaire().multiply(BigDecimal.valueOf(LC.getQuantite())))               
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
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
