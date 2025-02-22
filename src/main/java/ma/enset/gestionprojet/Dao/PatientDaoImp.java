package ma.enset.gestionprojet.Dao;

import ma.enset.gestionprojet.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImp implements PatientDao{
    static Connection cnx=SingletonConnexionDb.getConnection();
    @Override
    public void add(Patient patient)  {
        try{PreparedStatement stmt=cnx.prepareStatement("INSERT INTO Patient(NOM,PRENOM,TEL) VALUES(?,?,?)");
        stmt.setString(1, patient.getNom());
        stmt.setString(2, patient.getPrenom());
        stmt.setString(3, patient.getTel());
        stmt.executeUpdate();}
        catch(SQLException e)
        {e.printStackTrace();}
    }

    @Override
    public List<Patient> findbyAll() {
        List<Patient> Patients=new ArrayList<Patient>();
        try {
            PreparedStatement stmt = cnx.prepareStatement("select * From Patient");
            ResultSet res=stmt.executeQuery();
            while (res.next()){
                Patient p=new Patient();
                p.setId(res.getLong("id"));
                p.setNom(res.getString("Nom"));
                p.setPrenom(res.getString("Prenom"));
                p.setTel(res.getString("Tel"));
                Patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Patients;
    }

    @Override
    public void delete(Patient patient)  {
        try {
            PreparedStatement stmt = cnx.prepareStatement("delete from Patient where id=?");
            stmt.setLong(1,patient.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Patient patient) {
        try {
            PreparedStatement stmt = cnx.prepareStatement("update Patient set Nom=?,Prenom=?,tel=? where id=?");
            stmt.setString(1,patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getTel());
            stmt.setLong(4,patient.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient findbyid(Long id) {
        Patient p=null;
        try{
            PreparedStatement stmt=cnx.prepareStatement("Select * from Patient where id=?");
            stmt.setLong(1,id);
            ResultSet rs= stmt.executeQuery();
            if(rs.next()){
                p=new Patient();
                p.setTel(rs.getString("tel"));
                p.setPrenom(rs.getString("Prenom"));
                p.setNom(rs.getString("Nom"));
                p.setId(rs.getLong("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Patient> searchbyquery(String query) {
        List<Patient> Patients=new ArrayList<Patient>();
        try {
            PreparedStatement stmt = cnx.prepareStatement("select * From Patient where Nom like ? or Prenom like ?");
            stmt.setString(1,"%"+query+"%");
            stmt.setString(2,"%"+query+"%");
            ResultSet res=stmt.executeQuery();
            while (res.next()){
                Patient p=new Patient();
                p.setId(res.getLong("id"));
                p.setNom(res.getString("Nom"));
                p.setPrenom(res.getString("Prenom"));
                p.setTel(res.getString("Tel"));
                Patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Patients;
    }

}
