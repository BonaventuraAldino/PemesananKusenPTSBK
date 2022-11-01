/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PemesananPT_SBK.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Statement;

/**
 *
 * @author riski
 */
public class Koneksi {
    public Connection con;
    public Statement stm;
    public boolean KoneksiDatabase(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/ptsbk",
                    "root",
                    "");
            con = conn;
            stm = con.createStatement();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error :"+e);
            return false;
       }
    }
}
