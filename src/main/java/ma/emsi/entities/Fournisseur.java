package ma.emsi.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Fournisseur {
    private String numFournisseur;
    private String nom;
    private String adresse;
    private String ville;
    @OneToMany
    @JoinColumn(name = "id_fournisseur")
    private List<Livraison> livraisons;

    @OneToMany
    @JoinColumn(name = "id_fournisseur")
    private List<Fournisseur> fournisseurs;

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

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "numFournisseur='" + numFournisseur + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", livraisons=" + livraisons +
                ", fournisseurs=" + fournisseurs +
                '}';
    }
}
