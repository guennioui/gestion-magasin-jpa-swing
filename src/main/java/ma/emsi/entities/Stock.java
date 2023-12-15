package ma.emsi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ma.emsi.primaryKeys.PkOfStock;

import java.time.LocalDateTime;

@Entity
public class Stock {
    @Id
    private PkOfStock stockId;
    private LocalDateTime dateDepot;

    private int quantite;

    public Stock() {
    }

    public PkOfStock getStockId() {
        return stockId;
    }

    public void setStockId(PkOfStock stockId) {
        this.stockId = stockId;
    }

    public LocalDateTime getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDateTime dateDepot) {
        this.dateDepot = dateDepot;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId='" + stockId + '\'' +
                ", dateDepot=" + dateDepot +
                ", quantite=" + quantite +
                '}';
    }
}
