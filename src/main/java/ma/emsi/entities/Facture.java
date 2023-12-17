package ma.emsi.entities;

import jakarta.persistence.*;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numFacture;
    @OneToOne
    private Commande commande;

    public Facture() {
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "numFacture='" + numFacture + '\'' +
                ", commande=" + commande +
                '}';
    }
}
