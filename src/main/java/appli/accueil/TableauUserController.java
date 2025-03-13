package appli.accueil;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TableauUserController implements Initializable {
    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                { "Id Utilisateur","id" },
                { "Nom","nom" },
                { "Prénom","prenom" },
                { "Email","email" },
               // { "Rôle","role" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Utilisateur, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            tableView.getColumns().add(maCol);
        }
        tableView.getItems().addAll(utilisateurRepository.getAllutilisateur());
    }
}
