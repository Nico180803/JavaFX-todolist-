package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Type;
import model.Utilisateur;
import repository.ListeRepository;
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
    public Button modifierTypeButton;

    @FXML
    public TextField codeCouleurModifTypeTextField;

    @FXML
    public TextField nomModifTypeTextField;
    public Button confirmerModifierTypeButton;

    @FXML
    private TextField codeCouleurTypeTextField;

    @FXML
    private TextField nomTypeTextField;

    @FXML
    private TableView<Type> typeTableView;

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

        this.confirmerModifierTypeButton.setDisable(true);
        this.nomModifTypeTextField.setDisable(true);
        this.codeCouleurModifTypeTextField.setDisable(true);

        String[][] colonnes = {
                {"Id Utilisateur", "id"},
                {"Nom", "nom"},
                {"Prénom", "prenom"},
                {"Email", "email"},
        };

        for (int i = 0; i < colonnes.length; i++) {

//Création de la colonne avec le titre
            TableColumn<Utilisateur, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            userTableView.getColumns().add(maCol);
        }
        userTableView.getItems().addAll(utilisateurRepository.getAllutilisateur());

//TABLEAU DES TYPES : //

        String[][] colonnesType = {
                {"Id Type", "idType"},
                {"Nom", "nom"},
                {"Code Couleur", "CodeCouleur"},
        };

        for (int i = 0; i < colonnesType.length; i++) {

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
    void OnModifierTypeClick(ActionEvent event) {
       this.confirmerModifierTypeButton.setDisable(false);
       this.nomModifTypeTextField.setDisable(false);
       this.codeCouleurModifTypeTextField.setDisable(false);
    }


    @FXML
    void OnConfirmerModifierTypeClick(ActionEvent event) {
        Type type = typeTableView.getSelectionModel().getSelectedItem();
        type.setNom(nomModifTypeTextField.getText());
        type.setCodeCouleur(codeCouleurModifTypeTextField.getText());
        typeRepository.modifierType(type);

        this.confirmerModifierTypeButton.setDisable(true);
        this.nomModifTypeTextField.setDisable(true);
        this.codeCouleurModifTypeTextField.setDisable(true);

        this.typeTableView.refresh();
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
        Type type = new Type(nomTypeTextField.getText(), codeCouleurTypeTextField.getText());
        TypeRepository typeRepository = new TypeRepository();
        type = typeRepository.ajouterType(type);
        if (type.getIdType() == 0) {
            System.out.println("oups...");
        } else {
            typeTableView.getItems().add(type);
        }
    }

    @FXML
    void OnTableViewPressed(MouseEvent event) throws IOException {
        Utilisateur selection = userTableView.getSelectionModel().getSelectedItem();

        if (event.getClickCount() == 2) { // Vérifie si c'est un double-clic
            if (selection != null) {
                StartApplication.changeScene("accueil/ModificationUser");
                ModificationUserController controller = (ModificationUserController)
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }
        }
    }
}
