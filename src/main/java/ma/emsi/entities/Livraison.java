package ma.emsi.entities;

import jakarta.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@NamedQueries({
    @NamedQuery(
            name = "Livraison.findAll", query = "SELECT l FROM Livraison l"
    )
})
@Entity
public class Livraison {

    @Id
    private String numeroLivraison;
    private LocalDate dateLivraison;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "id_livraison")
    private List<LigneLivraison> ligneLivraisons;

    public Livraison() {
    }

    public String getNumeroLivraison() {
        return numeroLivraison;
    }

    public void setNumeroLivraison(String numeroLivraison) {
        this.numeroLivraison = numeroLivraison;
    }

    public List<LigneLivraison> getLigneLivraisons() {
        return ligneLivraisons;
    }

    public void setLigneLivraisons(List<LigneLivraison> ligneLivraisons) {
        this.ligneLivraisons = ligneLivraisons;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    private Serializable generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(100);
        return timestamp * 100 + random;
    }

    public String generateId() {
        return "Liv_" + this.dateLivraison + "-" +generateUniqueId();
    }

    @Override
    public String toString() {
        return "Livraison{"
                + "numeroLivraison='" + numeroLivraison + '\''
                + ", dateLivraison=" + dateLivraison
                + ", ligneLivraisons=" + ligneLivraisons
                + '}';
    }
}
