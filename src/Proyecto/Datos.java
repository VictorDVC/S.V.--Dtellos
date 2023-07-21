/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class Datos {
    int ID;
    String nombres;
    String dni;
    String numerocontacto;
    String pagototal;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumerocontacto() {
        return numerocontacto;
    }

    public void setNumerocontacto(String numerocontacto) {
        this.numerocontacto = numerocontacto;
    }

    public String getPagototal() {
        return pagototal;
    }

    public void setPagototal(String pagototal) {
        this.pagototal = pagototal;
    }


    public void InsertarRegistro(JTextField paramNombreC,JTextField paramDni,JTextField paramContacto,JTextField paramTotal){
        //añadiendo datos de los parametros
        
        setNombres(paramNombreC.getText());
        setDni(paramDni.getText());
        setNumerocontacto(paramContacto.getText());
        setPagototal(paramTotal.getText());
        
        //Crear objeto de Conexion
        
        Conexion conectar = new Conexion();
        
        //Haciendo la consulta
        
        String consulta ="insert into Venta (nombres,dni,numerocontacto,pagototal) values (?,?,?,?)";
        
        try {
            CallableStatement cs = conectar.Establecer_Conexion().prepareCall(consulta);
            
            cs.setString(1, getNombres());
            cs.setString(2, getDni());
            cs.setString(3, getNumerocontacto());
            cs.setString(4, getPagototal());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se implemento correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se implemento correctamente"+e.toString());
        }
    }
    public void Mostrar(JTable parametroMostrar){
        
        Conexion conectar = new Conexion();
        
        //Crear un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        //Ordenar tabla alfabeticamente
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(modelo);
        parametroMostrar.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        //Añadiendo columnas a la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dni");
        modelo.addColumn("Numero de contacto");
        modelo.addColumn("Pago Total");
        
        //Añadiendo el modelo creado a la tabla
        parametroMostrar.setModel(modelo);
        
        sql = "select * from Venta;";
        
        String[] datos = new String[5];
        Statement st;
        
        try {
            st = conectar.Establecer_Conexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {                
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);
            
            modelo.addRow(datos);
            }
            
            parametroMostrar.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos"+e.toString());
        }
    }
    
    public void Seleccionar(JTable paramMostrar,JTextField paramID,JTextField paramNombreM,JTextField paramDniM,JTextField paramNumeroContactoM,JTextField paramTotal){
        try {
            int fila = paramMostrar.getSelectedRow();
            if (fila>=0) {
                paramID.setText(paramMostrar.getValueAt(fila, 0).toString());
                paramNombreM.setText(paramMostrar.getValueAt(fila, 1).toString());
                paramDniM.setText(paramMostrar.getValueAt(fila, 2).toString());
                paramNumeroContactoM.setText(paramMostrar.getValueAt(fila, 3).toString());
                paramTotal.setText(paramMostrar.getValueAt(fila, 4).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la seleccion, error: "+e.toString());
        }
    }
        public void Modificar(JTextField paramID,JTextField paramNombreM,JTextField paramDniM,JTextField paramNumeroContactoM,JTextField paramTotal ){
            setID(Integer.parseInt(paramID.getText()));
            setNombres(paramNombreM.getText());
            setDni(paramDniM.getText());
            setNumerocontacto(paramNumeroContactoM.getText());
            setPagototal(paramTotal.getText());
            
            Conexion conectar = new Conexion();
            
            String consulta  = "update Venta set  venta.nombres=?,venta.dni=?,venta.numerocontacto=?,venta.pagototal=? where venta.id=?;";
            
            try {
                CallableStatement cs = conectar.Establecer_Conexion().prepareCall(consulta);
                
                cs.setString(1, getNombres());
                cs.setString(2, getDni());
                cs.setString(3, getNumerocontacto());
                cs.setString(4, getPagototal());
                cs.setInt(5, getID());
                
                cs.execute();
                
                JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la modificacion, error: "+e.toString());
            }
        }
        
        public void Eliminar (JTextField paramIDM){
            setID(Integer.parseInt(paramIDM.getText()));
            
            Conexion conectar = new Conexion();
            
            String consulta = "Delete from Venta where venta.id=?;";
            
            try {
                CallableStatement cs = conectar.Establecer_Conexion().prepareCall(consulta);
                
                cs.setInt(1, getID());
                
                cs.execute();
                
                JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar, error: "+e.toString());
            }
        }
}
