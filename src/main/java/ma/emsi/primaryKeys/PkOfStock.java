package ma.emsi.primaryKeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PkOfStock implements Serializable {
    private String id_article;
    private String id_Depot;

    public PkOfStock() {
    }

    public PkOfStock(String id_article, String id_Depot) {
        this.id_article = id_article;
        this.id_Depot = id_Depot;
    }

    public String getId_article() {
        return id_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    public String getId_Depot() {
        return id_Depot;
    }

    public void setId_Depot(String id_Depot) {
        this.id_Depot = id_Depot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PkOfStock pkOfStock = (PkOfStock) o;
        return Objects.equals(id_article, pkOfStock.id_article) && Objects.equals(id_Depot, pkOfStock.id_Depot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_article, id_Depot);
    }

    @Override
    public String toString() {
        return "PkOfStock{" +
                "id_article='" + id_article + '\'' +
                ", id_Depot='" + id_Depot + '\'' +
                '}';
    }
}
