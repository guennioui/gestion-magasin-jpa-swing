package ma.emsi.entities;

import jakarta.persistence.*;
import ma.emsi.primaryKeys.PkOfLigneCommande;

import java.io.Serializable;

@Entity
public class LigneCommande implements Serializable{
    @EmbeddedId
    private PkOfLigneCommande numero;
    private int quantite;

    public LigneCommande() {
    }

    public LigneCommande(PkOfLigneCommande numero, int quantite) {
        this.numero = numero;
        this.quantite = quantite;
    }

    public PkOfLigneCommande getNumero() {
        return numero;
    }

    public void setNumero(PkOfLigneCommande numero) {
        this.numero = numero;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "numero=" + numero +
                ", quantite=" + quantite +
                '}';
    }
}
