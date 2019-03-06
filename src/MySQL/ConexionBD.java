
package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AppsMX
 */
public class ConexionBD {
    private static Connection cn;
    
    public static Connection conectarBase(String user, String pass){
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            user = "root";
            cn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/contablemx", "root", "" );
            System.out.println("Se hizo la conexión a la BD");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se hizo la conexión a la BD");
        }
        return cn;
    }
}
