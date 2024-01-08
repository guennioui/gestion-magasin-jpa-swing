package ma.emsi.entities;

import jakarta.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.Random;

@NamedQueries({
    @NamedQuery(
            name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f"
    ),
    @NamedQuery(
            name = "Fournisseur.findFournisseurLike", query = "SELECT f FROM Fournisseur f WHERE f.numFournisseur LIKE :numFournisseur OR f.nom LIKE :nom OR f.prenom LIKE :prenom OR f.adresse LIKE :adresse OR f.ville LIKE :ville OR f.telephone LIKE :telephone")
})
@Entity
public class Fournisseur {

    @Id
    private String numFournisseur;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String telephone;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fournisseur")    
    private List<Livraison> livraisons;
    @OneToMany(mappedBy = "fournisseur")
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private Serializable generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(100);
        return timestamp * 100 + random;
    }

    public String generateId() {
        return "FR-" + this.nom + "-" + this.prenom.substring(0, 3) + "-"+generateUniqueId();
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "numFournisseur=" + numFournisseur + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", ville=" + ville + ", telephone=" + telephone + ", livraisons=" + livraisons + ", societeDistributions=" + '}';
    }

}
