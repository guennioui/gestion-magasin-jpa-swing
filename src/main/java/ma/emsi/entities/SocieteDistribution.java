package ma.emsi.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Random;

@NamedQuery(name = "SocieteDistribution.findAll", query = "SELECT S FROM SocieteDistribution S")
@NamedQuery(name = "SocieteDistribution.findSocieteDistributionLike", query = "SELECT s FROM SocieteDistribution s WHERE s.id LIKE :id OR s.nom LIKE :nom OR s.telephone LIKE :telephone OR s.ville LIKE :ville OR s.adresse LIKE :adresse")
@Entity
public class SocieteDistribution {

    @Id
    private String id;
    private String nom;
    private String telephone;
    private String ville;
    private String adresse;
    @ManyToOne
    private Fournisseur fournisseur;

    public SocieteDistribution() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    private Serializable generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(10);
        return timestamp * 10 + random;
    }

    public String generateId() {
        return "STE-" + this.nom + "-" + this.nom + "-" + generateUniqueId();
    }

    @Override
    public String toString() {
        return "SocieteDistribution{" + "id=" + id + ", nom=" + nom + ", telephone=" + telephone + ", ville=" + ville + ", adresse=" + adresse + ", fournisseur=" + fournisseur + '}';
    }

}
