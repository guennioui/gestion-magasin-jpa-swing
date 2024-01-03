package ma.emsi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@NamedQueries({
    @NamedQuery(
                name = "Artcile.findArticleLike", query = "SELECT a from Article a WHERE a.code LIKE :code OR a.nom LIKE :nom OR a.prixUnitaire = :prixUnitaire"
        ),
    @NamedQuery(
                name = "Artcile.findAll", query = "SELECT a FROM Article a"
    )
}
        
)
@Entity
public class Article {
    @Id
    private String code;
    @Column(unique = true)
    private String nom;
    private BigDecimal prixUnitaire;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_article")
    private List<LigneCommande> ligneCommandes;
    @OneToMany
    @JoinColumn(name = "id_article")
    private List<Stock> stocks;
    @OneToMany
    @JoinColumn(name = "id_article")
    private List<LigneLivraison> ligneLivraisons;
    @ManyToOne
    private Categorie categorie;
    static int nbArticle;

    public Article() {
        ++nbArticle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<LigneLivraison> getLigneLivraisons() {
        return ligneLivraisons;
    }

    public void setLigneLivraisons(List<LigneLivraison> ligneLivraisons) {
        this.ligneLivraisons = ligneLivraisons;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public String generateid(){
        return "ART-"+this.nom+"-"+nbArticle;
    }
    @Override
    public String toString() {
        return "Article{" + "code=" + code + ", nom=" + nom + ", prixUnitaire=" + prixUnitaire + ", ligneCommandes=" + ligneCommandes + ", stocks=" + stocks + ", ligneLivraisons=" + ligneLivraisons + '}';
    }

    
}

