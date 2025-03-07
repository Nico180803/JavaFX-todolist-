package model;

public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String role;

    public Utilisateur(int id, String nom, String prenom, String email, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = password;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = password;
        this.role = role;
    }

    public Utilisateur(String email, String password) {
        this.email = email;
        this.mdp = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + mdp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String password) {
        this.mdp = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
