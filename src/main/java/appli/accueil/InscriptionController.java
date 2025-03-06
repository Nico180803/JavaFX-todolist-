package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InscriptionController {

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
        if (emailTextField.getText().equals("mail@mail.fr")){
            System.out.println("Erreur");
        }else {
            System.out.println("Inscripiton reussite");
        }
    }

    @FXML
    void onRetourClick(ActionEvent event) {

    }

}
