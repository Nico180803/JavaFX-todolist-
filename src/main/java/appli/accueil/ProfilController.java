package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import session.SessionUtilisateur;

import java.io.IOException;

public class ProfilController {

    @FXML
    private Button deconnexionButton;

    @FXML
    private Button nouvelleListeButton;

    @FXML
    void OnDeconnexionClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    void OnNouvelleListeClick(ActionEvent event) {

    }

}
