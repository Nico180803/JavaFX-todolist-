package model;

public class Liste {
    private int idListe;
    private String nom;

    public Liste(int id_liste, String nom) {
        this.idListe = id_liste;
        this.nom = nom;
    }

    public Liste(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }
}
