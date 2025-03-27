package repository;

import database.Database;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository {
    private Connection connexion;

    public TypeRepository() {
        connexion = Database.getConnexion();
    }

    public Type ajouterType(Type type) {

        String query = "INSERT INTO type(nom,code_couleur) VALUES (?,?)";

        String sql2 = "SELECT LAST_INSERT_ID()";
        try {
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setString(1, type.getNom());
            ps.setString(2, type.getCodeCouleur());
            ps.executeUpdate();
            ps.close();

            ps = connexion.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            type.setIdType(id);

            System.out.println("Ajout du type "+type.getNom()+" reussi");
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du type : "+e.getMessage());
        }
        return type;
    }

    public void supprimerType(Type type) {
        String query = "DELETE FROM type WHERE id_type = ?";
        try {
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, type.getIdType());
            ps.executeUpdate();
            System.out.println("Suppression du type "+type.getIdType()+" reussi");

        }catch (SQLException e){
            System.out.println("Erreur lors de l'ajout du type : "+e.getMessage());
        }
    }

    public List<Type> getAllType() {
        ArrayList<Type>types= new ArrayList<>();
        String query = "SELECT * FROM type";
        try {
            PreparedStatement ps = connexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idType = rs.getInt("id_type");
                String nom = rs.getString("nom");
                String codeCouleur = rs.getString("code_couleur");
                Type type = new Type(idType, nom, codeCouleur);
                types.add(type);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation des types : "+e.getMessage());
        }
        return types;
    }
}
