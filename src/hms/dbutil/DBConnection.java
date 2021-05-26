/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DBConnection {
    private static Connection conn;
    static 
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-2QMDRUS:1521/xe","myhms","student");
            //JOptionPane.showMessageDialog(null,"Connection done successfully");
            
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null,"Cannot load driver!"+cnfe);
            cnfe.printStackTrace();
        }
        catch(SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null,"Problem in DB!"+sqlex);
            sqlex.printStackTrace();
        
        }
    
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            if(null!=conn)
            {
                conn.close();
                JOptionPane.showMessageDialog(null,"Connection close Successfully!");
            }
        }
        catch(SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Problem in closing connection!"+sqlex);
             sqlex.printStackTrace();
        }
    }
}

