package ma.emsi.entities;

import jakarta.persistence.*;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numCategorie;
    @Column(unique = true)
    private String nomCategorie;
    @OneToMany(mappedBy = "categorie")    
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
