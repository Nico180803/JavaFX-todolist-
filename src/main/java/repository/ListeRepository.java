package repository;

import database.Database;
import model.Liste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListeRepository {

    private Connection connexion;

    public ListeRepository() {
        connexion = Database.getConnexion();
    }

    public void ajouterListe(Liste liste) {

        String sql = "INSERT INTO liste(nom) VALUES (?)";

        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setString(1, liste.getNom());
            ps.executeUpdate();
            System.out.println("Ajout de la la liste "+liste.getNom()+" reussi !");
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de liste");
        }
    }

}
