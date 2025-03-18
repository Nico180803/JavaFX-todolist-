package model;

public class Tache {
    private int id_tache;
    private String nom;
    private int etat;
    private int ref_liste;
    private int ref_type;

    public Tache(int id, String nom, int etat, int ref_liste, int ref_type) {
        this.id_tache = id;
        this.nom = nom;
        this.etat = etat;
        this.ref_liste = ref_liste;
        this.ref_type = ref_type;
    }

    public Tache(String nom, int etat) {
        this.nom = nom;
        this.etat = etat;
    }

    public int getId_tache() {
        return id_tache;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getRef_liste() {
        return ref_liste;
    }

    public void setRef_liste(int ref_liste) {
        this.ref_liste = ref_liste;
    }

    public int getRef_type() {
        return ref_type;
    }

    public void setRef_type(int ref_type) {
        this.ref_type = ref_type;
    }
}
