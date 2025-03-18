package repository;

import database.Database;
import model.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TacheRepository {

    private Connection connection;

    public TacheRepository() {
        connection = Database.getConnexion();
    }

    public void ajouterTache(Tache tache) {
        String sql = "INSERT INTO taches(nom,etat,ref_liste,ref_type) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tache.getNom());
            ps.setInt(2,tache.getEtat());
            ps.setInt(3,tache.getrefListe());
            ps.setInt(4,tache.getrefType());
            ps.executeUpdate();

            System.out.println("Ajout de la tache "+ tache.getNom()+" reussi");
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la tache : " + e.getMessage());
        }
    }
}
