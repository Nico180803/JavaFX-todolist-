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

        String sql = "INSERT INTO utilisateurs (nom, prenom, email, mdp, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, encoder.encode(utilisateur.getMdp()));
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";

        Utilisateur utilisateur = null;
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                utilisateur = new Utilisateur(rs.getInt("id_user"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mdp"),rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation de l'utilisateur : " + e.getMessage());
        }

        return utilisateur;
    }

    public ArrayList<Utilisateur> getAllUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getString("role"));
                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }

    public void supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM utilisateurs WHERE email = ?";
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
        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, mdp = ?, role = ? WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getMdp());
            stmt.setString(4, utilisateur.getRole());
            stmt.setString(5, utilisateur.getEmail());
            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Erreur lors de la modification du compte : " + e.getMessage());
        }
    }

    public boolean connecterUser(String email, String password) {

        //recup mdp encrypter avec SELECT p
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "SELECT * FROM utilisateurs WHERE email = ? AND mdp = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, encoder.matches(password));
            ResultSet rs = stmt.executeQuery();
            System.out.println("Connection reussi");
            return true;
        }catch (SQLException e){
            System.out.println("Erreur lors de la connexion du compte : " + e.getMessage());
            return false;
        }
    }

    public boolean verifUser(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";
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

