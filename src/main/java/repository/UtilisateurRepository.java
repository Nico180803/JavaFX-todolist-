package repository;

import database.Database;
import jdk.jshell.execution.Util;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.ArrayList;

public class UtilisateurRepository {

    private Connection connexion;

    public UtilisateurRepository() {
        connexion = Database.getConnexion();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String sqlDeux = "INSERT INTO utilisateur (nom, prenom, email, mdp) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sqlDeux);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, encoder.encode(utilisateur.getMdp()));
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";

        Utilisateur utilisateur = null;
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id_user"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation de l'utilisateur : " + e.getMessage());
        }

        return utilisateur;
    }

    public ArrayList<Utilisateur> getAllutilisateur() {
        ArrayList<Utilisateur> utilisateur = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Utilisateur utilisateurUn = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"));
                utilisateur.add(utilisateurUn);
            }

        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateur : " + e.getMessage());
        }
        return utilisateur;
    }

    public void supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM utilisateur WHERE email = ?";
        try{
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Compte bien supprimé");
        }catch (SQLException e){
            System.out.println("Erreur lors de la suppression du compte : " + e.getMessage());
        }
    }

    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, mdp = ? WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getMdp());
            stmt.setString(4, utilisateur.getEmail());
            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Erreur lors de la modification du compte : " + e.getMessage());
        }
    }

    public Utilisateur connecterUser(String email, String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String mdp = null;
        Utilisateur utilisateur = null;

        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                mdp =rs.getString("mdp");
                System.out.println(mdp);
            }else{
                return null;
            }
        }catch (SQLException e){
            System.out.println("erreur dans la récupération de l'email");
        }

        if (encoder.matches(password,mdp)) {
            String sqlDeux = "SELECT * FROM utilisateur WHERE email = ? AND mdp = ?";
            try {
                PreparedStatement stmt = connexion.prepareStatement(sqlDeux);
                stmt.setString(1, email);
                stmt.setString(2, mdp);

                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    utilisateur = new Utilisateur(rs.getInt("id_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"));
                    System.out.println("Connection reussi");
                    return utilisateur;
                }else {
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la connexion du compte : " + e.getMessage());
                return null;
            }
        }else {
            System.out.println("Mot de passe incorrect");
            return null;
        }
    }

    public boolean verifUser(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la connexion du compte : " + e.getMessage());
            return true;
        }
    }
}

