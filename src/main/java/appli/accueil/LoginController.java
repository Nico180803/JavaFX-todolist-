package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void onConnexionClick(ActionEvent event) {
        if (mdpPasswordField.getText().equals("Azerty1234") && emailTextField.getText().equals("mail@mail.fr")) {
            System.out.println("Connexion Reussi");
        }else {
            System.out.println("echec");
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
