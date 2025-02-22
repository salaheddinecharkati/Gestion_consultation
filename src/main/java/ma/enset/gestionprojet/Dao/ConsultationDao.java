package ma.enset.gestionprojet.Dao;

import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;

import java.util.List;

public interface ConsultationDao extends Dao<Consultation,Long,String>{
    void add(Consultation e);
    List<Consultation> findbyAll();
    void delete(Consultation e) ;
    void update(Consultation e);
    Consultation findbyid(Long id);
    List<Consultation> searchbyquery(String query);
}
