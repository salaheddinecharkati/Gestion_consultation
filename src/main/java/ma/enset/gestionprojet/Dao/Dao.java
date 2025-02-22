package ma.enset.gestionprojet.Dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E,U,V> {
    void add(E e);
    List<E> findbyAll();
    void delete(E e);
    void update(E e);
    E findbyid(U id);
    List<E> searchbyquery(V query);

}
