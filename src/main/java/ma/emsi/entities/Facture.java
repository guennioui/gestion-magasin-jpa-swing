package ma.emsi.entities;

import jakarta.persistence.*;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numFacture;
    @OneToOne
    private LigneCommande ligneCommande;

    public Facture() {
    }

    public String getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "numFacture='" + numFacture + '\'' +
                ", ligneCommande=" + ligneCommande +
                '}';
    }
}
