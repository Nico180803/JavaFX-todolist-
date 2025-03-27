package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import appli.service.EmailService;
import javafx.scene.text.Text;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class MotDePasseOublieController {

    @FXML
    public TextField newMdpField;

    @FXML
    public Button modifierButton;

    @FXML
    private TextField codeField;

    @FXML
    private Button confirmerButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button envoyerButton;

    @FXML
    private Button retourButton;

    @FXML
    private Text textInfo;

    private String code;

    private String email;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    void OnConfirmerClick(ActionEvent event) {
        if (codeField.getText().matches(code)) {
            newMdpField.visibleProperty().setValue(true);
            modifierButton.visibleProperty().setValue(true);
        }
        else {
            textInfo.visibleProperty().setValue(true);
            System.out.println("Code incorrect");
        }
    }
    @FXML
    void OnEnvoyerClick(ActionEvent event) {
        envoyerCode();
    }

    @FXML
    void OnRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    void OnModifierClick(ActionEvent event) throws IOException {
        Utilisateur utilisateur = utilisateurRepository.getUtilisateurParEmail(email);
        utilisateur.setMdp(newMdpField.getText());
        utilisateurRepository.mettreAJourUtilisateur(utilisateur);
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    private void envoyerCode() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            System.out.println("Veuillez entrer une adresse e-mail.");
            return;
        }
        if (utilisateurRepository.getUtilisateurParEmail(email) != null) {
            String code = EmailService.genererCode();

            this.code = code;
            this.email = email;

            EmailService.envoyerEmail(email, "Réinitialisation de mot de passe", "Votre code de " +
                    "réinitialisation est : " + code);
            System.out.println("Code envoyé à : " + email);

            codeField.visibleProperty().setValue(true);
            confirmerButton.visibleProperty().setValue(true);
        }
        else {
            System.out.println("Le mail n'existe pas");
        }
    }
}

