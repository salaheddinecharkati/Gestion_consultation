package ma.enset.gestionprojet.entities;

import java.util.List;

public class Patient {
    private Long id;
    private String Nom;
    private String Prenom;
    private String tel;
    private List<Consultation> consultations;

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Patient() {
    }
    public Patient(String nom,String Prenom,String tel) {
        this.Nom = nom;
        this.Prenom=Prenom;
        this.tel=tel;
    }
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return  Prenom + " " + Nom ;
    }
}
