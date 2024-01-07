package ma.emsi.entities;

import jakarta.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.Random;

@NamedQueries({
    @NamedQuery(
            name = "Categorie.findAll", query = "SELECT c from Categorie c"
    ),
    @NamedQuery(
            name = "Categorie.findCategorieLike", query = "SELECT c from Categorie c where c.nomCategorie LIKE :nomCategorie"
    )
})
@Entity
public class Categorie {

    @Id
    private String numCategorie;
    @Column(unique = true)
    private String nomCategorie;
    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Article> articles;

    public Categorie() {
        this.numCategorie = generateId();
    }

    public String getNumCategorie() {
        return numCategorie;
    }

    public void setNumCategorie(String numCategorie) {
        this.numCategorie = numCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private Serializable generateUniqueId() {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(10);
        return timestamp * 10 + random;
    }

    public String generateId() {
        return "CAT-" + this.nomCategorie + "-" + generateUniqueId();
    }

    @Override
    public String toString() {
        return "Categorie{"
                + "numCategorie='" + numCategorie + '\''
                + ", nomCategorie='" + nomCategorie + '\''
                + ", articles=" + articles
                + '}';
    }
}
