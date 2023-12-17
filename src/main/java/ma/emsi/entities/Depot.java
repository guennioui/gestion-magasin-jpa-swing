package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Depot.findAll", query = "SELECT d FROM Depot d"
        )
})
@Entity
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numeroDepot;
    private String ville;
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

    @Override
    public String toString() {
        return "Depot{" +
                "numeroDepot='" + numeroDepot + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", stocks=" + stocks +
                '}';
    }
}
