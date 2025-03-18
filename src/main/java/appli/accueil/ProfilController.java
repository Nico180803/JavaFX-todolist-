package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Liste;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class ProfilController {

    @FXML
    private Button deconnexionButton;

    @FXML
    private Button modifierProfilButton;

    @FXML
    private Button nouvelleListeButton;

    @FXML
    private Button pageAdminButton;

    @FXML
    private TextField nomListeTextField;

    @FXML
    void OnDeconnexionClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    void OnModfierProfilClick(ActionEvent event) {

    }

    @FXML
    void OnNouvelleListeClick(ActionEvent event) {
        Liste liste = new Liste(nomListeTextField.getText());
        ListeRepository listeRepository = new ListeRepository();
        listeRepository.ajouterListe(liste);
    }

    @FXML
    void OnPageAdminButtonClick(ActionEvent event) {

    }

}
