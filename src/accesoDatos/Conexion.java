/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesoDatos;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucy
 */
public class Conexion {
    
    static String url = "jdbc:postgresql://localhost/trabajodegrado";
    static String user = "postgres";
    static String password = "tesis";
    
    public ResultSet EjecutarConsulta(String consulta, Object[] datos, String[] tipos) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(consulta);
            
            int i;
            int tam = datos.length;
            for(i = 1; i <= tam; i++)
            {
                if(tipos[i].equals("string"))
                {
                    pst.setString(i, datos[i].toString());
                }
                if(tipos[i].equals("int"))
                {
                    pst.setInt(i, Integer.parseInt(datos[i].toString()));
                }
                if(tipos[i].equals("double"))
                {
                    pst.setDouble(i, Double.parseDouble(datos[i].toString()));
                }
            }            
            rs = pst.executeQuery();
        } 
        catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Conexion.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Conexion.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return rs;
    }
}