package ma.enset.gestionprojet.service;

import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;

import java.util.List;

public interface Icabinet {
    void addPatient (Patient e);
    List<Patient> findAllPatient();
    void deletePatient(Patient e) ;
    void updatePatient(Patient e);
    Patient findPatientbyid(Long id);
    void addConsultation(Consultation e);
    List<Consultation> findAllConsultation();
    void deleteConsultation(Consultation e) ;
    void updateConsultation(Consultation e);
    Consultation findConsultationbyid(Long id);
    List<Patient> searchbyquery(String query);
    List<Consultation> searchbyqueryc(String query);

}
