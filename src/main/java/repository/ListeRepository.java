package repository;

import database.Database;
import model.Liste;
import model.Utilisateur;
import session.SessionUtilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListeRepository {

    private Connection connexion;

    public ListeRepository() {
        connexion = Database.getConnexion();
    }

    public Liste ajouterListe(Liste liste) {

        String sql = "INSERT INTO liste(nom) VALUES (?)";

        String sql2 = "SELECT LAST_INSERT_ID()";

        String sql3 = "INSERT INTO utilisateur_liste(ref_utilisateur,ref_liste) VALUES (?,?)";

        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setString(1, liste.getNom());
            ps.executeUpdate();
            ps.close();

            ps = connexion.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            liste.setIdListe(id);
            ps.close();

            ps = connexion.prepareStatement(sql3);
            ps.setInt(1, SessionUtilisateur.getInstance().getUtilisateur().getId());
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();

            System.out.println("Ajout de la la liste "+liste.getNom()+" reussi !");

        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de liste "+e.getMessage());
        }
        return liste;
    }

    public List<Liste> recupererListes() {
        ArrayList<Liste> listes = new ArrayList<>();

        String sql = "SELECT * "+
                     "FROM liste "+
                     "INNER JOIN utilisateur_liste ON liste.id_liste = utilisateur_liste.ref_liste "+
                     "WHERE utilisateur_liste.ref_utilisateur = ?";

        try {
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setInt(1, SessionUtilisateur.getInstance().getUtilisateur().getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt(1), rs.getString(2));
                listes.add(liste);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperer liste "+e.getMessage());
        }
        return listes;
    }
}
