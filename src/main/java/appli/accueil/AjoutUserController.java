package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Liste;
import model.Utilisateur;
import repository.TacheRepository;
import repository.UtilisateurRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjoutUserController  implements Initializable {

    @FXML
    private ComboBox<Utilisateur> listeUtilisateurChoice;

    @FXML
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    Liste liste;

    public void initData(Liste liste){
        this.liste = liste;
    }

    public void initialize(URL location, ResourceBundle resources) {
        List<Utilisateur> utilisateur = utilisateurRepository.getAllutilisateur();
        listeUtilisateurChoice.getItems().addAll(utilisateur);
    }

    @FXML
    void OnListeUtilsateurChoiceClick(ActionEvent event) throws IOException {
        Utilisateur utilisateur = listeUtilisateurChoice.getSelectionModel().getSelectedItem();
        utilisateurRepository.ajouterListe(utilisateur,liste);
        StartApplication.changeScene("accueil/Liste");
    }


}
