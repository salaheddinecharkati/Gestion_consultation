package ma.enset.gestionprojet.Dao;

import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImp implements ConsultationDao{
    static Connection cnx=SingletonConnexionDb.getConnection();


    @Override
    public void add(Consultation consultation) {
        try{
            PreparedStatement stmt=cnx.prepareStatement("INSERT INTO consultation(DateConsultation,Description,patient_id) VALUES(?,?,?)");
            stmt.setDate(1,consultation.getDateConsultation());
            stmt.setString(2,consultation.getDescription());
            stmt.setLong(3,consultation.getPatient().getId());
            stmt.executeUpdate();}
        catch(SQLException e)
        {e.printStackTrace();}
    }

    @Override
    public List<Consultation> findbyAll() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM consultation");
            ResultSet res = stmt.executeQuery();

            // Assurez-vous que Pdi est une instance de PatientDaoImp ou d'une classe qui implémente findbyid()
            PatientDaoImp patientDao = new PatientDaoImp();

            while (res.next()) {
                Consultation c = new Consultation();
                c.setId(res.getLong("id"));
                c.setDateConsultation(res.getDate("DateConsultation"));
                c.setPatient(patientDao.findbyid(res.getLong("patient_id"))); // Utilisation correcte du setter
                c.setDescription(res.getString("Description"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }


    @Override
    public void delete(Consultation c) {
        try {
            PreparedStatement stmt = cnx.prepareStatement("delete from consultation where id=?");
            stmt.setLong(1,c.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Consultation c) {
        try {
            PreparedStatement stmt = cnx.prepareStatement("update Consultation set DateConsultation=?,Patient_id=?,Description=? where id=?");
            stmt.setDate(1,c.getDateConsultation());
            stmt.setLong(2, c.getPatient().getId());
            stmt.setString(3, c.getDescription());
            stmt.setLong(4,c.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consultation findbyid(Long id) {
        Consultation c=null;
        try{
            PreparedStatement stmt=cnx.prepareStatement("Select * from consultation where id=?");
            stmt.setLong(1,id);
            ResultSet rs= stmt.executeQuery();
            PatientDaoImp Pdi=new PatientDaoImp();
            if(rs.next()){
                c=new Consultation();
                c.setDescription(rs.getString("Description"));
                c.setDateConsultation(rs.getDate("DateConsultation"));
                c.setPatient(Pdi.findbyid(rs.getLong("patient_id")));
                c.setId(rs.getLong("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    @Override
    public List<Consultation> searchbyquery(String query) {
        List<Consultation> consultations = new ArrayList<>();
        PatientDaoImp patientDao = new PatientDaoImp();

        // Recherche du patient correspondant à la requête
        List<Patient> patients = patientDao.searchbyquery(query);
        Patient pt = null;

        for (Patient p : patients) {
            String sql = "SELECT * FROM consultation WHERE Patient_id = ?";
            try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
                stmt.setLong(1, p.getId()); // Utilisation correcte de l'ID du patient
                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {
                        Consultation c = new Consultation();
                        c.setId(res.getLong("id"));
                        c.setDateConsultation(res.getDate("DateConsultation"));
                        c.setPatient(patientDao.findbyid(res.getLong("patient_id"))); // Associer le patient trouvé
                        c.setDescription(res.getString("Description"));
                        consultations.add(c);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consultations;
    }

}

