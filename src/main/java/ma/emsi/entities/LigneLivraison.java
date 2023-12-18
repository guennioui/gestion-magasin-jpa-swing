package ma.emsi.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ma.emsi.primaryKeys.PkOfLigneLivraison;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class LigneLivraison implements Serializable {
    @EmbeddedId
    private PkOfLigneLivraison numeroLigneLivraison;
    private LocalDateTime dateLivraison;
    private int quantiteLivrer;

    public LigneLivraison() {
    }

    public LigneLivraison(PkOfLigneLivraison numeroLigneLivraison, LocalDateTime dateLivraison, int quantiteLivrer) {
        this.numeroLigneLivraison = numeroLigneLivraison;
        this.dateLivraison = dateLivraison;
        this.quantiteLivrer = quantiteLivrer;
    }

    public PkOfLigneLivraison getNumeroLigneLivraison() {
        return numeroLigneLivraison;
    }

    public void setNumeroLigneLivraison(PkOfLigneLivraison numeroLigneLivraison) {
        this.numeroLigneLivraison = numeroLigneLivraison;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getQuantiteLivrer() {
        return quantiteLivrer;
    }

    public void setQuantiteLivrer(int quantiteLivrer) {
        this.quantiteLivrer = quantiteLivrer;
    }

    @Override
    public String toString() {
        return "LigneLivraison{" +
                "numeroLigneLivraison='" + numeroLigneLivraison + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", quantiteLivrer=" + quantiteLivrer +
                '}';
    }
}
