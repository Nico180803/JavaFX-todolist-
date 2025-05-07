package repository;

import database.Database;
import model.Liste;
import model.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheRepository {

    private Connection connection;

    public TacheRepository() {
        connection = Database.getConnexion();
    }

    public Tache ajouterTache(Tache tache) {
        String sql = "INSERT INTO tache(nom,etat,ref_liste,ref_type) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tache.getNom());
            ps.setInt(2, tache.getEtat());
            ps.setInt(3, tache.getRefListe());
            ps.setInt(4, tache.getRefType());
            ps.executeUpdate();
            ResultSet res = ps.getGeneratedKeys();
            if (res.next()) {
                tache.setIdTache(res.getInt(1));
            }
            System.out.println("Ajout de la tache " + tache.getNom() + " reussi");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la tache : " + e.getMessage());
        }
        return tache;
    }

    public List<Tache> recupererTache(Liste liste) {
        ArrayList<Tache> listeTache = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE ref_liste=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, liste.getIdListe());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idTache = rs.getInt("idTache");
                String nom = rs.getString("nom");
                int etat = rs.getInt("etat");
                int refListe = rs.getInt("refListe");
                int refType = rs.getInt("refType");
                Tache tache = new Tache(idTache, nom, etat, refListe, refType);
                listeTache.add(tache);
            }

        } catch (SQLException e) {
            System.out.printf("Erreur lors de la récupération de la tache " + e.getMessage());
        }
        return listeTache;
    }

    public void supprimerTache(Tache tache) {
        String sql = "DELETE FROM tache WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur lors de la supprimer la tache: " + e.getMessage());
        }
    }

    public void modifierTache(Tache tache) {
     String sql = "UPDATE tache SET nom = ?, etat = ? WHERE id_tache = ?";
     try{
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setString(1,tache.getNom());
         ps.setInt(2,tache.getEtat());
         ps.setInt(3,tache.getIdTache());
         ps.executeUpdate();

     }catch (SQLException e){
         System.out.println("Erreur lors de la modifier la tache: " + e.getMessage());
     }
    }

    public List<Tache> recupererTaches(int refListe){
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE ref_liste = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,refListe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tache tache = new Tache(rs.getInt("id_tache"),rs.getString("nom"),rs.getInt("etat"),rs.getInt("ref_liste"),rs.getInt("ref_type"));
                taches.add(tache);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupérations des taches: " + e.getMessage());
        }
        System.out.println(taches);
        return taches;
    }
}
