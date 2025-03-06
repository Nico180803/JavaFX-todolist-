package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void onInscriptionClick(ActionEvent event) {
        if (emailTextField.getText().equals("mail@mail.fr")||mdpPasswordField.getText().equals(mdpConfirmationPasswordField.getText())||emailTextField.getText().isEmpty()||nomTextField.getText().isEmpty()||prenomTextField.getText().isEmpty()||mdpPasswordField.getText().isEmpty()||mdpConfirmationPasswordField.getText().isEmpty()) {
            System.out.println("Erreur");
            erreur.setText("Erreur");
        }else {
            System.out.println("Inscripiton reussite");
        }
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login");
    }

}
