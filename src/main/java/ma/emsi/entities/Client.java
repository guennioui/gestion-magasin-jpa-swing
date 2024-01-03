package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findClientLike", query = "SELECT c FROM Client c WHERE c.id LIKE :id OR c.nom LIKE :nom OR c.prenom LIKE :prenom OR c.adresse LIKE :adresse OR c.ville LIKE :ville OR c.pays LIKE :pays OR c.telephone LIKE :telephone")
})
public class Client {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;
    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Commande> commandes;
    static int nbClient;

    public Client() {
        ++nbClient;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public String generateId(){
        return "CL-"+this.nom+"-"+this.prenom.substring(0,3)+"-"+nbClient;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", telephone='" + telephone + '\'' +
                ", commandes=" + commandes +
                '}';
    }
}
