package ma.emsi.entities;

import jakarta.persistence.*;

@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numero;
    private int quantite;

    @OneToOne(mappedBy = "ligneCommande")
    private Facture facture;

    public LigneCommande() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "numero='" + numero + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
