package ma.enset.gestionprojet.entities;

import java.sql.Date;

public class Consultation {
    private Long id;
    private Date DateConsultation;
    private String Description;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Date dateConsultation, String description, Patient patient) {
        this.DateConsultation = dateConsultation;
        this.Description = description;
        this.patient = patient;
    }

    public Date getDateConsultation() {
        return DateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        DateConsultation = dateConsultation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "DateConsultation=" + DateConsultation +
                ", id=" + id +
                ", Description='" + Description + '\'' +
                ", patient=" + patient +
                '}';
    }
}
