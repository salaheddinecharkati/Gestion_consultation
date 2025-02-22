module ma.enset.gestionprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;


    opens ma.enset.gestionprojet.Controllers to javafx.fxml;
    opens ma.enset.gestionprojet.entities to javafx.base;
    exports ma.enset.gestionprojet;
    exports ma.enset.gestionprojet.entities;

}