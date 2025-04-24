package appli.accueil;

import appli.StartApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Tache;
import repository.TacheRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListeController implements Initializable {

    @FXML
    private Button ajouterButton;

    @FXML
    private Button confirmerModifButton;

    @FXML
    private TextField etatModifTextField;

    @FXML
    private TextField etatTextField;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField nomModifTextField;

    @FXML
    private Text nomProjetText;

    @FXML
    private TextField nomTextField;

    @FXML
    private Button retourButton;

    @FXML
    private Button supprimerButton;

    private int refListe;

    @FXML
    private TableView<Tache> tachesTableView;

    private TacheRepository tacheRepository = new TacheRepository();

    public void initData(Liste liste){
        nomProjetText.setText(liste.getNom());
        refListe = liste.getIdListe();
//PROBLEME INIT DATA ENVOIE APS LA REF ET FAIRE LE SELECT SUR LE TABLEAU
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String [][] colonnes = {
                { "Id Tache","idTache" },
                { "Nom","nom" },
                { "Etat","etat" },
                { "Ref Liste","refListe" },
                { "Ref Type","refType" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Tache, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Tache, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            tachesTableView.getColumns().add(maCol);
        }
        tachesTableView.getItems().addAll(tacheRepository.recupererTaches(refListe));
        System.out.println(refListe);
    }

    @FXML
    void onAjouterClick(ActionEvent event) {
        TacheRepository tacheRepository = new TacheRepository();
        //Tache tache = new Tache(nomTextField.getText(), etatTextField.getText(), refListe,);
        //tacheRepository.ajouterTache(tache);
        // MAJ TABLEAU
    }

    @FXML
    void onConfirmerClick(ActionEvent event) {
        //Tache tache = new Tache(nomTextField.getText(), etatTextField.getText(), refListe, REFTACHE A METTRE MODIF LE SELECT);
        TacheRepository tacheRepository = new TacheRepository();
        //tacheRepository.modifierTache(tache);
        // MAJ TABLEAU
        //Cacher parti modif
    }

    @FXML
    void onModifierClick(ActionEvent event) {
        // montrer parti modif
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Profil");
    }

    @FXML
    void onSupprimerClick(ActionEvent event) {
        TacheRepository tacheRepository = new TacheRepository();
        tacheRepository.supprimerTache(tachesTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onTableViewPressed(MouseEvent event) {

    }

}

