package ma.enset.gestionprojet.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionprojet.Dao.ConsultationDaoImp;
import ma.enset.gestionprojet.Dao.PatientDaoImp;
import ma.enset.gestionprojet.entities.Patient;
import ma.enset.gestionprojet.service.Cabinetservice;
import ma.enset.gestionprojet.service.Icabinet;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML private TextField TFNom;
    @FXML private TextField TFPrenom;
    @FXML private TextField TFTel;
    @FXML private TextField TFSearch;
    @FXML private Button BTNDel;
    @FXML private Button BTNMod;
    @FXML private Button BTNAdd;
    @FXML private TableView<Patient> tabpatient;
    @FXML private TableColumn<Patient,Long> CLNId;
    @FXML private TableColumn<Patient,String> CLNNom;
    @FXML private TableColumn<Patient,String>CLNPrenom;
    @FXML private TableColumn<Patient,String> CLNTel;
    @FXML private Label LabeL1;
    Icabinet icabinet;
    private Patient selectedPatient;
    public static ObservableList<Patient> Patients= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        icabinet = new Cabinetservice(new ConsultationDaoImp(), new PatientDaoImp());
        Loadtable();
        CLNId.setCellValueFactory(new PropertyValueFactory<>("id"));
        CLNNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        CLNPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        CLNTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        TFSearch.textProperty().addListener(((observableValue, oldvalue, newvalue) ->{
            Patients.setAll(icabinet.searchbyquery(newvalue));
        }));
        tabpatient.getSelectionModel().selectedItemProperty().addListener(((observableValue, patient, newp) -> {
            if(newp!=null)
            {TFNom.setText(newp.getNom());
            TFPrenom.setText(newp.getPrenom());
            TFTel.setText(newp.getTel());
            selectedPatient=newp;}
        }));
    }
    private void reinitialiser(){
        TFNom.setText("");
        TFPrenom.setText("");
        TFTel.setText("");
    }
    public void addPatientact() {
        Patient p = new Patient();

        // Récupération des valeurs des champs
        String nom = TFNom.getText().trim();
        String prenom = TFPrenom.getText().trim();
        String tel = TFTel.getText().trim();

        // Vérification si tous les champs sont vides
        if (nom.isEmpty() && prenom.isEmpty() && tel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champs vides", "Les champs sont vides. Veuillez remplir tous les champs.");
            TFNom.getStyleClass().add("error");
            TFPrenom.getStyleClass().add("error");
            TFTel.getStyleClass().add("error");
            return;
        }

        // Vérification des champs un par un
        if (nom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Nom manquant", "Veuillez saisir le nom.");
            TFNom.getStyleClass().add("error");
            return;
        }
        if (prenom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Prénom manquant", "Veuillez saisir le prénom.");
            TFPrenom.getStyleClass().add("error");
            return;
        }
        if (tel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Téléphone manquant", "La zone du téléphone est vide.");
            TFTel.getStyleClass().add("error");
            return;
        }

        // Vérification que le téléphone contient uniquement des chiffres
        if (!tel.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Téléphone invalide", "Veuillez saisir uniquement des chiffres pour le téléphone.");
            TFTel.getStyleClass().add("error");
            return;
        }
        TFNom.getStyleClass().remove("error");
        TFPrenom.getStyleClass().remove("error");
        TFTel.getStyleClass().remove("error");
        // Si tout est bon, on crée l'objet Patient
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setTel(tel);

        // Ajout du patient et mise à jour de la table
        icabinet.addPatient(p);
        Loadtable();
        reinitialiser();
        // Effacer le message d'erreur après ajout réussi
        LabeL1.setText("");
    }

    public void delPatientact(){
        int index =tabpatient.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("aucun patient est selectionné");
            alert.show();
            return; // On quitte la méthode si aucun patient n'est sélectionné
        }
        icabinet.deletePatient(selectedPatient);
        LabeL1.setText("patient supprimé !");
        Loadtable();
        reinitialiser();
    }
    public void ModPatientact(){
        int index =tabpatient.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("aucun patient est selectionné");
            alert.show();
            return; // On quitte la méthode si aucun patient n'est sélectionné
        }
        String nom = TFNom.getText().trim();
        String prenom = TFPrenom.getText().trim();
        String tel = TFTel.getText().trim();
        if (nom.isEmpty() && prenom.isEmpty() && tel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Champs vides", "Les champs sont vides. Veuillez remplir tous les champs.");
            TFNom.getStyleClass().add("error");
            TFPrenom.getStyleClass().add("error");
            TFTel.getStyleClass().add("error");
            return;
        }

        // Vérification des champs un par un
        if (nom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Nom manquant", "Veuillez saisir le nom.");
            TFNom.getStyleClass().add("error");
            return;
        }
        if (prenom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Prénom manquant", "Veuillez saisir le prénom.");
            TFPrenom.getStyleClass().add("error");
            return;
        }
        if (tel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Téléphone manquant", "La zone du téléphone est vide.");
            TFTel.getStyleClass().add("error");
            return;
        }

        // Vérification que le téléphone contient uniquement des chiffres
        if (!tel.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Téléphone invalide", "Veuillez saisir uniquement des chiffres pour le téléphone.");
            TFTel.getStyleClass().add("error");
            return;
        }
        TFNom.getStyleClass().remove("error");
        TFPrenom.getStyleClass().remove("error");
        TFTel.getStyleClass().remove("error");
        // Si tout est bon, on crée l'objet Patient
        selectedPatient.setNom(nom);
        selectedPatient.setPrenom(prenom);
        selectedPatient.setTel(tel);
        icabinet.updatePatient(selectedPatient);
        LabeL1.setText("patient modifié !");
        Loadtable();
        reinitialiser();
    }
    private void Loadtable(){
        Patients.setAll(icabinet.findAllPatient());
        tabpatient.setItems(Patients);
    }
    private void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}

