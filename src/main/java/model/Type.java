package model;

public class Type {
    private int idType;
    private String nom;
    private String code_couleur;

    public Type(int idType, String nom, String code_couleur) {
        this.idType = idType;
        this.nom = nom;
        this.code_couleur = code_couleur;
    }

    public Type(String nom, String code_couleur) {
        this.nom = nom;
        this.code_couleur = code_couleur;
    }

    public int getidType() {
        return idType;
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
