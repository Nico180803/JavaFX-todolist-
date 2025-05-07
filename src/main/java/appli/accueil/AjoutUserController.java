package appli.accueil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Utilisateur;
import repository.TacheRepository;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjoutUserController  implements Initializable {

    @FXML
    private ComboBox<Utilisateur> listeUtilisateurChoice;

    @FXML
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public void initialize(URL location, ResourceBundle resources) {
        List<Utilisateur> utilisateur = utilisateurRepository.getAllutilisateur();
        listeUtilisateurChoice.getItems().addAll(utilisateur);
    }


}
