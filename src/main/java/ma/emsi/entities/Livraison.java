package ma.emsi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private String numeroLivraison;
    private LocalDate dateLivraison;
    @OneToMany
    @JoinColumn(name = "id_livraison")
    private List<LigneLivraison> ligneLivraisons;
    static int nbLivraison;

    public Livraison() {
        ++nbLivraison;
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
    public String generateId(){
        return "Liv_"+this.dateLivraison+"_N-"+nbLivraison;
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
