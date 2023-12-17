package ma.emsi.primaryKeys;

import jakarta.persistence.Embeddable;
import ma.emsi.entities.Article;
import ma.emsi.entities.Commande;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PkOfLigneCommande implements Serializable{
    private String id_commande;
    private String id_article;

    public PkOfLigneCommande() {
    }

    public PkOfLigneCommande(String id_commande, String id_article) {
        this.id_commande = id_commande;
        this.id_article = id_article;
    }

    public String getId_commande() {
        return id_commande;
    }

    public void setId_commande(String id_commande) {
        this.id_commande = id_commande;
    }

    public String getId_article() {
        return id_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PkOfLigneCommande that = (PkOfLigneCommande) o;
        return Objects.equals(id_commande, that.id_commande) && Objects.equals(id_article, that.id_article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_commande, id_article);
    }

    @Override
    public String toString() {
        return "PkOfLigneCommande{" +
                "id_commande='" + id_commande + '\'' +
                ", id_article='" + id_article + '\'' +
                '}';
    }
}
