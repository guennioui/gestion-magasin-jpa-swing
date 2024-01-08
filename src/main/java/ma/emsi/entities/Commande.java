package ma.emsi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.Fetch;

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
    @OneToMany
    @JoinColumn(name = "id_commande")    
    private List<LigneCommande> ligneCommandes;
    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;
    
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

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
