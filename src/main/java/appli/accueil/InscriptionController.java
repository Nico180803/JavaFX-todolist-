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

import java.io.IOException;

public class InscriptionController {

    public Label erreur;
    @FXML
    private TextField MdpConfirmationTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Label mdpConfirmationLabel;

    @FXML
    private TextField mdpConfirmationPasswordField;

    @FXML
    private Label mdpLabel;

    @FXML
    private PasswordField mdpPasswordField;

    @FXML
    private Label nomLabel;

    @FXML
    private TextField nomTextField;

    @FXML
    private Label prenomLabel;

    @FXML
    private TextField prenomTextField;

    @FXML
    private Button retourButton;

    @FXML
    void onInscriptionClick(ActionEvent event) throws IOException {
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        if (!mdpPasswordField.getText().equals(mdpConfirmationPasswordField.getText())||emailTextField.getText().isEmpty()||nomTextField.getText().isEmpty()||prenomTextField.getText().isEmpty()||mdpPasswordField.getText().isEmpty()||mdpConfirmationPasswordField.getText().isEmpty()) {
            System.out.println("Erreur, les champs ne sont pas tous renseigné");
            erreur.setText("Erreur, les champs ne sont pas tous renseigné");
        } else if (utilisateurRepository.verifUser(emailTextField.getText())) {
            System.out.println("Erreur le mail existe déja");
            erreur.setText("Erreur, le mail existe déja");
        } else {
            Utilisateur utilisateur = new Utilisateur(nomTextField.getText(), prenomTextField.getText(), emailTextField.getText(), mdpPasswordField.getText());
            utilisateurRepository.ajouterUtilisateur(utilisateur);
            StartApplication.changeScene("accueil/Login");
        }
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login");
    }

}
