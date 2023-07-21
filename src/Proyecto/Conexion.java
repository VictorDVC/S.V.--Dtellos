
package Proyecto;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar =  null;
    String usuario = "root";
    String contraseña = "Knox210@";
    String database = "basedatos";
    String servidor = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + servidor + ":" + puerto + "/" + database;
    
    public Connection Establecer_Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,contraseña);
            //JOptionPane.showMessageDialog(null, "Se realizo la conexion exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos"+e.toString());
        }
        return conectar;
    }
    
}
