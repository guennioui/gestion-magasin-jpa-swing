package ma.emsi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Livraison.findAll", query = "SELECT l FROM Livraison l"
        )
})
@Entity
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numeroLivraison;
    private LocalDateTime dateLivraison;
    @OneToMany
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

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "numeroLivraison='" + numeroLivraison + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", ligneLivraisons=" + ligneLivraisons +
                '}';
    }
}
