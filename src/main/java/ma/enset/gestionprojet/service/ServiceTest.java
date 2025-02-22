package ma.enset.gestionprojet.service;

import ma.enset.gestionprojet.Dao.ConsultationDaoImp;
import ma.enset.gestionprojet.Dao.PatientDaoImp;
import ma.enset.gestionprojet.entities.Patient;

public class ServiceTest {
    public static void main(String[] args) {
            Icabinet service = new Cabinetservice(new ConsultationDaoImp(), new PatientDaoImp());

            Patient p = new Patient("salaheddine", "charkati", "06452179893");
            //service.addPatient(p);

            System.out.println("Patient ajouté avec succès !");
            Patient pa=service.findPatientbyid(1L);
        System.out.println(pa);
    }
}
