package repository;

import database.Database;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TypeRepository {
    private Connection connexion;

    public TypeRepository() {
        connexion = Database.getConnexion();
    }

    public void ajouterType(Type type) {
        String query = "INSERT INTO types(nom,code_couleur) VALUES (?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setString(1, type.getNom());
            ps.setString(2, type.getCodeCouleur());
            ps.executeUpdate();

            System.out.println("Ajout du type "+type.getNom()+" reussi");
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du type : "+e.getMessage());
        }
    }
}
