package ma.emsi.entities;

import jakarta.persistence.*;

@NamedQuery(name = "SocieteDistribution.findAll", query = "SELECT S FROM SocieteDistribution S")
@Entity
public class SocieteDistribution {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nom;
    private String telephone;
    private String ville;
    private String adresse;

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

    @Override
    public String toString() {
        return "SocieteDistribution{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
