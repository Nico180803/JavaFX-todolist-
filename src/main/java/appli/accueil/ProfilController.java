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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Liste;
import repository.ListeRepository;
import repository.UtilisateurRepository;
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
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    private Button deconnexionButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button modifierProfilButton;

    @FXML
    private TextField nomListeTextField;

    @FXML
    private Text nomProfilText;

    @FXML
    private TextField nomTextField;

    @FXML
    private Button modifierListeButton;

    @FXML
    private Button supprimerListeButton;

    @FXML
    private Button nouvelleListeButton;

    @FXML
    private Button pageAdminButton;

    @FXML
    private TextField prenomTextField;

    @FXML
    private Button supprimerProfilButton;

    @FXML
    private Text reussiteText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomProfilText.setText("Profil de "+SessionUtilisateur.getInstance().getUtilisateur().getPrenom()+" "+SessionUtilisateur.getInstance().getUtilisateur().getNom());

        nomTextField.setText(SessionUtilisateur.getInstance().getUtilisateur().getNom());
        prenomTextField.setText(SessionUtilisateur.getInstance().getUtilisateur().getPrenom());
        emailTextField.setText(SessionUtilisateur.getInstance().getUtilisateur().getEmail());


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

        SessionUtilisateur.getInstance().getUtilisateur().setNom(nomTextField.getText());
        SessionUtilisateur.getInstance().getUtilisateur().setPrenom(prenomTextField.getText());
        SessionUtilisateur.getInstance().getUtilisateur().setEmail(emailTextField.getText());

        utilisateurRepository.mettreAJourUtilisateur(SessionUtilisateur.getInstance().getUtilisateur());
        reussiteText.setText("Profil bien mis à jour !");
        nomProfilText.setText("Profil de "+SessionUtilisateur.getInstance().getUtilisateur().getPrenom()+" "+SessionUtilisateur.getInstance().getUtilisateur().getNom());
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
    void OnSupprimerListeClick(ActionEvent event) {
            listeRepository.supprimerListe(tableView.getSelectionModel().getSelectedItem());
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void OnSupprimerProfilClick(ActionEvent event) throws IOException {
        utilisateurRepository.supprimerUtilisateur(SessionUtilisateur.getInstance().getUtilisateur());
        SessionUtilisateur.getInstance().deconnecter();
        StartApplication.changeScene("accueil/Login");
    }

    @FXML
    void OnTableViewPressed(MouseEvent event) throws IOException {
        int nbrClick = event.getClickCount();
        if (nbrClick == 2) {
            StartApplication.changeScene("accueil/Liste");

        }
    }

    @FXML
    void onModifierListeClick(ActionEvent event) {

    }

}
