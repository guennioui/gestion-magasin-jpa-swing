package ma.emsi.test;

public class clt {
    private String id;
    private String prenom;
    private String nom;
    private String telephone;

    public clt() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String generateId(){
       return this.id = this.nom+"-"+this.prenom.substring(0,3)+""+this.telephone.substring(7,10);
    }

    @Override
    public String toString() {
        return "clt{" +
                "id='" + id + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
