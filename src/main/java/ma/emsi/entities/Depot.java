package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numeroDepot;
    private String nomDepot;
    private String ville;
    private String adresse;
    private String telephone;    
    @OneToMany
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

    @Override
    public String toString() {
        return "Depot{" + "numeroDepot=" + numeroDepot + ", nomDepot=" + nomDepot + ", ville=" + ville + ", adresse=" + adresse + ", telephone=" + telephone + ", stocks=" + stocks + '}';
    }  
        
}
