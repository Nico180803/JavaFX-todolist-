package model;

public class Type {
    private int id_type;
    private String nom;
    private String code_couleur;

    public Type(int id_type, String nom, String code_couleur) {
        this.id_type = id_type;
        this.nom = nom;
        this.code_couleur = code_couleur;
    }

    public Type(String nom, String code_couleur) {
        this.nom = nom;
        this.code_couleur = code_couleur;
    }

    public int getId_type() {
        return id_type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode_couleur() {
        return code_couleur;
    }

    public void setCode_couleur(String code_couleur) {
        this.code_couleur = code_couleur;
    }
}
