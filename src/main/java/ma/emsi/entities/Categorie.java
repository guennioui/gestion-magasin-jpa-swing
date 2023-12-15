package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

@NamedQueries(
        @NamedQuery(
                name = "Categorie.findAll", query = "SELECT c from Categorie c"
        )
)
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numCategorie;
    private String nomCategorie;
    @OneToMany
    @JoinColumn(name = "categorie_id")
    private List<Article> articles;

    public Categorie() {
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

    @Override
    public String toString() {
        return "Categorie{" +
                "numCategorie='" + numCategorie + '\'' +
                ", nomCategorie='" + nomCategorie + '\'' +
                ", articles=" + articles +
                '}';
    }
}
