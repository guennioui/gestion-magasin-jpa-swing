package ma.emsi.entities;

import jakarta.persistence.*;
import ma.emsi.primaryKeys.PkOfLigneCommande;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class LigneCommande implements Serializable{
    @EmbeddedId
    private PkOfLigneCommande numero;
    private int quantite;
    private BigDecimal montantLigne;
    @ManyToOne
    private Article article;
    
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

    public BigDecimal getMontantLigne() {
        return montantLigne;
    }

    public void setMontantLigne(BigDecimal montantLigne) {
        this.montantLigne = montantLigne;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    
    
    @Override
    public String toString() {
        return "LigneCommande{" +
                "numero=" + numero +
                ", quantite=" + quantite +
                '}';
    }
}
