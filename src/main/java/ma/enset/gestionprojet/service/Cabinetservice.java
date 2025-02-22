package ma.enset.gestionprojet.service;

import ma.enset.gestionprojet.Dao.ConsultationDao;
import ma.enset.gestionprojet.Dao.PatientDao;
import ma.enset.gestionprojet.Dao.PatientDaoImp;
import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class Cabinetservice implements Icabinet{
    PatientDao patientDao;
    ConsultationDao consultationDao;

    public Cabinetservice(ConsultationDao consultationDao, PatientDao patientDao) {
        this.consultationDao = consultationDao;
        this.patientDao = patientDao;
    }

    @Override
    public void addPatient(Patient p) {
        patientDao.add(p);
    }

    @Override
    public List<Patient> findAllPatient() {
        List<Patient> p=new ArrayList<Patient>();
        p=patientDao.findbyAll();
        return p;
    }

    @Override
    public void deletePatient(Patient e) {
        patientDao.delete(e);
    }

    @Override
    public void updatePatient(Patient e) {
        patientDao.update(e);
    }

    @Override
    public Patient findPatientbyid(Long id) {
        Patient p=null;
        p=patientDao.findbyid(id);
        return p;
    }

    @Override
    public void addConsultation(Consultation c) {
        consultationDao.add(c);
    }

    @Override
    public List<Consultation> findAllConsultation() {
        List<Consultation> consultations=new ArrayList<Consultation>();
        consultations=consultationDao.findbyAll();
        return consultations;
    }

    @Override
    public void deleteConsultation(Consultation c) {
        consultationDao.delete(c);
    }

    @Override
    public void updateConsultation(Consultation e) {
        consultationDao.update(e);
    }

    @Override
    public Consultation findConsultationbyid(Long id) {
        Consultation c=new Consultation();
        c=consultationDao.findbyid(id);
        return c;
    }
    public List<Patient> searchbyquery(String query){
        List<Patient> Patients=new ArrayList<Patient>();
        Patients= patientDao.searchbyquery(query);
        return Patients;
    }

    @Override
    public List<Consultation> searchbyqueryc(String query) {
        List<Consultation> consultations=new ArrayList<Consultation>();
        consultations=consultationDao.searchbyquery(query);
        return consultations;
    }
}
