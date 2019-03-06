
package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;

/**
 *
 * @author AppsMX
 */
public class Crud {
    private static Connection cn;

    public static void setCn(Connection cn) {
        Crud.cn = cn;
    }
    
    public static void create(String sql){
        checkConnection();
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Registro eliminado!");
            alert.setHeaderText("Todo ha salido bien");
            alert.setContentText("ContableMX - Powered by AppsMX");
            alert.showAndWait();
        } catch(SQLException ex){
            if( ex.getErrorCode() == 1062 ){ 
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Registro duplicado");
                alert.setHeaderText("El registro que intenta ingresar está duplicado");
                alert.setContentText("Corrobore que la información no está duplicada\n"
                        + "Si el problema persiste póngase en contacto con su proveedor");
                alert.showAndWait();
            }
        }
    }
    
    public static LinkedList<Object[]> read(String sql, int datos){
        checkConnection();
        LinkedList<Object[]> query = null;
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            query = new LinkedList<Object[]>();
            while( rs.next() ){
                Object rows[] = new Object[datos];
                for( int i = 0; i < datos; i++ ){
                    rows[i] = rs.getObject( i + 1 );
                }
                query.add( rows );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() );
            ex.printStackTrace();
        }
        return query;
    }
    
    public static void update(String sql){
        checkConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "¡Query exitoso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getErrorCode() );
        }
    }
    
    public static void delete(String sql){
        checkConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Registro exitoso!");
            alert.setHeaderText("Todo ha salido bien");
            alert.setContentText("ContableMX - Powered by AppsMX");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("No se puede eliminar!!!!");
                alert.setHeaderText("El registro no puede ser eliminado ahora");
                alert.setContentText("Si el problema persiste póngase en contacto con su proveedor");
                alert.showAndWait();
        }
    }
    
    public static void checkConnection(){
        try {
            if( cn.isClosed() ){
                JOptionPane.showMessageDialog(null, "La conexión se perdió darle OK si quieres continuar");
                cn = ConexionBD.conectarBase("root", "root");
            }
            else{
                System.out.println("Connection continue!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception SQL: \n"
                    + "Error: " + ex.getErrorCode() );
        }
    }
}
