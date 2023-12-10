package ma.emsi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LigneLivraison {
    @Id
    private String numeroLigneLivraison;
    private LocalDateTime dateLivraison;
    private int quantiteLivrer;

    public LigneLivraison() {
    }

    public String getNumeroLigneLivraison() {
        return numeroLigneLivraison;
    }

    public void setNumeroLigneLivraison(String numeroLigneLivraison) {
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
