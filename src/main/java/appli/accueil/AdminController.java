package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Type;
import model.Utilisateur;
import repository.TypeRepository;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    public Button ajouterTypeButton;

    @FXML
    private TableView <Type> typeTableView;

    @FXML
    private TableView<Utilisateur> userTableView;

    @FXML
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    private TypeRepository typeRepository = new TypeRepository();

    @FXML
    private Button supprimerTypeButton;

    @FXML
    private Button deconnexionButton;

    @FXML
    private Button supprimerUserButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                { "Id Utilisateur","id" },
                { "Nom","nom" },
                { "Prénom","prenom" },
                { "Email","email" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Utilisateur, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            userTableView.getColumns().add(maCol);
        }
        userTableView.getItems().addAll(utilisateurRepository.getAllutilisateur());

//TABLEAU DES TYPES : //

        String [][] colonnesType = {
                { "Id Type","idType" },
                { "Nom","nom" },
                { "Code Couleur","CodeCouleur" },
        };

        for ( int i = 0 ; i < colonnesType.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Type, String> maCol2 = new TableColumn<>(colonnesType[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol2.setCellValueFactory(new PropertyValueFactory<Type, String>(colonnesType[i][1]));

//Ajout de la colonne dans notre tableau
            typeTableView.getColumns().add(maCol2);
        }
        typeTableView.getItems().addAll(typeRepository.getAllType());
    }

    @FXML
    void OnSupprimerTypeClick(ActionEvent event) {
        typeRepository.supprimerType(typeTableView.getSelectionModel().getSelectedItem());
        typeTableView.getItems().remove(typeTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void OnSupprimerUserClick(ActionEvent event) {
        utilisateurRepository.supprimerUtilisateur(userTableView.getSelectionModel().getSelectedItem());
        userTableView.getItems().remove(userTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void OnDeconnexionClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    public void OnAjouterTypeClick(ActionEvent actionEvent) {
    }
}
