package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.*;
import session.*;


public class AccueilController {

    @FXML
    private Button verifButton;

    @FXML
    private Button deconnexionButton;

    @FXML
    void onVerifClick(ActionEvent event) {
        Utilisateur utilisateurActuel = SessionUtilisateur.getInstance().getUtilisateur();

        if (utilisateurActuel != null) {
            System.out.println("Utilisateur connecté : " + utilisateurActuel.getNom());
        }
    }

    @FXML
    void onDecoClick(ActionEvent event) {
        handleLogout();
    }
    @FXML
    protected void handleLogout() {
        SessionUtilisateur.getInstance().deconnecter();
        System.out.println("Utilisateur déconnecté.");
// Redirection vers la page de connexion
    }
}
