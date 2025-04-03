package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class ModificationUserController {

    @FXML
    private Button confirmerModif;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private Utilisateur utilisateurSel;

    public void initData(Utilisateur utilisateur) {
        this.utilisateurSel = utilisateur;
        nomTextField.setText(utilisateur.getNom());
        prenomTextField.setText(utilisateur.getPrenom());
        emailTextField.setText(utilisateur.getEmail());
    }

    @FXML
    void OnConfirmerClick(ActionEvent event) throws IOException {
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        utilisateurSel.setNom(emailTextField.getText());
        utilisateurSel.setPrenom(prenomTextField.getText());
        utilisateurSel.setEmail(emailTextField.getText());
        utilisateurRepository.mettreAJourUtilisateurParId(utilisateurSel);
        StartApplication.changeScene("accueil/Admin");
    }

}

