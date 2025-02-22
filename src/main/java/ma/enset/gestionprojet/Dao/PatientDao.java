package ma.enset.gestionprojet.Dao;

import ma.enset.gestionprojet.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao extends Dao<Patient,Long,String>{
    void add(Patient e);
    List<Patient> findbyAll();
    void delete(Patient e) ;
    void update(Patient e);
    Patient findbyid(Long id);
    List<Patient> searchbyquery(String query);
}
