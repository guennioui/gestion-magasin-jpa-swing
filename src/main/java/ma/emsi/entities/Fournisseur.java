package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

@NamedQueries(
        @NamedQuery(
                name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f"
        )
)
@Entity
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numFournisseur;
    private String nom;
    private String adresse;
    private String ville;
    @OneToMany
    @JoinColumn(name = "id_fournisseur")
    private List<Livraison> livraisons;
    @OneToMany
    @JoinColumn(name = "id_fournisseur")
    private List<SocieteDistribution> societeDistributions;

    public Fournisseur() {
    }

    public String getNumFournisseur() {
        return numFournisseur;
    }

    public void setNumFournisseur(String numFournisseur) {
        this.numFournisseur = numFournisseur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Livraison> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(List<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public List<SocieteDistribution> getSocieteDistributions() {
        return societeDistributions;
    }

    public void setSocieteDistributions(List<SocieteDistribution> societeDistributions) {
        this.societeDistributions = societeDistributions;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "numFournisseur='" + numFournisseur + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", livraisons=" + livraisons +
                ", societeDistributions=" + societeDistributions +
                '}';
    }
}
