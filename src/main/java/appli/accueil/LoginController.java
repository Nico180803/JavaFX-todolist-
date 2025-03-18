package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class LoginController {

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public Label erreur;
    @FXML
    private Button connexionButton;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Label mdpLabel;

    @FXML
    private Button mdpOublieButton;

    @FXML
    private PasswordField mdpPasswordField;

    @FXML
    void onConnexionClick(ActionEvent event) throws IOException {
        Utilisateur utilisateur = utilisateurRepository.connecterUser(emailTextField.getText(), mdpPasswordField.getText());
        if (utilisateur != null) {
            System.out.println("Connexion réussie pour : " + utilisateur.getNom());
            SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);
            erreur.setVisible(false);
            StartApplication.changeScene("accueil/Profil");
        }else{
            System.out.println("Échec de la connexion. Email ou mot de passe incorrect.");
            erreur.setText("Email ou mot de passe incorrect.");
            erreur.setVisible(true);

        }
    }

    @FXML
    void onInscriptionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Inscription");
    }

    @FXML
    void onMdpOublieClick(ActionEvent event) {

    }

}
