package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repository.UtilisateurRepository;

import java.io.IOException;

public class LoginController {

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
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        boolean connexion = utilisateurRepository.connecterUser(emailTextField.getText(), mdpPasswordField.getText());
        if (connexion) {
            StartApplication.changeScene("accueil/Test");
        }else{
            System.out.println("echec connexion");
            erreur.setText("Erreur de connexion");
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
