package ma.emsi.entities;

import jakarta.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.Random;

@NamedQueries({
    @NamedQuery(
            name = "Depot.findAll", query = "SELECT d FROM Depot d"
    ),
    @NamedQuery(
            name = "Depot.findDepotLike", query = "SELECT d FROM Depot d WHERE d.numeroDepot LIKE :numeroDepot OR d.nomDepot LIKE :nomDepot OR d.ville LIKE :ville OR d.adresse LIKE :adresse OR d.telephone LIKE :telephone"
    )
})
@Entity
public class Depot {

    @Id
    private String numeroDepot;
    private String nomDepot;
    private String ville;
    private String adresse;
    private String telephone;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_depot")
    private List<Stock> stocks;

    public Depot() {        
    }

    public String getNumeroDepot() {
        return numeroDepot;
    }

    public void setNumeroDepot(String numeroDepot) {
        this.numeroDepot = numeroDepot;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomDepot() {
        return nomDepot;
    }

    public void setNomDepot(String nomDepot) {
        this.nomDepot = nomDepot;
    }

    private Serializable generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(100);
        return timestamp * 100 + random;
    }

    public String generateId() {
        return this.nomDepot + "-" + this.ville + "_N-" + generateUniqueId();
    }

    @Override
    public String toString() {
        return "Depot{" + "numeroDepot=" + numeroDepot + ", nomDepot=" + nomDepot + ", ville=" + ville + ", adresse=" + adresse + ", telephone=" + telephone + ", stocks=" + stocks + '}';
    }

}
