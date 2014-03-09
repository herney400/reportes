/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesoDatos;
import java.sql.*;

/**
 *
 * @author Lucy
 */
public class Conexion {

    static String baseDatos = "trabajodegrado";
    static String puerto = "5432";
    static String usuario = "postgres";
    static String clave = "tesis";
    static String ip = "localhost";

    public static ResultSet consultar (String consulta) throws Exception
    {
        ResultSet reg = null; 
        Connection conex  = null;
        Statement SQL = null;
        
        try
        {
            conex=conexion();
            SQL = conex.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            reg = SQL.executeQuery(consulta);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }        
        return reg;
    }
    
    public static Connection conexion()
    {
        Connection conex = null;
        String URL = "jdbc:postgresql://localhost:5432/trabajodegrado";

        try
        {
            Class.forName("org.postgresql.Driver" );
            conex = DriverManager.getConnection(URL, "postgres", "tesis" );
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return conex;
    }
    
    public int ejecutar(String consulta)
    {
        int conclusion = -1;
        Connection conex;
        Statement sql = null;
        try
        {
            conex = conexion();
            sql = conex.createStatement();
            sql.executeUpdate(consulta);
            sql.close();
            conex.close();
            conclusion = 1;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return conclusion;
    }

    public static boolean verificar(String x, String campo, String tabla)
    {
        ResultSet reg;
        try
        {
            String consulta = "Select count ("+campo+" ) from "+tabla+" where "+campo+" = '"+x+"'";
            reg = consultar(consulta);
            reg.first();
            int n = reg.getInt(1);
            if (n>0)
            {
                return true; 
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ResultSet Registros(String consulta)
    {
        ResultSet reg=null;
        Connection conex=null;
        Statement sql=null;
        try
        {
            conex=conexion();
            sql=conex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            reg=sql.executeQuery(consulta);
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return reg;
    } 
    
}
