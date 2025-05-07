package appli.accueil;

import appli.StartApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Tache;
import model.Type;
import repository.TacheRepository;
import repository.TypeRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeController implements Initializable {

    @FXML
    public ComboBox<Type> ModifChoiceBox;

    @FXML
    public ComboBox<Type> AjouterChoiceBox;

    @FXML
    public Button ajouterUtilisateur;

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

    @FXML
    private TableView<Tache> tachesTableView;

    private int refListe;

    private TacheRepository tacheRepository = new TacheRepository();
    private TypeRepository typeRepository = new TypeRepository();

    Liste liste;

    public void initData(Liste liste){
        this.liste = liste;
        nomProjetText.setText(liste.getNom());
        refListe = liste.getIdListe();
        tachesTableView.getItems().addAll(tacheRepository.recupererTaches(refListe));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        List<Type> types = typeRepository.getAllType();
        this.AjouterChoiceBox.getItems().addAll(types);
        this.ModifChoiceBox.getItems().addAll(types);

        this.AjouterChoiceBox.getSelectionModel().select(0);
        this.ModifChoiceBox.getSelectionModel().select(0);

        this.nomModifTextField.setDisable(true);
        this.etatModifTextField.setDisable(true);
        this.ModifChoiceBox.setDisable(true);
        this.modifierButton.setDisable(true);

        String [][] colonnes = {
                { "Nom","nom" },
                { "Etat","etat" },
                { "Type","refType" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){

//Création de la colonne avec le titre
            TableColumn<Tache, String> maCol = new TableColumn<>(colonnes[i][0]);

//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(new PropertyValueFactory<Tache, String>(colonnes[i][1]));

//Ajout de la colonne dans notre tableau
            tachesTableView.getColumns().add(maCol);
        }
    }

    @FXML
    void onAjouterClick(ActionEvent event) {
        TacheRepository tacheRepository = new TacheRepository();

        Tache tache = new Tache(nomTextField.getText(), Integer.parseInt(etatTextField.getText()), refListe ,typeRepository.getTypeParNom(AjouterChoiceBox.getSelectionModel().getSelectedItem().toString()));
        tache = tacheRepository.ajouterTache(tache);
        if (tache.getIdTache() != 0 ){
            tachesTableView.getItems().add(tache);
        }else{
            System.out.println("erreur lors de l'ajour");
        }
    }

    @FXML
    void onAjouterUserClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AjoutUser");
        AjoutUserController controller = (AjoutUserController)
                StartApplication.getControllerFromStage();
        controller.initData(liste);
    }

    @FXML
    void onConfirmerClick(ActionEvent event) {

        TacheRepository tacheRepository = new TacheRepository();
        Tache tache = tachesTableView.getSelectionModel().getSelectedItem();
        tache.setNom(nomModifTextField.getText());
        tache.setEtat(Integer.parseInt(etatModifTextField.getText()));
        tache.setRefType(ModifChoiceBox.getSelectionModel().getSelectedItem().getIdType());
        tacheRepository.modifierTache(tache);
        tachesTableView.refresh();

        this.nomModifTextField.setDisable(true);
        this.etatModifTextField.setDisable(true);
        this.ModifChoiceBox.setDisable(true);
        this.modifierButton.setDisable(true);
    }

    @FXML
    void onModifierClick(ActionEvent event) {
        this.nomModifTextField.setDisable(false);
        this.etatModifTextField.setDisable(false);
        this.ModifChoiceBox.setDisable(false);
        this.modifierButton.setDisable(false);
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