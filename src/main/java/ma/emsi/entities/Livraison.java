package ma.emsi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Livraison {
    @Id
    private String numeroLivraison;
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

    @Override
    public String toString() {
        return "Livraison{" +
                "numeroLivraison='" + numeroLivraison + '\'' +
                ", ligneLivraisons=" + ligneLivraisons +
                '}';
    }
}
