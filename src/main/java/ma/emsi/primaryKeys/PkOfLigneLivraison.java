package ma.emsi.primaryKeys;

import jakarta.persistence.Embeddable;
import ma.emsi.entities.Article;
import ma.emsi.entities.Commande;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PkOfLigneLivraison implements Serializable {
    private String id_article;
    private String id_livraison;

    public PkOfLigneLivraison() {
    }

    public PkOfLigneLivraison(String id_article, String id_livraison) {
        this.id_article = id_article;
        this.id_livraison = id_livraison;
    }

    public String getId_article() {
        return id_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    public String getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(String id_livraison) {
        this.id_livraison = id_livraison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PkOfLigneLivraison that = (PkOfLigneLivraison) o;
        return Objects.equals(id_article, that.id_article) && Objects.equals(id_livraison, that.id_livraison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_article, id_livraison);
    }

    @Override
    public String toString() {
        return "PkOfLigneLivraison{" +
                "id_article='" + id_article + '\'' +
                ", id_livraison='" + id_livraison + '\'' +
                '}';
    }
}
