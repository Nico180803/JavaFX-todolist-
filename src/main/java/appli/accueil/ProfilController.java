package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Liste;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {

    @FXML
    private TableView<Liste> tableView;
    @FXML
    private ListeRepository listeRepository = new ListeRepository();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                { "Id Liste","idListe" },
                { "Nom","nom" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Liste, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Liste, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            tableView.getColumns().add(maCol);
        }
        tableView.getItems().addAll(listeRepository.recupererListes());
    }

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
        liste = listeRepository.ajouterListe(liste);
        if (liste.getIdListe() == 0){
            System.out.println("oups...");
        }else {
            tableView.getItems().add(liste);
        }


    }

    @FXML
    void OnPageAdminButtonClick(ActionEvent event) {

    }

}
