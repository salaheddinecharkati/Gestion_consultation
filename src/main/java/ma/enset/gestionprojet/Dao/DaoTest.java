package ma.enset.gestionprojet.Dao;
import ma.enset.gestionprojet.entities.Consultation;
import ma.enset.gestionprojet.entities.Patient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DaoTest {
        public static void main(String[] args) {
            //Patient p=new Patient("salaheddine","charkati","064523245");
            //p.setId(1L);
            //p.setId(3L);
            //System.out.println(p);
            //PDI.add(p);
            //PDI.delete(p);
            String query="salah";
            ConsultationDaoImp cdi=new ConsultationDaoImp();
            List<Consultation> cs=cdi.searchbyquery(query);
            for(Consultation c:cs){
                System.out.println(c);
            }

        }
    }
