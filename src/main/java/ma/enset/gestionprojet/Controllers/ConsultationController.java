package ma.enset.gestionprojet.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionprojet.Dao.ConsultationDaoImp;
import ma.enset.gestionprojet.Dao.PatientDaoImp;
import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;
import ma.enset.gestionprojet.service.Cabinetservice;
import ma.enset.gestionprojet.service.Icabinet;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML private DatePicker DatePicker;
    @FXML private ComboBox<Patient> ComPatient;
    @FXML private TextArea TADes;
    @FXML private Button BTNaddc;
    @FXML private Button BTNdelc;
    @FXML private Button BTNmodc;
    @FXML private Button BTNsrc;
    @FXML private TextField TFsrc;
    @FXML private TableView<Consultation> tabc;
    @FXML private TableColumn<Consultation,Long> CNid;
    @FXML private TableColumn<Consultation, Date> CNDate;
    @FXML private TableColumn<Consultation,Patient>CNpatient;
    @FXML private TableColumn<Consultation,String> CNDes;
    @FXML private Label LabeL1;
    @FXML private Label LabeL2;
    Icabinet icabinet;
    private ObservableList<Consultation> consultations= FXCollections.observableArrayList();
    private Consultation selectedconsultation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        icabinet = new Cabinetservice(new ConsultationDaoImp(), new PatientDaoImp());
        Loadtable();
        PatientController.Patients.setAll(icabinet.findAllPatient());
        ComPatient.setItems(PatientController.Patients);
        CNid.setCellValueFactory(new PropertyValueFactory<>("id"));
        CNDate.setCellValueFactory(new PropertyValueFactory<>("DateConsultation"));
        CNpatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        CNDes.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tabc.getSelectionModel().selectedItemProperty().addListener((((observableValue, consultation, newconsultation) -> {
            if(newconsultation!=null)
            {   ComPatient.getSelectionModel().select(newconsultation.getPatient());
                TADes.setText(newconsultation.getDescription());
                DatePicker.setValue(newconsultation.getDateConsultation().toLocalDate());
                selectedconsultation=newconsultation;}
        })));
        TFsrc.textProperty().addListener(((observableValue, oldvalue, newvalue) ->{
            consultations.setAll(icabinet.searchbyqueryc(newvalue));
        } ));
    }
    private void Loadtable(){
        consultations.setAll(icabinet.findAllConsultation());
        tabc.setItems(consultations);
    }
    private void reinitialiser(){
        DatePicker.setValue(null);  // Réinitialiser la date sélectionnée
        TADes.setText("");          // Réinitialiser le contenu du TextArea

        // Réinitialiser la sélection du patient
        ComPatient.getSelectionModel().clearSelection();
    }
    public void addcon() {
        // Conversion en java.sql.Date
        Date date = Date.valueOf(DatePicker.getValue());
        Patient p = ComPatient.getSelectionModel().getSelectedItem();
        String description = TADes.getText().trim();
        if (DatePicker.getValue() == null || ComPatient.getSelectionModel().isEmpty() || TADes.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            TADes.getStyleClass().add("error");
            ComPatient.getStyleClass().add("error");
            DatePicker.getStyleClass().add("error");
            return;
        }
        if (DatePicker.getValue() == null) {
            // Afficher un message d'erreur ou faire une action appropriée
            System.out.println("Veuillez sélectionner une date.");
            return;  // Empêche de continuer si la date n'est pas sélectionnée
        }
        TADes.getStyleClass().remove("error");
        ComPatient.getStyleClass().remove("error");
        DatePicker.getStyleClass().remove("error");
        // Création et remplissage de l'objet Consultation
        Consultation consultation = new Consultation();
        consultation.setDateConsultation(date);
        consultation.setDescription(description);
        consultation.setPatient(p);

        // Ajout de la consultation et mise à jour de la table
        icabinet.addConsultation(consultation);
        Loadtable();
        reinitialiser();
        // Affichage du message de succès
        LabeL1.setText("Consultation ajoutée avec succès.");
    }
    public void Modc(){
        // Vérification si un patient est sélectionné
        if (DatePicker.getValue() == null || ComPatient.getSelectionModel().isEmpty() || TADes.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            TADes.getStyleClass().add("error");
            ComPatient.getStyleClass().add("error");
            DatePicker.getStyleClass().add("error");
            return;
        }
        TADes.getStyleClass().remove("error");
        ComPatient.getStyleClass().remove("error");
        DatePicker.getStyleClass().remove("error");
        if (DatePicker.getValue() == null) {
            // Afficher un message d'erreur ou faire une action appropriée
            System.out.println("Veuillez sélectionner une date.");
            return;  // Empêche de continuer si la date n'est pas sélectionnée
        }
        selectedconsultation.setPatient(ComPatient.getSelectionModel().getSelectedItem());
        Date date=Date.valueOf(DatePicker.getValue());
        selectedconsultation.setDateConsultation(date);
        selectedconsultation.setDescription(TADes.getText());
        icabinet.updateConsultation(selectedconsultation);
        LabeL1.setText("patient modifié !");
        Loadtable();
        reinitialiser();
    }
    public void delc(){
        Consultation c=tabc.getSelectionModel().getSelectedItem();
        int index=tabc.getSelectionModel().getSelectedIndex();
        if (index==-1){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("aucun consultation est selectionnée");
            alert.show();
        }
        icabinet.deleteConsultation(c);
        Loadtable();
    }
    private void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
