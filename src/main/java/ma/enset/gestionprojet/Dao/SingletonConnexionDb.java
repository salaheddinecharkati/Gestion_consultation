package ma.enset.gestionprojet.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDb {
    private static String dburl= "jdbc:mysql://localhost:3306/Db_gestion_Consultation";
    private static String username="root";
    private static String password="sc2002";
    private static Connection connection;
    static{
        try{
            connection = DriverManager.getConnection(dburl, username, password);
            System.out.println("Connecté avec succès");
        }catch(SQLException e){
            System.out.println("une erreur s'est produit");
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
